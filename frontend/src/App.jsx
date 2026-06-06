import {BrowserRouter, Routes, Route, Link} from 'react-router-dom'

import Login from './pages/Login'
import Home from "./pages/Home.jsx";
import AdminDashboard from "./pages/admin/AdminDashboard.jsx";
import MainLayout from "./components/layout/MainLayout.jsx";
import EmpresasPendientes from "./pages/admin/EmpresasPendientes.jsx";
import GenerarClave from "./pages/admin/GenerarClave.jsx";
import EmpresaDashboard from "./pages/empresa/EmpresaDashboard.jsx";
import Puestos from "./pages/empresa/Puestos.jsx";
import PublicarPuesto from "./pages/empresa/PublicarPuesto";
import DetalleCandidato from "./pages/empresa/DetalleCandidato";
import OferenteDashboard from "./pages/oferente/OferenteDashboard.jsx";
import Caracteristicas from "./pages/admin/Caracteristicas.jsx";
import Reportes from "./pages/admin/Reportes.jsx";
import Candidatos from "./pages/empresa/Candidatos";
import Habilidades from "./pages/oferente/Habilidades";
import RegistroOferente from "./pages/RegistroOferente";
import RegistroEmpresa from "./pages/RegistroEmpresa";
import ProtectedRoute from "./components/ProtectedRoute";


function App() {
    return (
        <BrowserRouter>
            <MainLayout>
                <Routes>
                    {/* Rutas públicas */}
                    <Route path="/" element={<Home/>}/>
                    <Route path="/login" element={<Login/>}/>
                    <Route path="/oferente/registro-oferente" element={<RegistroOferente />}/>
                    <Route path="/empresa/registro-empresa" element={<RegistroEmpresa />}/>

                    {/* Rutas ADMIN */}
                    <Route path="/admin" element={<ProtectedRoute element={<AdminDashboard/>} allowedRoles="ADMIN" />}/>
                    <Route path="/admin/pendientes/:type" element={<ProtectedRoute element={<EmpresasPendientes/>} allowedRoles="ADMIN" />}/>
                    <Route path="/admin/generarClave/:id" element={<ProtectedRoute element={<GenerarClave/>} allowedRoles="ADMIN" />}/>
                    <Route path="/admin/caracteristicas" element={<ProtectedRoute element={<Caracteristicas/>} allowedRoles="ADMIN" />}/>
                    <Route path="/admin/reportes-puestos" element={<ProtectedRoute element={<Reportes/>} allowedRoles="ADMIN" />}/>

                    {/* Rutas EMPRESA */}
                    <Route path="/empresa" element={<ProtectedRoute element={<EmpresaDashboard/>} allowedRoles="EMPRESA" />}/>
                    <Route path="/empresa/puestos" element={<ProtectedRoute element={<Puestos/>} allowedRoles="EMPRESA" />} />
                    <Route path="/empresa/publicar-puesto" element={<ProtectedRoute element={<PublicarPuesto />} allowedRoles="EMPRESA" />}/>
                    <Route path="/empresa/candidatos/:idPuesto" element={<ProtectedRoute element={<Candidatos />} allowedRoles="EMPRESA" />} />
                    <Route path="/empresa/candidatos/detalle/:idOferente" element={<ProtectedRoute element={<DetalleCandidato />} allowedRoles="EMPRESA" />}/>

                    {/* Rutas OFERENTE */}
                    <Route path="/oferente" element={<ProtectedRoute element={<OferenteDashboard/>} allowedRoles="OFERENTE" />}/>
                    <Route path="/oferente/habilidades" element={<ProtectedRoute element={<Habilidades/>} allowedRoles="OFERENTE" />}/>

                    {/* 404 */}
                    <Route path="*" element={<h1>404 Not Found</h1>} />
                </Routes>
            </MainLayout>
        </BrowserRouter>)
}

export default App
