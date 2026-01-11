import React from 'react';

export const Card = ({ children, className = '' }: { children: React.ReactNode; className?: string }) => (
  <div className={`bg-purple-800/50 rounded-xl p-6 backdrop-blur-sm border border-purple-700/50 ${className}`}>
    {children}
  </div>
);
