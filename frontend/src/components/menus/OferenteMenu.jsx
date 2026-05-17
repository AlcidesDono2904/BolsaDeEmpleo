import {Link} from "react-router-dom";

function OferenteMenu() {
    return (
        <div className="navbar-nav">
            <Link className="nav-link" to="/oferente/oferente-dashboard">Dashboard</Link>
            <Link className="nav-link" to="/oferente/habilidades">Mis habilidades</Link>
            <Link className="nav-link" to="/oferente/cv">Mi CV</Link>
        </div>
    );
}

export default OferenteMenu;