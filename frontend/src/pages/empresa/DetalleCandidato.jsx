import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import apiClient from "../../services/apiClient";


function DetalleCandidato() {

    const { idOferente } = useParams();

    const [oferente, setOferente] = useState(null);

    useEffect(() => {

        apiClient.get(
            `/api/empresa/candidatos/detalle/${idOferente}`
        )
            .then((response) => {
                console.log(JSON.stringify(response.data, null, 2));
                setOferente(response.data);
            })
            .catch((error) => {
                console.error(error);
            });

    }, [idOferente]);

    if (!oferente) {
        return <div className="container mt-4">Cargando...</div>;
    }

    return (

        <div className="container mt-4">

            <h3>Detalle de oferente</h3>

            <div className="card p-3 mb-4">

                <strong>
                    {oferente.nombre} {oferente.apellido}
                </strong>

                <br />

                <b>Identificación:</b> {oferente.identificacion}
                <br />

                <b>Email:</b> {oferente.correo}
                <br />

                <b>Teléfono:</b> {oferente.telefono}
                <br />

                <b>Residencia:</b> {oferente.residencia}

            </div>

            <h5>Habilidades</h5>

            <table className="table">

                <thead>
                <tr>
                    <th>Habilidad</th>
                    <th>Nivel</th>
                </tr>
                </thead>

                <tbody>

                {oferente.habilidads?.map((h, index) => (

                    <tr key={index}>

                        <td>
                            {h.idCaracteristica?.nombre}
                        </td>

                        <td>
                            {h.nivel}
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

export default DetalleCandidato;