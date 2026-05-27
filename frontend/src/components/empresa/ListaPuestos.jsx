import { Link } from "react-router-dom";
import apiClient from "../../services/apiClient";

function ListaPuestos({ puestos = [], recargar }) {

    function desactivar(id) {

        apiClient.put(`/api/empresa/desactivar/${id}`)
            .then(() => {
                recargar();
            })
            .catch((error) => {
                console.error(error);
            });
    }

    return (

        <table className="table table-striped">

            <thead>
            <tr>
                <th>ID</th>
                <th>Descripción</th>
                <th>Salario</th>
                <th>Activo</th>
                <th>Acciones</th>
            </tr>
            </thead>

            <tbody>

            {puestos.map((p) => (

                <tr key={p.id}>

                    <td>{p.id}</td>

                    <td>{p.descripcion}</td>

                    <td>{p.salarioUsd}</td>

                    <td>
                        {p.activo ? "Sí" : "No"}
                    </td>

                    <td>

                        {p.activo && (
                            <button
                                className="btn btn-danger btn-sm me-2"
                                onClick={() => desactivar(p.id)}
                            >
                                Desactivar
                            </button>
                        )}

                        <Link
                            to={`/empresa/candidatos/${p.id}`}
                            className="btn btn-primary btn-sm"
                        >
                            Buscar candidatos
                        </Link>

                    </td>

                </tr>

            ))}

            </tbody>

        </table>
    );
}

export default ListaPuestos;