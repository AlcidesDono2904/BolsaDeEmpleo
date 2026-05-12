import { useState } from "react";
import axios from "axios";

function Login() {

    const [correo, setCorreo] = useState("");
    const [clave, setClave] = useState("");

    const login = async (e) => {

        e.preventDefault();

        try {

            const response = await axios.post(
                "http://localhost:8080/api/auth/login",
                {
                    correo,
                    passwordHash: clave
                }
            );

            console.log(response.data);

            alert("Login correcto");

        } catch (error) {

            alert("Credenciales incorrectas");
        }
    };

    return (

        <div className="container mt-5">

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

        </div>
    );
}

export default Login;