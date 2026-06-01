import {Link} from "react-router-dom";

function ListaUsuariosPendientes({ usuariosPendientes, userType = "empresa" }) {

    return (
        <table className="table table-striped mt-3">
            <thead>
            <tr>
                <th>Usuario</th>
                <th>Acción</th>
            </tr>
            </thead>
            <tbody className="table-group-divider">
                {usuariosPendientes.map((usuario) => (
                    <tr key={usuario.id}>
                        <td>{usuario.correo}</td>
                        <td>
                            <Link className={"btn btn-success btn-sm"}
                                  to={`/admin/generarClave/${usuario.id}?type=${userType}`}>
                                Generar clave
                            </Link>
                        </td>
                    </tr>
                ))}
            </tbody>
        </table>
    );
}

export default ListaUsuariosPendientes;