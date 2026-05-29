import ListaUsuariosPendientes from "../../components/admin/ListaUsuariosPendientes.jsx";
import {useContext, useEffect, useState} from "react";
import {AuthContext} from "../../context/AuthProvider.jsx";
import apiClient from "../../services/apiClient.js";
import {useParams} from "react-router-dom";

function EmpresasPendientes() {
    const { usuario } = useContext(AuthContext);
    const { type = "empresa" } = useParams();
    const [data, setData] = useState([]);

    const getTitulo = () => type === "oferente" ? "Oferentes pendientes" : "Empresas pendientes";
    const getEndpoint = () => type === "oferente" ? "/api/admin/oferentes-pendientes" : "/api/admin/empresas-pendientes";

    useEffect(() => {
        if (!usuario) {
            console.warn("No hay usuario autenticado");
            return;
        }
        apiClient.get(getEndpoint())
            .then(response => {
                setData(response.data);
            })
            .catch(error => {
                console.error(`Error al obtener ${type} pendientes:`, error);
            });
    }, [usuario, type]);

    return(
        <div className="container mt-4">
            <h3>{getTitulo()}</h3>
            {data && <p className="text-muted">Total: {data.length}</p>}
            <ListaUsuariosPendientes usuariosPendientes={data} userType={type}/>
        </div>
    );
}

export default EmpresasPendientes;