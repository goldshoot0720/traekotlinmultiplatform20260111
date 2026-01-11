import React, { useState } from 'react';
import { Layout } from './components/Layout';
import { Home } from './pages/Home';
import { ImageLibrary } from './pages/ImageLibrary';
import { Subscription } from './pages/Subscription';
import { Food } from './pages/Food';
import { Settings } from './pages/Settings';

export type Page = 'home' | 'images' | 'videos' | 'music' | 'bank' | 'subscription' | 'food' | 'settings';

export default function App() {
  const [currentPage, setCurrentPage] = useState<Page>('home');

  const renderPage = () => {
    switch (currentPage) {
      case 'home':
        return <Home />;
      case 'images':
        return <ImageLibrary />;
      case 'subscription':
        return <Subscription />;
      case 'food':
        return <Food />;
      case 'settings':
        return <Settings />;
      default:
        return (
          <div className="flex flex-col items-center justify-center h-64 text-purple-300">
            <div className="text-4xl mb-4">🚧</div>
            <h2 className="text-xl font-bold mb-2">頁面開發中</h2>
            <p>Page "{currentPage}" is under construction</p>
          </div>
        );
    }
  };

  return (
    <Layout currentPage={currentPage} onNavigate={setCurrentPage}>
      {renderPage()}
    </Layout>
  );
}
