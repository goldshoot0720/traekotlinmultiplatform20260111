import React, { useState } from 'react';
import { Card } from '../components/Card';

interface FoodItem {
  id: string;
  name: string;
  amount: number;
  price: number;
  currency: string;
  expiryDate: string;
  imageUrl: string;
}

// Mock Data
const MOCK_FOODS: FoodItem[] = [
  {
    id: '1',
    name: '【張君雅】五香海苔休閒丸子',
    amount: 3,
    price: 0,
    currency: '$',
    expiryDate: '2026-01-05',
    imageUrl: 'https://placehold.co/100x100/green/white?text=Food1'
  },
  {
    id: '2',
    name: '【張君雅】日式串燒休閒丸子',
    amount: 6,
    price: 0,
    currency: '$',
    expiryDate: '2026-01-06',
    imageUrl: 'https://placehold.co/100x100/orange/white?text=Food2'
  },
  {
    id: '3',
    name: '樂事',
    amount: 5,
    price: 0,
    currency: '$',
    expiryDate: '2026-01-21',
    imageUrl: 'https://placehold.co/100x100/yellow/black?text=Lays'
  },
  {
    id: '4',
    name: '萬丹保久乳',
    amount: 12,
    price: 0,
    currency: '$',
    expiryDate: '2026-02-25',
    imageUrl: 'https://placehold.co/100x100/white/green?text=Milk'
  },
  {
    id: '5',
    name: '【義美】純豬肉鬆',
    amount: 1,
    price: 0,
    currency: '$',
    expiryDate: '2026-04-13',
    imageUrl: 'https://placehold.co/100x100/red/white?text=Meat'
  },
  {
    id: '6',
    name: '【義美】煎餅 (花生) (杏仁)',
    amount: 7,
    price: 0,
    currency: '$',
    expiryDate: '2026-05-18',
    imageUrl: 'https://placehold.co/100x100/brown/white?text=Cookie'
  }
];

export function Food() {
  const [foods, setFoods] = useState<FoodItem[]>(MOCK_FOODS);
  
  // Form State
  const [name, setName] = useState('');
  const [amount, setAmount] = useState('1');
  const [price, setPrice] = useState('0');
  const [currency, setCurrency] = useState('NT$');
  const [expiryDate, setExpiryDate] = useState('2026-01-11');
  const [shop, setShop] = useState('');
  const [hash, setHash] = useState('');

  const handleDelete = (id: string) => {
    if (window.confirm('確定要刪除嗎？')) {
      setFoods(prev => prev.filter(item => item.id !== id));
    }
  };

  const handleSync = () => {
    alert('同步功能尚未實作');
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    const newItem: FoodItem = {
      id: Date.now().toString(),
      name,
      amount: Number(amount) || 1,
      price: Number(price) || 0,
      currency: currency === 'NT$' ? '$' : currency,
      expiryDate,
      imageUrl: 'https://placehold.co/100x100/gray/white?text=New'
    };
    setFoods([newItem, ...foods]);
    // Reset form (partial)
    setName('');
    setAmount('1');
    setPrice('0');
    setHash('');
  };

  return (
    <div className="flex flex-col lg:flex-row gap-6 h-full">
      {/* Left Column: List */}
      <div className="flex-1 space-y-4 overflow-y-auto">
        <div className="flex items-center gap-4 mb-4">
          <h2 className="text-2xl font-bold text-white">食品清單</h2>
          <button 
            onClick={handleSync}
            className="flex items-center gap-2 px-3 py-1 bg-purple-700/50 hover:bg-purple-600 rounded text-purple-200 text-sm transition-colors"
          >
            🔄 同步
          </button>
        </div>

        <div className="space-y-4">
          {foods.map((item) => (
            <div key={item.id} className="bg-purple-800/50 rounded-lg p-3 border border-purple-700/50 flex items-center gap-4 relative group hover:border-purple-500 transition-colors">
              <img src={item.imageUrl} alt={item.name} className="w-16 h-16 rounded object-cover bg-white" />
              <div className="flex-1">
                <h3 className="font-bold text-white mb-1">{item.name}</h3>
                <div className="text-sm text-purple-200">
                  數量: {item.amount} | 價格: {item.currency}{item.price}
                </div>
                <div className="text-sm text-orange-300">
                  到期日: {item.expiryDate}
                </div>
              </div>
              <button 
                onClick={() => handleDelete(item.id)}
                className="text-gray-400 hover:text-red-400 p-2 opacity-60 group-hover:opacity-100 transition-opacity"
              >
                🗑️
              </button>
            </div>
          ))}
        </div>
      </div>

      {/* Right Column: Add Form */}
      <div className="w-full lg:w-96 shrink-0">
        <Card className="sticky top-4">
          <h3 className="text-xl font-bold text-white mb-6">新增食品</h3>
          <form onSubmit={handleSubmit} className="space-y-4">
            <div>
              <label className="block text-sm text-purple-200 mb-1">名稱 (Name)</label>
              <input 
                type="text" 
                value={name}
                onChange={(e) => setName(e.target.value)}
                className="w-full bg-purple-900/50 border border-purple-600 rounded p-2 text-white focus:outline-none focus:border-purple-400"
              />
            </div>

            <div className="flex gap-4">
              <div className="flex-1">
                <label className="block text-sm text-purple-200 mb-1">數量 (Amount)</label>
                <div className="flex">
                  <input 
                    type="number" 
                    value={amount}
                    onChange={(e) => setAmount(e.target.value)}
                    className="w-full bg-purple-900/50 border border-purple-600 rounded-l p-2 text-white focus:outline-none focus:border-purple-400"
                  />
                  <div className="flex flex-col border-y border-r border-purple-600 rounded-r bg-purple-800">
                    <button type="button" onClick={() => setAmount(String(Number(amount) + 1))} className="px-2 text-xs hover:bg-purple-700 h-1/2">▲</button>
                    <button type="button" onClick={() => setAmount(String(Math.max(1, Number(amount) - 1)))} className="px-2 text-xs hover:bg-purple-700 h-1/2">▼</button>
                  </div>
                </div>
              </div>
              
              <div className="flex-1">
                <label className="block text-sm text-purple-200 mb-1">價格 (Price)</label>
                <div className="flex">
                  <div className="bg-purple-800 border border-purple-600 rounded-l p-2 text-sm text-purple-200">
                    {currency}
                  </div>
                  <input 
                    type="number" 
                    value={price}
                    onChange={(e) => setPrice(e.target.value)}
                    className="w-full bg-purple-900/50 border border-purple-600 border-l-0 rounded-r p-2 text-white focus:outline-none focus:border-purple-400"
                  />
                </div>
              </div>
            </div>

            <div>
              <label className="block text-sm text-purple-200 mb-1">到期日 (To Date)</label>
              <input 
                type="date" 
                value={expiryDate}
                onChange={(e) => setExpiryDate(e.target.value)}
                className="w-full bg-purple-900/50 border border-purple-600 rounded p-2 text-white focus:outline-none focus:border-purple-400"
              />
            </div>

            <div>
              <label className="block text-sm text-purple-200 mb-1">商店 (Shop)</label>
              <input 
                type="text" 
                value={shop}
                onChange={(e) => setShop(e.target.value)}
                className="w-full bg-purple-900/50 border border-purple-600 rounded p-2 text-white focus:outline-none focus:border-purple-400"
              />
            </div>

            <div>
              <label className="block text-sm text-purple-200 mb-1">圖片 (Photo)</label>
              <div className="w-full h-32 bg-purple-900/50 border border-purple-600 rounded p-2 flex items-center justify-center text-purple-400 cursor-pointer hover:bg-purple-800/50 transition-colors">
                選擇圖片...
              </div>
            </div>

            <div>
              <label className="block text-sm text-purple-200 mb-1">Hash:</label>
              <input 
                type="text" 
                value={hash}
                readOnly
                className="w-32 bg-purple-900/50 border border-purple-600 rounded p-1 text-xs text-gray-400"
              />
            </div>

            <div className="w-full bg-gray-700 h-1 rounded-full mt-4 overflow-hidden">
              <div className="bg-green-500 h-full w-3/4"></div>
            </div>
          </form>
        </Card>
      </div>
    </div>
  );
}
