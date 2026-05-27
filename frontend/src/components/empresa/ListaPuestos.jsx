import { Link } from "react-router-dom";
import apiClient from "../../services/apiClient";

function ListaPuestos({ puestos }) {

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

                        <button
                            className="btn btn-danger btn-sm"
                            onClick={() => {
                                apiClient.put(`/api/empresa/puestos/desactivar/${p.id}`)
                                    .then(() => {
                                        window.location.reload();
                                    })
                                    .catch((error) => {
                                        console.error(error);
                                    });
                            }}
                        >
                            Desactivar
                        </button>

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