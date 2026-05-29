import {Link} from "react-router-dom";

function AdminDashboard() {
    return (
        <div className="container mt-4">
            <h3>Administrador</h3>
            
            <p className="text-muted">
                Aprobaciones, catalogo de caracteristicas y reportes.
            </p>

            <Link to="/admin/pendientes/empresa" className="btn btn-primary me-2">
                Empresas Pendientes
            </Link>
            <Link to="/admin/pendientes/oferente" className="btn btn-primary me-2">
                Oferentes Pendientes
            </Link>
            <Link to="/admin/caracteristicas" className="btn btn-primary me-2">
                Caracteristicas
            </Link>
            <Link to="/admin/reportes-puestos" className="btn btn-primary me-2">
                Reportes
            </Link>

        </div>
    );
}

export default AdminDashboard;