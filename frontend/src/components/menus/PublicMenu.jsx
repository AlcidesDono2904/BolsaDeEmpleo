import {Link} from "react-router-dom";

function PublicMenu() {
    return (
        <div className="navbar-nav">
            <Link className="nav-link" to="/puestos/buscar">Buscar puestos</Link>
            <Link className="nav-link" to="/empresa/registro-empresa">Registro Empresa</Link>
            <Link className="nav-link" to="/oferente/registro-oferente">Registro Oferente</Link>
        </div>
    );
}

export default PublicMenu;