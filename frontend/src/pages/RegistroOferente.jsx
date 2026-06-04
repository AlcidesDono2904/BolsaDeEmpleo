import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import apiClient from "../services/apiClient";

function RegistroOferente() {

    const navigate = useNavigate();

    const [nacionalidades, setNacionalidades] = useState([]);

    const [form, setForm] = useState({
        identificacion: "",
        nacionalidad: "",
        nombre: "",
        apellido: "",
        telefono: "",
        residencia: "",
        correo: ""
    });

    useEffect(() => {

        apiClient.get("/api/public/nacionalidades")
            .then((response) => {

                setNacionalidades(response.data);

            })
            .catch(console.error);

    }, []);

    function handleChange(e) {

        setForm({
            ...form,
            [e.target.name]: e.target.value
        });
    }

    function guardar(e) {

        e.preventDefault();

        apiClient.post(
            "/api/public/registro-oferente",
            form
        )
            .then(() => {

                alert(
                    "Registro enviado correctamente"
                );

                navigate("/login");

            })
            .catch((error) => {

                console.error(error);

                alert(
                    "Error al registrar oferente"
                );
            });
    }

    return (

        <div className="container mt-4">

            <h3>Registro de Oferente</h3>

            <form
                className="mt-4"
                onSubmit={guardar}
            >

                <div className="row">

                    <div className="col-md-6 mb-3">

                        <label className="form-label">
                            Identificación
                        </label>

                        <input
                            type="text"
                            className="form-control"
                            name="identificacion"
                            value={form.identificacion}
                            onChange={handleChange}
                        />

                    </div>

                    <div className="col-md-6 mb-3">

                        <label className="form-label">
                            Nacionalidad
                        </label>

                        <select
                            className="form-select"
                            name="nacionalidad"
                            value={form.nacionalidad}
                            onChange={handleChange}
                        >

                            <option value="">
                                Seleccione
                            </option>

                            {nacionalidades.map((n, index) => (

                                <option
                                    key={index}
                                    value={n}
                                >
                                    {n}
                                </option>

                            ))}

                        </select>

                    </div>

                </div>

                <div className="row">

                    <div className="col-md-6 mb-3">

                        <label className="form-label">
                            Nombre
                        </label>

                        <input
                            type="text"
                            className="form-control"
                            name="nombre"
                            value={form.nombre}
                            onChange={handleChange}
                        />

                    </div>

                    <div className="col-md-6 mb-3">

                        <label className="form-label">
                            Apellido
                        </label>

                        <input
                            type="text"
                            className="form-control"
                            name="apellido"
                            value={form.apellido}
                            onChange={handleChange}
                        />

                    </div>

                </div>

                <div className="row">

                    <div className="col-md-6 mb-3">

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

                    <div className="col-md-6 mb-3">

                        <label className="form-label">
                            Residencia
                        </label>

                        <input
                            type="text"
                            className="form-control"
                            name="residencia"
                            value={form.residencia}
                            onChange={handleChange}
                        />

                    </div>

                </div>

                <div className="row">

                    <div className="col-md-6 mb-3">

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

                </div>

                <button
                    type="submit"
                    className="btn btn-success"
                >
                    Registrar
                </button>

            </form>

        </div>
    );
}

export default RegistroOferente;