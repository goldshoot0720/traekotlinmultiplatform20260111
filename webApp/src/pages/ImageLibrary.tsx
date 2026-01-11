import React, { useState } from 'react';

interface ImageItem {
  id: string;
  url: string;
  title: string;
  date?: string;
}

// Mock Data
const MOCK_IMAGES: ImageItem[] = [
  { id: '1', url: 'https://placehold.co/400x400/orange/white?text=Feng', title: '0d5c4921-9c4c-46b8-82.png', date: '2026-01-12' },
  { id: '2', url: 'https://placehold.co/400x400/purple/white?text=Birthday', title: '148bfa68-c7e3-40e6.png', date: '2026-01-11' },
  { id: '3', url: 'https://placehold.co/400x400/blue/white?text=Anime', title: '1761405813-e8acecce.jpg', date: '2026-01-10' },
  { id: '4', url: 'https://placehold.co/400x400/green/white?text=Girl', title: '1761405863-3ca40781.jpeg', date: '2026-01-09' },
  { id: '5', url: 'https://placehold.co/400x400/red/white?text=Pixel', title: '1761405934-74814b15.jpeg', date: '2026-01-08' },
  { id: '6', url: 'https://placehold.co/400x400/yellow/black?text=Cat', title: '20240917_183326.png', date: '2024-09-17' },
  { id: '7', url: 'https://placehold.co/400x400/cyan/black?text=Receipt', title: '20250708_110554.jpg', date: '2025-07-08' },
  { id: '8', url: 'https://placehold.co/400x400/pink/black?text=Food', title: '20250709_110014.jpg', date: '2025-07-09' },
  { id: '9', url: 'https://placehold.co/400x400/gray/white?text=Street', title: '20250709_143748.jpg', date: '2025-07-09' },
  { id: '10', url: 'https://placehold.co/400x400/teal/white?text=Tiles', title: '20250709_171342.jpg', date: '2025-07-09' },
];

export function ImageLibrary() {
  const [images, setImages] = useState<ImageItem[]>(MOCK_IMAGES);
  const [loading, setLoading] = useState(false);

  const handleRefresh = () => {
    setLoading(true);
    // Simulate loading
    setTimeout(() => {
      setLoading(false);
    }, 1000);
  };

  return (
    <div className="space-y-6">
      <div className="flex items-center justify-between">
        <h2 className="text-2xl font-bold text-white flex items-center gap-4">
          圖片庫
          <button 
            onClick={handleRefresh}
            className="text-sm px-3 py-1 bg-purple-700 hover:bg-purple-600 rounded text-purple-200 transition-colors"
            disabled={loading}
          >
            {loading ? '載入中...' : '重新整理'}
          </button>
        </h2>
        <span className="text-purple-300 text-sm">已載入 {images.length} 張圖片</span>
      </div>

      <div className="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-4 xl:grid-cols-5 gap-4">
        {images.map((image) => (
          <div key={image.id} className="bg-purple-800/50 rounded-lg overflow-hidden group hover:bg-purple-800 transition-colors">
            <div className="aspect-square bg-purple-900 relative overflow-hidden">
              <img 
                src={image.url} 
                alt={image.title}
                className="w-full h-full object-cover group-hover:scale-110 transition-transform duration-300"
                loading="lazy"
              />
            </div>
            <div className="p-3">
              <p className="text-xs text-purple-200 truncate font-mono" title={image.title}>
                {image.title}
              </p>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
