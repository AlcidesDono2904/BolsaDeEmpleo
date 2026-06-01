import {Link} from "react-router-dom";

function AdminMenu() {
    return (
        <div className="navbar-nav">
            <Link className="nav-link" to="/admin">Dashboard</Link>
            <Link className="nav-link" to="/admin/pendientes/empresa">Empresas pendientes</Link>
            <Link className="nav-link" to="/admin/pendientes/oferente">Oferentes pendientes</Link>
            <Link className="nav-link" to="/admin/caracteristicas">Caracteristicas</Link>
            <Link className="nav-link" to="/admin/reportes-puestos">Reportes</Link>
        </div>);
}

export default AdminMenu;