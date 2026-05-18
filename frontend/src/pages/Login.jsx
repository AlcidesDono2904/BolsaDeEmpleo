import {useNavigate} from "react-router-dom";
import {useContext, useState} from "react";
import axios from "axios";
import API_URL from "../services/api";
import {AuthContext} from "../context/AuthProvider.jsx";

function Login() {

    const { login } = useContext(AuthContext);
    const navigate = useNavigate();
    const [correo, setCorreo] = useState("");
    const [clave, setClave] = useState("");

    const loginUser = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post(`${API_URL}/api/auth/login`, {
                correo: correo, password: clave
            });
            const data = response.data;
            if (data) {
                login(data.token);
                if (data.rol === "ADMIN") {
                    navigate("/admin");
                } else if (data.rol === "EMPRESA") {
                    navigate("/empresa");
                } else if (data.rol === "OFERENTE") {
                    navigate("/oferente");
                }

            } else {
                alert("Credenciales incorrectas");
            }
        } catch (error) {
            console.log(error);
            alert("Credenciales incorrectas, error");
        }
    };

    return (<div className="container mt-5">
        <h2>Login</h2>
        <form onSubmit={loginUser}>
            <input
                className="form-control mb-3"
                placeholder="Correo"
                value={correo}
                onChange={(e) => setCorreo(e.target.value)}
            />
            <input
                type="password"
                className="form-control mb-3"
                placeholder="Clave"
                value={clave}
                onChange={(e) => setClave(e.target.value)}
            />
            <button className="btn btn-primary">
                Ingresar
            </button>
        </form>

    </div>);
}

export default Login;