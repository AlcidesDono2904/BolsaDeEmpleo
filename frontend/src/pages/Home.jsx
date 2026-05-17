import 'bootstrap/dist/css/bootstrap.min.css';
import {useEffect, useState} from "react";
import JobCard from "../components/puestos/JobCard.jsx";
import API_URL from "../services/api.js";

function Home() {

    const [Puestos, setPuestos] = useState([]);
    
    useEffect(() => {
        fetch(`${API_URL}/api/public/ultimosPuestos`)
            .then(res => res.json())
            .then(data => setPuestos(data));
    }, []);

    return (
        <div>
            <div className="container mt-4">
                <h2>Bolsa de Empleo</h2>
                <p>Últimos 5 puestos públicos</p>
            </div>

            <div className="row">
                {Puestos.map(puesto => <JobCard key={puesto.id} puesto={puesto}/>)}
            </div>
        </div>
    );
}

export default Home;