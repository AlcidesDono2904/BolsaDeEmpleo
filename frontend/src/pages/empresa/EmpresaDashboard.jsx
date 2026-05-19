import {Link} from "react-router-dom";

function EmpresaDashboard() {
    return (
        <div className="container mt-4">
            <h3>Empresa</h3>

            <p className="text-muted">
                Puestos y candidatos.
            </p>

            <Link to="/empresa/puestos" className="btn btn-primary me-2">
                  Ver mis puestos
            </Link>
            <Link to="/empresa/publicar" className="btn btn-primary me-2">
                Publicar nuevo puesto
            </Link>

        </div>
    );
}

export default EmpresaDashboard;