import {BrowserRouter, Routes, Route, Link} from 'react-router-dom'

import Login from './pages/Login'
import Home from "./pages/Home.jsx";
import AdminDashboard from "./pages/AdminDashboard.jsx";
import MainLayout from "./components/layout/MainLayout.jsx";

function App() {

    return (
        <BrowserRouter>
            <MainLayout>
                <Routes>
                    <Route path="/" element={<Home/>}/>
                    <Route path="/login" element={<Login/>}/>
                    <Route path="/admin" element={<AdminDashboard/>}/>
                </Routes>
            </MainLayout>
        </BrowserRouter>)
}

export default App
