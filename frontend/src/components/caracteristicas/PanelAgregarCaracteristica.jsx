import { useState } from 'react';
import caracteristicas from "../../pages/admin/Caracteristicas.jsx";
import apiClient from "../../services/apiClient.js";

function PanelAgregarCaracteristica({ Caracteristicas = [] }) {
    const [nombre, setNombre] = useState('');
    const [idPadre, setIdPadre] = useState(0);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');

    function handleSubmit (e) {
        e.preventDefault();
        setError('');
        setLoading(true);
        if (!nombre.trim()) {
            setError('El nombre es obligatorio.');
            setLoading(false);
            return;
        }
        apiClient.post('/api/admin/caracteristicas',
            {idPadre:idPadre, nombre: nombre.trim()
            })
            .then(() => {
                setNombre('');
                setIdPadre('');
            })
            .catch(error => {
            console.error("Error al crear la característica:", error);
            setError('Ocurrió un error al crear la característica. Por favor, inténtalo de nuevo.');
            });
        setLoading(false);
        }

    return (
        <div>
            <div className="card p-3" style={{backgroundColor: "#e8f5e9"}}>
                <h6>Agregar Característica</h6>
                <form onSubmit={handleSubmit}>
                    <label htmlFor="nombreCaracteristica" className="form-label mt-2">Nombre</label>
                    <input
                        type="text"
                        className="form-control"
                        id="nombreCaracteristica"
                        value={nombre}
                        onChange={(e) => setNombre(e.target.value)}
                        required
                    />
                    <label htmlFor="selectPadre" className="form-label mt-3">Padre</label>
                    <select
                        className="form-select"
                        id="selectPadre"
                        value={idPadre}
                        onChange={(e) => setIdPadre(e.target.value)}
                    >
                        <option value="0">Sin padre</option>
                        {Caracteristicas.map((car) => (
                            <option key={car.id} value={car.id}>
                                {car.nombre}
                            </option>
                        ))}
                    </select>
                    {error && <div className="alert alert-danger mt-2">{error}</div>}
                    <button
                        type="submit"
                        className="btn btn-primary mt-3"
                        disabled={loading}
                    >
                        {loading ? 'Creando...' : 'Crear'}
                    </button>
                </form>
            </div>
        </div>
    );
}

export default PanelAgregarCaracteristica;