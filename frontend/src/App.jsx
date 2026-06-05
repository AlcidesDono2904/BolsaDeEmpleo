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


function App() {
// TODO: Add private routes and role-based access control
    return (
        <BrowserRouter>
            <MainLayout>
                <Routes>
                    <Route path="/" element={<Home/>}/>
                    <Route path="/login" element={<Login/>}/>
                    <Route path="/admin" element={<AdminDashboard/>}/>
                    <Route path="/admin/pendientes/:type" element={<EmpresasPendientes/>}/>
                    <Route path="/admin/generarClave/:id" element={<GenerarClave/>}/>
                    <Route path="/admin/caracteristicas" element={<Caracteristicas/>}/>
                    <Route path="/admin/reportes-puestos" element={<Reportes/>}/>
                    <Route path="/empresa" element={<EmpresaDashboard/>}/>
                    <Route path="/empresa/puestos" element={<Puestos/>} />
                    <Route path="/empresa/publicar-puesto" element={<PublicarPuesto />}/>
                    <Route path="/empresa/candidatos/:idPuesto" element={<Candidatos />} />
                    <Route path="/empresa/candidatos/detalle/:idOferente" element={<DetalleCandidato />}/>
                    <Route path="/oferente" element={<OferenteDashboard/>}/>
                    <Route path="oferente/habilidades" element={<Habilidades/>}/>
                    <Route path="oferente/registro-oferente" element={<RegistroOferente />}/>
                    <Route path="/empresa/registro-empresa" element={<RegistroEmpresa />}/>
                    <Route path="*" element={<h1>404 Not Found</h1>} />
                </Routes>
            </MainLayout>
        </BrowserRouter>)
}

export default App
