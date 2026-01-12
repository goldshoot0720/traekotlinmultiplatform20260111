package com.example.traekotlinmultiplatform20260111.data

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText
import io.ktor.http.URLBuilder
import io.ktor.http.appendPathSegments
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

class ContentfulDeliveryClient(
    private val httpClient: HttpClient,
    private val spaceId: String,
    private val accessToken: String,
    private val environmentId: String = "master"
) {
    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    suspend fun fetchEntries(
        contentType: String? = null,
        limit: Int = 100,
        include: Int = 2
    ): ContentfulEntriesResponse {
        val url = URLBuilder("https://cdn.contentful.com").apply {
            appendPathSegments("spaces", spaceId, "environments", environmentId, "entries")
        }.build().toString()

        val text = httpClient.get(url) {
            parameter("access_token", accessToken)
            parameter("limit", limit)
            parameter("include", include)
            if (!contentType.isNullOrBlank()) {
                parameter("content_type", contentType)
            }
        }.bodyAsText()

        val root = json.parseToJsonElement(text).jsonObject
        val items = root["items"]?.jsonArray ?: JsonArray(emptyList())
        val includesAssets = root["includes"]
            ?.jsonObject
            ?.get("Asset")
            ?.jsonArray
            ?: JsonArray(emptyList())

        val assetsById = includesAssets
            .map { it.jsonObject }
            .associateBy { it.getString("sys.id") ?: "" }
            .filterKeys { it.isNotBlank() }

        return ContentfulEntriesResponse(
            items = items.mapNotNull { it as? JsonObject ?: it.jsonObject },
            assetsById = assetsById
        )
    }
}

data class ContentfulEntriesResponse(
    val items: List<JsonObject>,
    val assetsById: Map<String, JsonObject>
)

fun ContentfulEntriesResponse.toFoodItems(): List<ContentfulFoodItem> {
    val candidateItems = items.mapNotNull { item ->
        val sys = item["sys"]?.jsonObject
        val contentTypeId = sys?.getString("contentType.sys.id")
        val entryId = sys?.getString("id") ?: return@mapNotNull null
        val fields = item["fields"]?.jsonObject ?: JsonObject(emptyMap())
        ContentfulEntry(entryId = entryId, contentTypeId = contentTypeId, fields = fields)
    }

    val filtered = run {
        val foodTypes = candidateItems
            .mapNotNull { it.contentTypeId?.lowercase() }
            .filter { it.contains("food") || it.contains("foods") || it.contains("grocery") || it.contains("pantry") || it.contains("fridge") }
            .distinct()

        if (foodTypes.isNotEmpty()) {
            candidateItems.filter { it.contentTypeId?.lowercase() in foodTypes }
        } else {
            candidateItems
        }
    }

    return filtered.mapNotNull { entry ->
        val name = entry.fields.getFirstString("name", "title", "productName", "itemName") ?: return@mapNotNull null
        val expiry = entry.fields.getFirstString("expiry", "expireDate", "expirationDate", "toDate", "date") ?: ""
        val quantity = entry.fields.getFirstString("quantity", "qty", "amount", "count") ?: ""
        val price = entry.fields.getFirstString("price", "cost", "amountPrice") ?: ""
        val shop = entry.fields.getFirstString("shop", "store", "location", "vendor") ?: ""
        val imageUrl = entry.fields.getAssetUrl(
            assetsById = this.assetsById,
            keys = listOf("image", "photo", "picture", "asset", "thumbnail")
        )

        ContentfulFoodItem(
            id = entry.entryId,
            name = name,
            expiry = expiry,
            quantity = quantity,
            price = price,
            shop = shop,
            imageUrl = imageUrl
        )
    }
}

data class ContentfulFoodItem(
    val id: String,
    val name: String,
    val expiry: String,
    val quantity: String,
    val price: String,
    val shop: String,
    val imageUrl: String?
)

private data class ContentfulEntry(
    val entryId: String,
    val contentTypeId: String?,
    val fields: JsonObject
)

private fun JsonObject.getString(path: String): String? {
    val segments = path.split(".")
    var current: JsonElement = this
    for (seg in segments) {
        current = when (current) {
            is JsonObject -> current[seg] ?: return null
            else -> return null
        }
    }
    val primitive = current as? JsonPrimitive ?: return null
    return if (primitive.isString) primitive.content else primitive.content
}

private fun JsonObject.getFirstString(vararg keys: String): String? {
    for (key in keys) {
        val value = this[key] ?: continue
        val text = value.asStringOrNumber()
        if (!text.isNullOrBlank()) return text
    }
    return null
}

private fun JsonElement.asStringOrNumber(): String? {
    return when (this) {
        is JsonPrimitive -> this.content
        is JsonObject -> this["value"]?.asStringOrNumber()
        else -> null
    }
}

private fun JsonObject.getAssetUrl(assetsById: Map<String, JsonObject>, keys: List<String>): String? {
    val assetId = keys.asSequence()
        .mapNotNull { key ->
            val value = this[key] ?: return@mapNotNull null
            value.asAssetLinkId()
        }
        .firstOrNull()
        ?: return null

    val asset = assetsById[assetId] ?: return null
    val url = asset.getString("fields.file.url") ?: return null
    return if (url.startsWith("//")) "https:$url" else url
}

private fun JsonElement.asAssetLinkId(): String? {
    val obj = this as? JsonObject ?: return null
    val sys = obj["sys"] as? JsonObject ?: return null
    val linkType = (sys["linkType"] as? JsonPrimitive)?.content
    if (linkType != "Asset") return null
    return (sys["id"] as? JsonPrimitive)?.content
}
