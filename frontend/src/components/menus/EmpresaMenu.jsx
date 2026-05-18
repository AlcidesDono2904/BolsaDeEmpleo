import { Link } from "react-router-dom";

function EmpresaMenu() {
    return (
        <div className="navbar-nav">
            <Link className="nav-link" to="/empresa/empresa-dashboard">Dashboard</Link>
            <Link className="nav-link" to="/empresa/puestos">Mis puestos</Link>
            <Link className="nav-link" to="/empresa/publicar/puesto">Publicar puesto</Link>
        </div>
    );
}

export default EmpresaMenu;