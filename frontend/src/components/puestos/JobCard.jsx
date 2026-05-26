function JobCard({ puesto }) {
    //TODO: agregar logica de "cargando"
    //TODO: agregar modal con detalle del puesto (caracteristicas requeridas)
  return (
      <div key={puesto.id} className="col-md-4">
          <div className="card bg-light border-success mb-3">
              <div className="card-body">
                  <h5>{puesto.nombreEmpresa}</h5>
                  <p>{puesto.descripcion}</p>
                  <div className="container m-0">
                      <div className="row">
                          <strong>$ <span>{puesto.salarioUsd}</span></strong>
                      </div>
                      <div className="row">
                          <strong>₡ <span>{puesto.salarioColones}</span></strong>
                      </div>
                  </div>
                  <br/>

                  <button className="btn btn-outline-primary w-100"
                          data-bs-toggle="modal"
                          data-bs-target = {'#detallePuesto_'+puesto.id}>
                      Ver detalle
                  </button>
              </div>
          </div>

          <div className="modal fade"
               id={'detallePuesto_'+puesto.id}
               tabIndex="-1">
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
  );
}

export default JobCard;