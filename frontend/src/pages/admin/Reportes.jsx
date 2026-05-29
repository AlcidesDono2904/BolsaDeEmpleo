import {useState} from "react";
import apiClient from "../../services/apiClient.js";

function Reportes() {
    const fecha = new Date();

    const [year, setYear] = useState(fecha.getFullYear());
    const [month, setMonth] = useState(fecha.getMonth() + 1);

    const handleSubmit = (e) => {
        e.preventDefault()
        apiClient.get(`/api/admin/reporte`, {
            responseType: 'blob',
            params:{
                month: month,
                year: year
            }
        })
            .then((response) => {
                const url = window.URL.createObjectURL(new Blob([response.data]));
                const link = document.createElement('a');
                link.href = url;
                link.setAttribute('download', `reporte_puestos_${year}_${month}.pdf`);
                document.body.appendChild(link);
                link.click();
                link.remove();
            })
            .catch((error) => {
                console.error("Error al generar el reporte:", error);
                alert("Ocurrió un error al generar el reporte. Por favor, inténtalo de nuevo.");
            });

    }

    return (
        <div className="container mt-4">
            <h3>Reporte de Puestos por Mes</h3>

            <p className="text-muted">
                Selecciona un mes y año para generar el reporte en PDF con todos los puestos publicados.
            </p>

            <form onSubmit={handleSubmit}>
                <div className="row">
                    <div className="col-md-4">
                        <label htmlFor="mes" className="form-label">Mes</label>
                        <select id="mes" name="mes"
                                onChange={(e) => setMonth(e.target.value)}
                                className="form-select" required>
                            <option value="1">Enero</option>
                            <option value="2">Febrero</option>
                            <option value="3">Marzo</option>
                            <option value="4">Abril</option>
                            <option value="5">Mayo</option>
                            <option value="6">Junio</option>
                            <option value="7">Julio</option>
                            <option value="8">Agosto</option>
                            <option value="9">Septiembre</option>
                            <option value="10">Octubre</option>
                            <option value="11">Noviembre</option>
                            <option value="12">Diciembre</option>
                        </select>
                    </div>

                    <div className="col-md-4">
                        <label htmlFor="anio" className="form-label">Año</label>
                        <input type="number" id="anio" name="anio" className="form-control"
                               onChange={(e) => setYear(e.target.value)}
                               value={year} required/>
                    </div>

                    <div className="col-md-4 d-flex align-items-end">
                        <button
                            onClick={handleSubmit}
                            className="btn btn-primary w-100">
                            Descargar PDF
                        </button>
                    </div>
                </div>
            </form>

            <div className="alert alert-info mt-4" role="alert">
                <strong>Nota:</strong> El reporte incluirá todos los puestos publicados en el mes y año seleccionado,
                agrupados por empresa.
            </div>

        </div>
    );
}

export default Reportes;