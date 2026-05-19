import {Link} from "react-router-dom";

function OferenteDashboard() {
    return (
        <div className="container mt-4">
            <h3>Oferente</h3>

            <p className="text-muted">
                Habilidades y CV.
            </p>

            <Link to="/oferente/habilidades" className="btn btn-primary me-2">
                Mis Habilidades
            </Link>
            <Link to="/oferente/cv" className="btn btn-primary me-2">
                Mi CV
            </Link>

        </div>
    );
}

export default OferenteDashboard;