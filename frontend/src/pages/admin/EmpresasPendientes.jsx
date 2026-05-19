import ListaUsuariosPendientes from "../../components/admin/ListaUsuariosPendientes.jsx";
import {useContext, useEffect, useState} from "react";
import {AuthContext} from "../../context/AuthProvider.jsx";
import apiClient from "../../services/apiClient.js";

 function EmpresasPendientes() {

    const { usuario } = useContext(AuthContext);
    const [data, setData] = useState([])

    useEffect(() => {
        if (!usuario) {
            console.warn("No hay usuario autenticado");
            return;
        }
        apiClient.get("/api/admin/empresas-pendientes")
            .then(response => {
                setData(response.data);
            })
            .catch(error => {
                console.error("Error al obtener empresas pendientes:", error);
            });
    }, [usuario]);

    return(
        <div className="container mt-4">
            <h3>Empresas pendientes</h3>
            {data && <p className="text-muted">Total: {data.length}</p>}
            <ListaUsuariosPendientes usuariosPendientes={data}/>
        </div>
    );
}

export default EmpresasPendientes;