import React from 'react';
import ReactDOM from 'react-dom/client';
import './style/index.scss';
import App from './Components/App';

export const WS_ENDPOINT = "ws://localhost:18080/wordmap";


const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);

