import {Link} from "react-router-dom";

function AdminMenu() {
    return (
        <div className="navbar-nav">
            <Link className="nav-link" to="/admin/admin-dashboard">Dashboard</Link>
            <Link className="nav-link" to="/admin/empresas-pendientes">Empresas pendientes</Link>
            <Link className="nav-link" to="/admin/oferentes-pendientes">Oferentes pendientes</Link>
            <Link className="nav-link" to="/admin/caracteristicas">Caracteristicas</Link>
            <Link className="nav-link" to="/admin/reportes-puestos">Reportes</Link>
        </div>);
}

export default AdminMenu;