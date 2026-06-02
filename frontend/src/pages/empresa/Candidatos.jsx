import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
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
                    <th>Nombre</th>
                    <th>Compatibilidad</th>
                    <th>Requisitos alcanzados</th>
                </tr>
                </thead>

                <tbody>

                {candidatos.map((c, index) => (

                    <tr key={index}>

                        <td>
                            {c.nombre} {c.apellido}
                        </td>

                        <td>
                            {c.porcentajeCompatibilidad}%
                        </td>

                        <td>
                            {c.requisitosAlcanzados}
                        </td>

                    </tr>

                ))}

                </tbody>

            </table>

        </div>
    );
}

export default Candidatos;