import ListaCaracteristica from "../../components/caracteristicas/ListaCaracteristicas.jsx";
import apiClient from "../../services/apiClient.js";
import {useEffect, useState} from "react";
import listaCaracteristicas from "../../components/caracteristicas/ListaCaracteristicas.jsx";
import PanelAgregarCaracteristica from "../../components/caracteristicas/PanelAgregarCaracteristica.jsx";

function Caracteristicas() {

    const [CaracteristicasLista, setCaracteristicasLista] = useState([]);
    const [caracteristicaSeleccionada, setCaracteristicaSeleccionada] = useState(null);

    useEffect(() => {
        apiClient.get(`/api/admin/caracteristicas`)
            .then(response => {
                setCaracteristicasLista(response.data);
            })
    }, []);


    return (
        <div className="container mt-4">
            <h3>Características</h3>
            <div className="row">

                <div className="col-md-7">
                    <ListaCaracteristica  caracteristicas={CaracteristicasLista}
                                                    caracteristicaSeleccionada={caracteristicaSeleccionada}
                                                    setCaracteristicaSeleccionada={setCaracteristicaSeleccionada}
                    />
                </div>

                <div className="col-md-5">
                    <PanelAgregarCaracteristica Caracteristicas={CaracteristicasLista} />
                </div>

            </div>
        </div>
    );
}

export default Caracteristicas;