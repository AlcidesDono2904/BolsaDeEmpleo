import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import apiClient from "../../services/apiClient";

function Candidatos() {

    const { idPuesto } = useParams();

    const [candidatos, setCandidatos] = useState([]);

    useEffect(() => {

        apiClient.get(`/api/empresa/candidatos/${idPuesto}`)
            .then((response) => {
                setCandidatos(response.data);
            })
            .catch((error) => {
                console.error(error);
            });

    }, [idPuesto]);

    return (

        <div className="container mt-4">

            <h3>Candidatos para el puesto</h3>

            <table className="table table-striped mt-4">

                <thead>
                <tr>
                    <th>Oferente</th>
                    <th>Requisitos cumplidos</th>
                    <th>% Coincidencia</th>
                    <th></th>
                </tr>
                </thead>

                <tbody>

                {candidatos.map((c) => (

                    <tr key={c.id}>

                        <td>
                            {c.nombre} {c.apellido}
                        </td>

                        <td>
                            {c.requisitosAlcanzados}
                        </td>

                        <td>
                            {c.porcentajeCompatibilidad.toFixed(2)}%
                        </td>

                        <td>
                            <Link
                                to={`/empresa/candidatos/detalle/${c.id}`}
                                className="btn btn-outline-primary btn-sm"
                            >
                                Ver detalle
                            </Link>
                        </td>

                    </tr>

                ))}

                </tbody>

            </table>

            <Link
                to="/empresa/puestos"
                className="btn btn-secondary"
            >
                Volver
            </Link>

        </div>
    );
}

export default Candidatos;