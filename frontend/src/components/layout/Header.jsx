import {useContext} from "react";
import {Link, useNavigate} from "react-router-dom";

import {AuthContext} from "../../context/AuthProvider.jsx";
import PublicMenu from "../menus/PublicMenu.jsx";
import AdminMenu from "../menus/AdminMenu.jsx";
import EmpresaMenu from "../menus/EmpresaMenu.jsx";
import OferenteMenu from "../menus/OferenteMenu.jsx";


function Header() {
    const {usuario, logout} = useContext(AuthContext);
    const navigate = useNavigate();

    const isAdmin = usuario && usuario.ROL === "ADMIN";
    const isOferente = usuario && usuario.ROL === "OFERENTE";
    const isEmpresa = usuario && usuario.ROL === "EMPRESA";

    function handleLogout() {
        navigate("/login");
        logout();
    }

    return (
        <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
            <div className="container-fluid">

                <Link className="navbar-brand" to="/">
                    <img src="/img/empleo.png" width="30" className="me-2" alt="Logo"/>
                    BolsaEmpleo
                </Link>
                {/* Público */}
                {usuario == null && <PublicMenu/>}
                {
                    isAdmin && <AdminMenu/>
                }
                {
                    isOferente && <OferenteMenu/>
                }
                {
                    isEmpresa && <EmpresaMenu/>
                }

                <div className="ms-auto">
                    <div>
                        {usuario ? (
                            <div className="d-flex gap-3 align-items-center">
                                <div className="p-2">
                                    <span className="nav-link text-white">
                                        {usuario.correo + " --- " + usuario.ROL}
                                    </span>
                                </div>
                                <div className="p-2">
                                    <button onClick={handleLogout}
                                            className="btn btn-outline-light">
                                        Logout
                                    </button>
                                </div>
                            </div>
                        ) : (
                            <Link className="nav-link text-white" to="/login">Login</Link>
                        )
                        }
                    </div>
                </div>
            </div>
        </nav>
    )
}

export default Header;