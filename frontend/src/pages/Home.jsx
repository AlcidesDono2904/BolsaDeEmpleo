import 'bootstrap/dist/css/bootstrap.min.css';
import {useEffect, useState} from "react";
import JobCard from "../components/puestos/JobCard.jsx";
import apiClient from "../services/apiClient.js";

function Home() {
    //TODO: agregar manejo de errores

    const [puestos, setPuestos] = useState([]);
    
    useEffect(() => {
        apiClient.get(`/api/public/ultimosPuestos`)
            .then(response => {
                 setPuestos(response.data);
            })
    }, []);

    return (
        <div className="container mt-4">
            <div className="container mt-4">
                <h2>Bolsa de Empleo</h2>
                <p>Últimos 5 puestos públicos</p>
            </div>

            <div className="row">
                {puestos.map(puesto => <JobCard key={puesto.id} puesto={puesto}/>)}
            </div>
        </div>
    );
}

export default Home;