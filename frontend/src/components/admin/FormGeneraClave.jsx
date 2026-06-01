import {useState} from "react";
import apiClient from "../../services/apiClient.js";
import {Link, useNavigate} from "react-router-dom";

function FormGeneraClave({id, userType = "empresa"}) {
    const navigate = useNavigate();
    const [password, setPassword] = useState("");

    const getEndpoint = () => userType === "oferente"
        ? `/api/admin/oferentes-pendientes`
        : `/api/admin/empresas-pendientes`;

    const getRedirectPath = () => `/admin/pendientes/${userType}`;

    function handleSubmit(event) {
        if (password.trim() === "") {
            alert("La clave no puede estar vacía");
            return;
        }

        apiClient.post(getEndpoint(), {id: id, password: password})
            .then(response => {
                alert(`Clave generada para el usuario ${id}`);
                navigate(getRedirectPath());
            })
            .catch(error => {
                console.error("Error al generar la clave:", error);
                alert("Ocurrió un error al generar la clave. Por favor, inténtalo de nuevo.");
            });
    }

    return (
        <div>
            <input type="hidden" name="id"/>

            <div className="mb-3">
                <label htmlFor="password" className="form-label">Clave:</label>
                <input type="password" className="form-control" id="password"
                       name="passwordHash" required
                       value={password}
                       onChange={(e) => setPassword(e.target.value)}
                />
                <small className="form-text text-muted">Ingresa una clave</small>
            </div>

            <div className="d-grid gap-2 d-md-flex justify-content-md-end">
                <Link to={getRedirectPath()} className="btn btn-secondary">Cancelar</Link>
                <button onClick={() => handleSubmit()} type="button" className="btn btn-success">Aprobar y generar clave</button>
            </div>
        </div>
    );
}

export default FormGeneraClave;
