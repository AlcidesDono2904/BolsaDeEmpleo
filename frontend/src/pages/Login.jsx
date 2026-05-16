import {useNavigate} from "react-router-dom";
import {useState} from "react";
import axios from "axios";
import API_URL from "../services/api";

function Login() {

    const navigate = useNavigate();
    const [correo, setCorreo] = useState("");
    const [clave, setClave] = useState("");

    const login = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post(`${API_URL}/api/auth/login`, {
                correo: correo, passwordHash: clave
            });
            if (response.data) {

                localStorage.setItem("usuario", JSON.stringify(response.data));
                navigate("/dashboard");

                localStorage.setItem("usuario", JSON.stringify(response.data));
                if (response.data.rol === "ADMIN") {
                    navigate("/admin");
                } else if (response.data.rol === "EMPRESA") {
                    navigate("/empresa");
                } else if (response.data.rol === "OFERENTE") {
                    navigate("/oferente");
                }

            } else {
                alert("Credenciales incorrectas");
            }
        } catch (error) {
            console.log(error);
            alert("Credenciales incorrectas");
        }
    };

    return (<div className="container mt-5">
        <h2>Login</h2>
        <form onSubmit={login}>
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