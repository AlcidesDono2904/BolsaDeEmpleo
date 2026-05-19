import {useParams} from "react-router-dom";
import FormGeneraClave from "../../components/admin/FormGeneraClave.jsx";
import {useState} from "react";


function GenerarClave() {
    const {id} = useParams();

    return (
        <div>
            <div className="row justify-content-center">
                <div className="col-md-6">
                    <div className="card">
                        <div className="card-header bg-dark text-white">
                            <h4>Generar clave para usuario</h4>
                        </div>
                        <div className="card-body">
                            <p><strong>Usuario:</strong> <span>{id}</span></p>
                            <FormGeneraClave id={id}/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default GenerarClave;