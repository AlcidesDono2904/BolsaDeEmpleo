import { useState } from "react";
import { useNavigate } from "react-router-dom";
import apiClient from "../services/apiClient";

function RegistroEmpresa() {

    const navigate = useNavigate();

    const [form, setForm] = useState({
        nombre: "",
        correo: "",
        telefono: "",
        localizacion: "",
        descripcion: ""
    });

    function handleChange(e) {

        setForm({
            ...form,
            [e.target.name]: e.target.value
        });
    }

    function guardar(e) {

        e.preventDefault();

        apiClient.post(
            "/api/public/registro-empresa",
            form
        )
            .then(() => {

                alert("Empresa registrada correctamente");

                navigate("/login");

            })
            .catch((error) => {

                console.error(error);

                alert("Error al registrar empresa");
            });
    }

    return (

        <div className="container mt-4">

            <div className="row justify-content-center">

                <div className="col-md-6">

                    <div className="card shadow-sm p-4">

                        <h4 className="mb-3 text-center">
                            Registro de Empresa
                        </h4>

                        <form onSubmit={guardar}>

                            <div className="mb-3">

                                <label className="form-label">
                                    Nombre de la empresa
                                </label>

                                <input
                                    type="text"
                                    className="form-control"
                                    name="nombre"
                                    value={form.nombre}
                                    onChange={handleChange}
                                />

                            </div>

                            <div className="mb-3">

                                <label className="form-label">
                                    Correo
                                </label>

                                <input
                                    type="email"
                                    className="form-control"
                                    name="correo"
                                    value={form.correo}
                                    onChange={handleChange}
                                />

                            </div>

                            <div className="mb-3">

                                <label className="form-label">
                                    Teléfono
                                </label>

                                <input
                                    type="text"
                                    className="form-control"
                                    name="telefono"
                                    value={form.telefono}
                                    onChange={handleChange}
                                />

                            </div>

                            <div className="mb-3">

                                <label className="form-label">
                                    Localización
                                </label>

                                <input
                                    type="text"
                                    className="form-control"
                                    name="localizacion"
                                    value={form.localizacion}
                                    onChange={handleChange}
                                />

                            </div>

                            <div className="mb-3">

                                <label className="form-label">
                                    Descripción
                                </label>

                                <textarea
                                    className="form-control"
                                    rows="3"
                                    name="descripcion"
                                    value={form.descripcion}
                                    onChange={handleChange}
                                />

                            </div>

                            <button
                                type="submit"
                                className="btn btn-primary w-100"
                            >
                                Registrar Empresa
                            </button>

                        </form>

                    </div>

                </div>

            </div>

        </div>
    );
}

export default RegistroEmpresa;