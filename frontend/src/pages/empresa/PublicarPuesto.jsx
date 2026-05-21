import { useEffect, useState } from "react";
import apiClient from "../../services/apiClient";
import FormPublicarPuesto
    from "../../components/empresa/FormPublicarPuesto";

function PublicarPuesto() {

    const [caracteristicas, setCaracteristicas] =
        useState([]);

    useEffect(() => {

        apiClient.get("/api/empresa/caracteristicas")
            .then((response) => {

                console.log(response.data);

                setCaracteristicas(response.data);
            })
            .catch((error) => {
                console.error(error);
            });

    }, []);

    return (

        <div className="container mt-4">

            <h3>Publicar Nuevo Puesto</h3>

            <FormPublicarPuesto
                caracteristicas={caracteristicas}
            />

        </div>
    );
}

export default PublicarPuesto;