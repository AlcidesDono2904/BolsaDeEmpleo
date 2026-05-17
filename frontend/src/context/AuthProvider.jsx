// context/AuthProvider.jsx

import React, { createContext, useEffect, useState } from "react";
import {jwtDecode} from "jwt-decode";

export const AuthContext = createContext(undefined);

function AuthProvider({ children }) {

    const [usuario, setUsuario] = useState(null);

    useEffect(() => {
        const token = localStorage.getItem("token");
        if (token) {
            const payload = jwtDecode(token);
            setUsuario({
                correo: payload.sub,
                ROL: payload.ROL
            });
        }
    }, []);

    const login = (token) => {
        localStorage.setItem("token", token);

        const payload = jwtDecode(token);
        console.log("Payload del token:", payload.ROL);
        setUsuario({
            correo: payload.sub,
            ROL: payload.ROL
        });
    };

    const logout = () => {
        localStorage.removeItem("token");
        setUsuario(null);
    };

    return (
        <AuthContext.Provider
            value={{
                usuario,
                login,
                logout
            }}
        >
            {children}
        </AuthContext.Provider>
    );
}

export default AuthProvider;