function Caracteristicas() {



    return (
        <div className="container mt-4">
            <h3>Características</h3>
            <div className="row">
                <!-- LISTA DE CATEGORIAS -->
                <div className="col-md-7">
                    <div th:replace="~{fragments/listarCaracteristicas :: listarCaracteristicas}"></div>
                </div>
                <!-- AGREGAR CARACTERISTICA -->
                <div className="col-md-5">
                    <div className="card p-3" style="background-color:#e8f5e9">
                        <h6>Agregar Característica</h6>
                        <form action="/admin/agregarCaracteristica" method="POST">
                            <label htmlFor="nombreCaracteristica" className="form-label mt-2">Nombre</label>
                            <input name="nombre" className="form-control" id="nombreCaracteristica" required/>
                            <label htmlFor="selectPadre" className="form-label mt-3">Padre</label>
                            <select className="form-select" id="selectPadre" name="idPadre">

                                <option th:value="${null}">Sin padre</option>

                                <option th:each="car : ${caracteristicas}"
                                        th:value="${car.id}"
                                        th:text="${car.nombre}">
                                </option>
                            </select>
                            <button type="submit" className="btn btn-primary mt-3">
                                Crear
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Caracteristicas;