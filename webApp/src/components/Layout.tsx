import React from 'react';
import type { Page } from '../App';

interface LayoutProps {
  children: React.ReactNode;
  currentPage: Page;
  onNavigate: (page: Page) => void;
}

const NavItem = ({ 
  page, 
  label, 
  active, 
  onClick, 
  icon 
}: { 
  page: Page; 
  label: string; 
  active: boolean; 
  onClick: (p: Page) => void;
  icon: React.ReactNode;
}) => (
  <button
    onClick={() => onClick(page)}
    className={`flex items-center p-3 rounded-lg transition-colors w-full ${
      active ? 'bg-purple-800 text-white' : 'text-purple-200 hover:bg-purple-800 hover:text-white'
    }`}
  >
    <span className="mr-3">{icon}</span>
    <span className="font-medium">{label}</span>
  </button>
);

const MobileNavItem = ({ 
  page, 
  label, 
  active, 
  onClick, 
  icon 
}: { 
  page: Page; 
  label: string; 
  active: boolean; 
  onClick: (p: Page) => void;
  icon: React.ReactNode;
}) => (
  <button
    onClick={() => onClick(page)}
    className={`flex flex-col items-center justify-center p-2 w-full ${
      active ? 'text-white' : 'text-purple-300'
    }`}
  >
    <div className="mb-1">{icon}</div>
    <span className="text-xs">{label}</span>
  </button>
);

// Simple SVG Icons
const HomeIcon = () => (
  <svg className="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6" /></svg>
);
const ImageIcon = () => (
  <svg className="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M4 16l4.586-4.586a2 2 0 012.828 0L16 16m-2-2l1.586-1.586a2 2 0 012.828 0L20 14m-6-6h.01M6 20h12a2 2 0 002-2V6a2 2 0 00-2-2H6a2 2 0 00-2 2v12a2 2 0 002 2z" /></svg>
);
const VideoIcon = () => (
  <svg className="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M15 10l4.553-2.276A1 1 0 0121 8.618v6.764a1 1 0 01-1.447.894L15 14M5 18h8a2 2 0 002-2V8a2 2 0 00-2-2H5a2 2 0 00-2 2v8a2 2 0 002 2z" /></svg>
);
const MusicIcon = () => (
  <svg className="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M9 19V6l12-3v13M9 19c0 1.105-1.343 2-3 2s-3-.895-3-2 1.343-2 3-2 3 .895 3 2zm12-3c0 1.105-1.343 2-3 2s-3-.895-3-2 1.343-2 3-2 3 .895 3 2zM9 10l12-3" /></svg>
);
const MenuIcon = () => (
  <svg className="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M4 6h16M4 12h16M4 18h16" /></svg>
);

export function Layout({ children, currentPage, onNavigate }: LayoutProps) {
  return (
    <div className="min-h-screen bg-purple-900 text-white flex flex-col md:flex-row font-sans">
      {/* Desktop Sidebar */}
      <aside className="hidden md:flex flex-col w-64 bg-purple-900 border-r border-purple-800 h-screen sticky top-0">
        <div className="p-6 flex items-center space-x-3">
          <div className="bg-orange-500 p-2 rounded text-white font-bold">鋒</div>
          <h1 className="text-xl font-bold">鋒兄AI系統</h1>
        </div>
        <nav className="flex-1 px-4 space-y-2">
          <NavItem page="home" label="首頁" active={currentPage === 'home'} onClick={onNavigate} icon={<HomeIcon />} />
          <NavItem page="images" label="圖片庫" active={currentPage === 'images'} onClick={onNavigate} icon={<ImageIcon />} />
          <NavItem page="videos" label="影片庫" active={currentPage === 'videos'} onClick={onNavigate} icon={<VideoIcon />} />
          <NavItem page="music" label="鋒兄音樂歌詞" active={currentPage === 'music'} onClick={onNavigate} icon={<MusicIcon />} />
          <NavItem page="bank" label="銀行速記" active={currentPage === 'bank'} onClick={onNavigate} icon={<span className="text-lg">🏦</span>} />
          <NavItem page="subscription" label="訂閱管理" active={currentPage === 'subscription'} onClick={onNavigate} icon={<span className="text-lg">📋</span>} />
          <NavItem page="food" label="食品管理" active={currentPage === 'food'} onClick={onNavigate} icon={<span className="text-lg">🍎</span>} />
          <NavItem page="settings" label="系統設定" active={currentPage === 'settings'} onClick={onNavigate} icon={<span className="text-lg">⚙️</span>} />
        </nav>
      </aside>

      {/* Mobile Header */}
      <header className="md:hidden bg-purple-900 p-4 flex items-center justify-between sticky top-0 z-10 border-b border-purple-800">
        <div className="flex items-center space-x-2">
          <div className="bg-orange-500 p-1.5 rounded text-white font-bold text-sm">鋒</div>
          <h1 className="text-lg font-bold">鋒兄AI系統</h1>
        </div>
        <button className="text-white">
          <MenuIcon />
        </button>
      </header>

      {/* Main Content */}
      <main className="flex-1 p-4 md:p-8 overflow-y-auto bg-purple-900/50 pb-20 md:pb-8">
        {children}
      </main>

      {/* Mobile Bottom Navigation */}
      <nav className="md:hidden fixed bottom-0 left-0 right-0 bg-purple-900 border-t border-purple-800 flex justify-around p-2 z-20 safe-area-bottom">
        <MobileNavItem page="home" label="首頁" active={currentPage === 'home'} onClick={onNavigate} icon={<HomeIcon />} />
        <MobileNavItem page="images" label="圖片" active={currentPage === 'images'} onClick={onNavigate} icon={<ImageIcon />} />
        <MobileNavItem page="videos" label="影片" active={currentPage === 'videos'} onClick={onNavigate} icon={<VideoIcon />} />
        <MobileNavItem page="music" label="音樂" active={currentPage === 'music'} onClick={onNavigate} icon={<MusicIcon />} />
      </nav>
    </div>
  );
}
