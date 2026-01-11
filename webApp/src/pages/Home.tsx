import React from 'react';
import { Card } from '../components/Card';

const StatItem = ({ label, value, subtext }: { label: string; value: string | number; subtext?: string }) => (
  <div className="flex flex-col items-center">
    <span className="text-purple-300 text-sm mb-1">{label}</span>
    <span className="text-2xl font-bold text-white">{value}</span>
    {subtext && <span className="text-xs text-purple-400 mt-1">{subtext}</span>}
  </div>
);

export function Home() {
  return (
    <div className="space-y-6 max-w-7xl mx-auto">
      <h2 className="text-2xl font-bold text-white mb-6">歡迎使用鋒兄AI資訊系統</h2>

      {/* Hero Card */}
      <Card className="bg-gradient-to-br from-purple-800 to-purple-900 flex flex-col items-center justify-center text-center py-12 min-h-[300px]">
        <div className="bg-orange-500 w-16 h-16 rounded-lg flex items-center justify-center text-3xl font-bold text-white mb-6 shadow-lg">
          鋒
        </div>
        <h1 className="text-3xl md:text-4xl font-bold text-white mb-4">歡迎使用鋒兄AI資訊系統</h1>
        <p className="text-purple-200 text-lg mb-8 max-w-2xl">
          智能管理您的影片和圖片收藏，支援智能分類和快速搜尋
        </p>
        <p className="text-purple-400 text-sm">
          鋒兄涂哥公開資訊© 版權所有 2025 ~ 2125
        </p>
      </Card>

      {/* Tech Stack Grid */}
      <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
        <Card>
          <h3 className="text-xl font-bold text-white mb-4 flex items-center">
            <span className="mr-2">⚡</span> 前端技術
          </h3>
          <ul className="space-y-2 text-purple-200">
            <li>• Vue 3 (Vite) / React (Current)</li>
            <li>• 網頁存放於 Local</li>
            <li>• 響應式設計 + CSS</li>
          </ul>
        </Card>

        <Card>
          <h3 className="text-xl font-bold text-white mb-4 flex items-center">
            <span className="mr-2">🚀</span> 後端技術
          </h3>
          <ul className="space-y-2 text-purple-200">
            <li>• Contentful (Headless CMS)</li>
            <li>• 資料存放於 Contentful Space</li>
            <li>• Contentful Delivery API</li>
          </ul>
        </Card>
      </div>

      {/* Management Grid */}
      <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
        <Card>
          <h3 className="text-xl font-bold text-white mb-6 flex items-center">
            <span className="mr-2">📰</span> 訂閱管理
          </h3>
          <div className="grid grid-cols-3 gap-4">
            <StatItem label="項目數" value="24" />
            <StatItem label="7天提醒" value="6" subtext="最近: 2026/1/10" />
            <StatItem label="30天提醒" value="10" subtext="最近: 2026/1/10" />
          </div>
        </Card>

        <Card>
          <h3 className="text-xl font-bold text-white mb-6 flex items-center">
            <span className="mr-2">🥚</span> 食品管理
          </h3>
          <div className="grid grid-cols-3 gap-4">
            <StatItem label="項目數" value="13" />
            <StatItem label="3天提醒" value="0" subtext="最近: -" />
            <StatItem label="7天提醒" value="0" subtext="最近: -" />
          </div>
        </Card>
      </div>
    </div>
  );
}
