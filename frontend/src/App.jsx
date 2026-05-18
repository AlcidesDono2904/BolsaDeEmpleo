import {BrowserRouter, Routes, Route, Link} from 'react-router-dom'

import Login from './pages/Login'
import Home from "./pages/Home.jsx";
import AdminDashboard from "./pages/admin/AdminDashboard.jsx";
import MainLayout from "./components/layout/MainLayout.jsx";
import EmpresasPendientes from "./pages/admin/EmpresasPendientes.jsx";
import GenerarClave from "./pages/admin/GenerarClave.jsx";

function App() {

    return (
        <BrowserRouter>
            <MainLayout>
                <Routes>
                    <Route path="/" element={<Home/>}/>
                    <Route path="/login" element={<Login/>}/>
                    <Route path="/admin" element={<AdminDashboard/>}/>
                    <Route path="/admin/empresas-pendientes" element={<EmpresasPendientes/>}/>
                    <Route path="/admin/generarClave/:id" element={<GenerarClave/>}/>
                </Routes>
            </MainLayout>
        </BrowserRouter>)
}

export default App
