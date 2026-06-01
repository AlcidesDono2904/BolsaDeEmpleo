import { useEffect, useState } from "react";
import ListaCaracteristicas from "../../components/caracteristicas/ListaCaracteristicas";
import apiClient  from "../../services/apiClient";

function Habilidades() {
    const [habilidades, setHabilidades] = useState([]);
    const [caracteristicas, setCaracteristicas] = useState([]);
    const [caracteristicaSeleccionada, setCaracteristicaSeleccionada] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [formData, setFormData] = useState({ idCaracteristica: "", nivel: "" });
    const [submitError, setSubmitError] = useState(null);
    const [submitSuccess, setSubmitSuccess] = useState(false);

    // Cargar características disponibles
    useEffect(() => {
        cargarCaracteristicas();
        cargarHabilidades();
    }, []);

    const cargarCaracteristicas = async () => {
        try {
            const response = await apiClient.get("/api/oferentes/caracteristicas");
            setCaracteristicas(response.data || []);
            setLoading(false);
        } catch (err) {
            setError("Error al cargar características");
            setLoading(false);
        }
    };

    const cargarHabilidades = async () => {
        try {
            const response = await apiClient.get("/api/oferentes/mis-caracteristicas");
            setHabilidades(response.data || []);
        } catch (err) {
            console.error("Error al cargar habilidades:", err);
        }
    };

    const handleFormChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({
            ...prev,
            [name]: value
        }));
    };

    const handleAgregarHabilidad = async (e) => {
        e.preventDefault();
        setSubmitError(null);
        setSubmitSuccess(false);

        if (!formData.idCaracteristica || !formData.nivel) {
            setSubmitError("Por favor complete todos los campos");
            return;
        }

        try {
            await apiClient.post("/api/oferentes/habilidades/agregar", {
                idCaracteristica: parseInt(formData.idCaracteristica),
                nivel: parseInt(formData.nivel)
            });

            setSubmitSuccess(true);
            setFormData({ idCaracteristica: "", nivel: "" });
            await cargarHabilidades();

            // Limpiar mensaje de éxito después de 3 segundos
            setTimeout(() => setSubmitSuccess(false), 3000);
        } catch (err) {
            setSubmitError(err.response?.data?.message || "Error al agregar habilidad");
        }
    };

    if (loading) {
        return <div className="container mt-4"><p>Cargando...</p></div>;
    }

    return (
        <div className="container mt-4">
            <h3>Mis habilidades</h3>

            <div className="row">
                {/* HABILIDADES ACTUALES */}
                <div className="col-md-4">
                    <table className="table table-sm table-bordered">
                        <thead>
                            <tr>
                                <th>Característica</th>
                                <th>Nivel</th>
                            </tr>
                        </thead>
                        <tbody>
                            {habilidades.length > 0 ? (
                                habilidades.map((h, index) => (
                                    <tr key={index}>
                                        <td>{h.nombre}</td>
                                        <td>{h.nivel}</td>
                                    </tr>
                                ))
                            ) : (
                                <tr>
                                    <td colSpan="2" className="text-center text-muted">
                                        Sin habilidades registradas
                                    </td>
                                </tr>
                            )}
                        </tbody>
                    </table>
                </div>

                {/* LISTA DE CARACTERÍSTICAS */}
                <div className="col-md-4">
                    <ListaCaracteristicas
                        caracteristicas={caracteristicas}
                        caracteristicaSeleccionada={caracteristicaSeleccionada}
                        setCaracteristicaSeleccionada={setCaracteristicaSeleccionada}
                    />
                </div>

                {/* AGREGAR HABILIDAD */}
                <div className="col-md-4">
                    <div className="card p-3">
                        <h6>Agregar habilidad</h6>

                        {submitSuccess && (
                            <div className="alert alert-success alert-dismissible fade show" role="alert">
                                ¡Habilidad agregada exitosamente!
                                <button
                                    type="button"
                                    className="btn-close"
                                    onClick={() => setSubmitSuccess(false)}
                                ></button>
                            </div>
                        )}

                        {submitError && (
                            <div className="alert alert-danger alert-dismissible fade show" role="alert">
                                {submitError}
                                <button
                                    type="button"
                                    className="btn-close"
                                    onClick={() => setSubmitError(null)}
                                ></button>
                            </div>
                        )}

                        <form onSubmit={handleAgregarHabilidad}>
                            <label className="form-label">Característica</label>
                            <select
                                className="form-select"
                                name="idCaracteristica"
                                value={formData.idCaracteristica}
                                onChange={handleFormChange}
                                required
                            >
                                <option value="">Seleccione...</option>
                                {caracteristicas.map((c, index) => (
                                    <option key={index} value={c.id}>
                                        {c.nombre}
                                    </option>
                                ))}
                            </select>

                            <label className="form-label mt-3">Nivel (1-5)</label>
                            <input
                                type="number"
                                className="form-control"
                                name="nivel"
                                min="1"
                                max="5"
                                value={formData.nivel}
                                onChange={handleFormChange}
                                required
                            />

                            <button type="submit" className="btn btn-primary mt-3">
                                Agregar
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Habilidades;