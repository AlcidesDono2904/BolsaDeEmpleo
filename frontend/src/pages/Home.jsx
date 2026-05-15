import 'bootstrap/dist/css/bootstrap.min.css';

function Home() {
    return (
        <main className="flex-fill" style={{maxWidth: '1500px', margin: '0 auto'}}>
            <div className="container mt-4">
                <h2>Bolsa de Empleo</h2>
                <p>Últimos 5 puestos públicos</p>
            </div>

            <div className="row">
                <div className="col-md-4" th:each="p, stat : ${puestos}">
                    <div className="card bg-light border-success mb-3">
                        <div className="card-body">
                            <h5 th:text="${p.idEmpresa.nombre}">Empresa</h5>
                            <p th:text="${p.descripcion}">Descripcion</p>
                            <div className="container m-0">
                                <div className="row">
                                    <strong>$ <span th:text="${p.salarioUsd}"></span></strong>
                                </div>
                                <div className="row">
                                    <strong>₡ <span th:text="${salariosColones[stat.index]}"></span></strong>
                                </div>
                            </div>
                            <br/>

                            <button className="btn btn-outline-primary w-100"
                                    data-bs-toggle="modal"
                                    th:attr="data-bs-target='#detallePuesto_' + ${p.id}">
                                Ver detalle
                            </button>
                        </div>
                    </div>

                    <div className="modal fade"
                         th:id="'detallePuesto_' + ${p.id}"
                         tabindex="-1">
                        <div className="modal-dialog">
                            <div className="modal-content">
                                <div className="modal-header">
                                    <h5 className="modal-title"
                                        th:text="${p.idEmpresa.nombre}">
                                        Empresa
                                    </h5>
                                    <button type="button"
                                            className="btn-close"
                                            data-bs-dismiss="modal">
                                    </button>
                                </div>
                                <div className="modal-body">
                                    <ul>
                                        <li th:each="pc : ${p.puestoCaracteristicas}">
                                            <span th:text="${pc.idCaracteristica.nombre}"></span>
                                            (Nivel <span th:text="${pc.nivelRequerido}"></span>)
                                        </li>
                                    </ul>
                                </div>
                                <div className="modal-footer">
                                    <button className="btn btn-secondary"
                                            data-bs-dismiss="modal">
                                        Cerrar
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    );
}

export default Home;