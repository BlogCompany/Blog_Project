import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Index from './index0';
import LoginPage from './pages/LoginPage';
import Register from './pages/Register';
import ArticlesPage from './pages/ArticlesPage';
import Test1 from './Test1';

import SingleArticle from './pages/SingleArticle';

import UserData from './pages/UserData';
import OnePage from './pages/OnePage';

import ArticleEditor from './pages/ArticleEditor';
import './styles/App.css';
import ForgotPasswordPage from './pages/ForgotPasswordPage';
import ResetPasswordPage from './ResetPasswordPage ';
import Header from './components/Header';
import Footer from './components/Footer';
import SearchBar from './components/SearchBar';
import EmailVerificationPage from './pages/EmailVerificationPage';

const App = () => {

  // 提升状态到父组件
  const [username, setUsername] = useState('');
  const [userImage, setUserImage] = useState('');

  // 登录成功时调用这个函数更新状态
  const handleLoginSuccess = (userData) => {
    setUsername(userData.username);
    setUserImage(userData.userImage);
  };

  return (
    <Router>
      <Header username={username} userImage={userImage} /> 
    <main>
      <Routes>
        <Route path="/" element={<Index />} />
        <Route path="/login" element={<LoginPage onLoginSuccess={handleLoginSuccess} />} />
        <Route path="/register" element={<Register />} />
        <Route path="/test1" element={<Test1 />} />
        <Route path="/forgot-password" element={<ForgotPasswordPage />} />
        <Route path="/reset-password" element={<ResetPasswordPage />} />
        <Route path="/articlesPage" element={<ArticlesPage />} />

        <Route path="/singleArticle/:articleId" element={<SingleArticle />} /> 

        <Route path='/publish-article' element={<ArticleEditor />} />
        <Route path="/UserData" element={<UserData />} />
        <Route path="/onePage/:id" element={<OnePage />} /> 

        <Route path="/edit-article/:articleId" element={<ArticleEditor />} />
        <Route path="/verify-email" element={<EmailVerificationPage />} />
        {/* 你可以在这里添加更多的路由 */}
      </Routes>
    </main>s
    <Footer />
  </Router>
  );
};

export default App;
