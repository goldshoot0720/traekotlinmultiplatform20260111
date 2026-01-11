import React, { useState } from 'react';
import { Card } from '../components/Card';

export function Settings() {
  const [spaceId, setSpaceId] = useState('2gk8lkqqmfmn');
  const [accessToken, setAccessToken] = useState('LGPePocQZX9WMGbMbPLGZxxM1U1JhWNFM-szsO7VIGk');
  const [managementToken, setManagementToken] = useState('●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●');

  const handleCopy = (text: string) => {
    navigator.clipboard.writeText(text);
    // Could add a toast notification here
  };

  return (
    <div className="max-w-4xl mx-auto space-y-6">
      <h2 className="text-2xl font-bold text-white mb-6">系統設定</h2>

      <Card>
        <h3 className="text-xl font-bold text-white mb-4">Contentful 設定</h3>
        <p className="text-purple-300 text-sm mb-6">請設定您的 Contentful Space ID 與 Delivery Access Token。</p>

        <div className="space-y-6">
          <div>
            <label className="block text-sm font-bold text-purple-200 mb-2">Space ID</label>
            <div className="flex gap-2">
              <input 
                type="text" 
                value={spaceId}
                onChange={(e) => setSpaceId(e.target.value)}
                className="flex-1 bg-purple-900/50 border border-purple-600 rounded p-2 text-white focus:outline-none focus:border-purple-400 font-mono"
              />
              <button 
                onClick={() => handleCopy(spaceId)}
                className="px-4 py-2 bg-purple-700 hover:bg-purple-600 rounded text-purple-200 transition-colors"
              >
                複製
              </button>
            </div>
          </div>

          <div>
            <label className="block text-sm font-bold text-purple-200 mb-2">Access Token (Delivery API)</label>
            <div className="flex gap-2">
              <input 
                type="text" 
                value={accessToken}
                onChange={(e) => setAccessToken(e.target.value)}
                className="flex-1 bg-purple-900/50 border border-purple-600 rounded p-2 text-white focus:outline-none focus:border-purple-400 font-mono"
              />
              <button 
                onClick={() => handleCopy(accessToken)}
                className="px-4 py-2 bg-purple-700 hover:bg-purple-600 rounded text-purple-200 transition-colors"
              >
                複製
              </button>
            </div>
          </div>

          <div>
            <label className="block text-sm font-bold text-purple-200 mb-2">Management Token (CMA - 用於寫入)</label>
            <div className="flex gap-2">
              <input 
                type="text" 
                value={managementToken}
                onChange={(e) => setManagementToken(e.target.value)}
                className="flex-1 bg-purple-900/50 border border-purple-600 rounded p-2 text-white focus:outline-none focus:border-purple-400 font-mono"
              />
              <button 
                onClick={() => handleCopy(managementToken)}
                className="px-4 py-2 bg-purple-700 hover:bg-purple-600 rounded text-purple-200 transition-colors"
              >
                複製
              </button>
            </div>
          </div>

          <div className="flex justify-end gap-4 mt-8 pt-4 border-t border-purple-700/50">
            <button className="px-6 py-2 bg-orange-500 hover:bg-orange-600 text-white font-bold rounded transition-colors">
              儲存設定
            </button>
            <button className="px-6 py-2 bg-white text-purple-900 hover:bg-gray-100 font-bold rounded transition-colors">
              測試 Token 權限
            </button>
          </div>
        </div>
      </Card>
    </div>
  );
}
