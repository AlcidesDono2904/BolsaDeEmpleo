import { useEffect, useState } from "react";
import apiClient from "../../services/apiClient";
import ListaPuestos from "../../components/empresa/ListaPuestos";

function Puestos() {

    const [puestos, setPuestos] = useState([]);

    useEffect(() => {

        apiClient.get("/api/empresa/puestos")
            .then((response) => {
                setPuestos(response.data);
            })
            .catch((error) => {
                console.error(error);
            });

    }, []);

    return (

        <div className="container mt-4">

            <h3>Mis puestos</h3>

            <ListaPuestos puestos={puestos} />

        </div>
    );
}

export default Puestos;