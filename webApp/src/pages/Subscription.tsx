import React, { useState } from 'react';
import { Card } from '../components/Card';

interface Subscription {
  id: string;
  name: string;
  price: number;
  currency: string;
  account: string;
  url: string;
  nextPaymentDate: string;
}

// Mock Data
const MOCK_SUBSCRIPTIONS: Subscription[] = [
  {
    id: '1',
    name: '天晟/處方箋/心臟內科',
    price: 0,
    currency: '$',
    account: '帳號:',
    url: 'https://www.tcmg.com.tw/index.php/main/schedule_time?id=18',
    nextPaymentDate: '2027-02-06 16:00'
  },
  {
    id: '2',
    name: '天晟/處方箋/身心科',
    price: 0,
    currency: '$',
    account: '帳號:',
    url: 'https://www.tcmg.com.tw/index.php/main/schedule_time?id=14',
    nextPaymentDate: '2027-02-06 16:00'
  },
  {
    id: '3',
    name: 'Perplexity Pro/goldshoot0720',
    price: 660,
    currency: '$',
    account: '帳號:',
    url: 'https://www.perplexity.ai/',
    nextPaymentDate: '2026-11-06 16:00'
  },
  {
    id: '4',
    name: 'Cloudflare Domain',
    price: 350,
    currency: '$',
    account: '帳號:',
    url: 'https://www.tpe12thmayor2038from2025.com/',
    nextPaymentDate: '2026-09-15 16:00'
  }
];

export function Subscription() {
  const [subscriptions, setSubscriptions] = useState<Subscription[]>(MOCK_SUBSCRIPTIONS);
  
  // Form State
  const [name, setName] = useState('');
  const [price, setPrice] = useState('');
  const [currency, setCurrency] = useState('NT$');
  const [nextDate, setNextDate] = useState('2026-01-11');
  const [site, setSite] = useState('');
  const [account, setAccount] = useState('');
  const [note, setNote] = useState('');

  const handleDelete = (id: string) => {
    if (window.confirm('確定要刪除嗎？')) {
      setSubscriptions(prev => prev.filter(sub => sub.id !== id));
    }
  };

  const handleSync = () => {
    alert('同步功能尚未實作');
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    const newSub: Subscription = {
      id: Date.now().toString(),
      name,
      price: Number(price) || 0,
      currency: '$',
      account: account ? `帳號: ${account}` : '帳號:',
      url: site,
      nextPaymentDate: `${nextDate} 16:00`
    };
    setSubscriptions([newSub, ...subscriptions]);
    // Reset form
    setName('');
    setPrice('');
    setSite('');
    setAccount('');
    setNote('');
  };

  return (
    <div className="flex flex-col lg:flex-row gap-6 h-full">
      {/* Left Column: List */}
      <div className="flex-1 space-y-4 overflow-y-auto">
        <div className="flex items-center gap-4 mb-4">
          <h2 className="text-2xl font-bold text-white">訂閱清單</h2>
          <button 
            onClick={handleSync}
            className="flex items-center gap-2 px-3 py-1 bg-purple-700/50 hover:bg-purple-600 rounded text-purple-200 text-sm transition-colors"
          >
            🔄 同步
          </button>
        </div>

        <div className="space-y-4">
          {subscriptions.map((sub) => (
            <div key={sub.id} className="bg-purple-800/50 rounded-lg p-4 border border-purple-700/50 relative group hover:border-purple-500 transition-colors">
              <button 
                onClick={() => handleDelete(sub.id)}
                className="absolute top-4 right-4 text-gray-400 hover:text-red-400 p-1 opacity-60 group-hover:opacity-100 transition-opacity"
              >
                🗑️
              </button>
              
              <h3 className="text-lg font-bold text-white mb-1">{sub.name}</h3>
              <div className="text-sm text-purple-200 mb-1">
                價格: {sub.currency}{sub.price} | {sub.account}
              </div>
              <a 
                href={sub.url} 
                target="_blank" 
                rel="noreferrer"
                className="text-xs text-blue-300 hover:underline block mb-1 truncate max-w-[90%]"
              >
                {sub.url}
              </a>
              <div className="text-xs text-orange-300">
                下次扣款: {sub.nextPaymentDate}
              </div>
            </div>
          ))}
        </div>
      </div>

      {/* Right Column: Add Form */}
      <div className="w-full lg:w-96 shrink-0">
        <Card className="sticky top-4">
          <h3 className="text-xl font-bold text-white mb-6">新增訂閱</h3>
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

            <div>
              <label className="block text-sm text-purple-200 mb-1">價格 (Price)</label>
              <div className="flex gap-2">
                <select 
                  value={currency}
                  onChange={(e) => setCurrency(e.target.value)}
                  className="bg-purple-900/50 border border-purple-600 rounded p-2 text-white focus:outline-none focus:border-purple-400 w-24"
                >
                  <option value="NT$">NT$</option>
                  <option value="$">$</option>
                </select>
                <input 
                  type="number" 
                  value={price}
                  onChange={(e) => setPrice(e.target.value)}
                  className="flex-1 bg-purple-900/50 border border-purple-600 rounded p-2 text-white focus:outline-none focus:border-purple-400"
                />
              </div>
            </div>

            <div>
              <label className="block text-sm text-purple-200 mb-1">下次扣款日 (Next Date)</label>
              <input 
                type="date" 
                value={nextDate}
                onChange={(e) => setNextDate(e.target.value)}
                className="w-full bg-purple-900/50 border border-purple-600 rounded p-2 text-white focus:outline-none focus:border-purple-400"
              />
            </div>

            <div>
              <label className="block text-sm text-purple-200 mb-1">網站 (Site)</label>
              <input 
                type="url" 
                value={site}
                onChange={(e) => setSite(e.target.value)}
                className="w-full bg-purple-900/50 border border-purple-600 rounded p-2 text-white focus:outline-none focus:border-purple-400"
              />
            </div>

            <div>
              <label className="block text-sm text-purple-200 mb-1">帳號 (Account)</label>
              <input 
                type="text" 
                value={account}
                onChange={(e) => setAccount(e.target.value)}
                className="w-full bg-purple-900/50 border border-purple-600 rounded p-2 text-white focus:outline-none focus:border-purple-400"
              />
            </div>

            <div>
              <label className="block text-sm text-purple-200 mb-1">備註 (Note)</label>
              <textarea 
                value={note}
                onChange={(e) => setNote(e.target.value)}
                rows={3}
                className="w-full bg-purple-900/50 border border-purple-600 rounded p-2 text-white focus:outline-none focus:border-purple-400"
              />
            </div>

            <button 
              type="submit"
              className="w-full bg-green-500 hover:bg-green-600 text-white font-bold py-2 px-4 rounded transition-colors mt-4"
            >
              加入清單
            </button>

            <div className="text-center text-xs text-purple-300 mt-2">
              從 Contentful 載入 {subscriptions.length} 筆訂閱
            </div>
          </form>
        </Card>
      </div>
    </div>
  );
}
