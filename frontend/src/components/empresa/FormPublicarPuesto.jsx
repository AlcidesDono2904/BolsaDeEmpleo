import { useState } from "react";
import { useNavigate } from "react-router-dom";
import apiClient from "../../services/apiClient";

function FormPublicarPuesto({ caracteristicas }) {

    const navigate = useNavigate();

    const [descripcion, setDescripcion] =
        useState("");

    const [salario, setSalario] =
        useState("");

    const [tipo, setTipo] =
        useState("PUBLICO");

    const [seleccionadas, setSeleccionadas] =
        useState([]);

    function toggleCaracteristica(id) {

        if (seleccionadas.includes(id)) {

            setSeleccionadas(
                seleccionadas.filter((c) => c !== id)
            );

        } else {

            setSeleccionadas([
                ...seleccionadas,
                id
            ]);
        }
    }

    function guardarPuesto(e) {

        e.preventDefault();

        apiClient.post(
            "/api/empresa/guardar-puesto",
            {
                descripcion,
                salario,
                tipo,
                caracteristicas: seleccionadas
            }
        )
            .then(() => {

                alert("Puesto publicado");

                navigate("/empresa/puestos");
            })
            .catch((error) => {

                console.error(error);

                alert("Error al publicar");
            });
    }

    return (

        <form
            className="mt-4"
            onSubmit={guardarPuesto}
        >

            <div className="mb-4">

                <label className="form-label">
                    Características requeridas
                </label>

                <div
                    className="border rounded p-3"
                    style={{
                        maxHeight: "200px",
                        overflowY: "auto"
                    }}
                >

                    {caracteristicas[0]?.caracteristicas?.map((c) => (
                        <div
                            className="form-check"
                            key={c.id}
                        >

                            <input
                                className="form-check-input"
                                type="checkbox"
                                checked={
                                    seleccionadas.includes(c.id)
                                }
                                onChange={() =>
                                    toggleCaracteristica(c.id)
                                }
                            />

                            <label
                                className="form-check-label"
                            >
                                {c.nombre}
                            </label>

                        </div>

                    ))}

                </div>

            </div>

            <div className="mb-3">

                <label className="form-label">
                    Descripción
                </label>

                <textarea
                    className="form-control"
                    rows="4"
                    value={descripcion}
                    onChange={(e) =>
                        setDescripcion(e.target.value)
                    }
                />

            </div>

            <div className="row">

                <div className="col-md-6 mb-3">

                    <label className="form-label">
                        Salario
                    </label>

                    <input
                        type="number"
                        step="0.01"
                        className="form-control"
                        value={salario}
                        onChange={(e) =>
                            setSalario(e.target.value)
                        }
                    />

                </div>

                <div className="col-md-6 mb-3">

                    <label className="form-label">
                        Tipo
                    </label>

                    <select
                        className="form-select"
                        value={tipo}
                        onChange={(e) =>
                            setTipo(e.target.value)
                        }
                    >

                        <option value="PUBLICO">
                            Pública
                        </option>

                        <option value="PRIVADO">
                            Privada
                        </option>

                    </select>

                </div>

            </div>

            <button className="btn btn-success">
                Publicar Puesto
            </button>

        </form>
    );
}

export default FormPublicarPuesto;