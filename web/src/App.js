import { BrowserRouter, Routes, Route } from 'react-router-dom';
import List from './pages/List';
import Register from './pages/Register';
import Login from './pages/Login';
import LoginContextProvider from './context/LoginContextProviderProvider';

function App() {
  return (
    <BrowserRouter>
      <LoginContextProvider>
        <Routes>
          <Route path="/" element={<List />} />
          <Route path="login" element={<Login />} />
          <Route path="register" element={<Register />} />
        </Routes>
      </LoginContextProvider>
    </BrowserRouter>
  );
}

export default App;
