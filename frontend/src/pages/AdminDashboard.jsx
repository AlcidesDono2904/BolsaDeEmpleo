import {useContext} from "react";
import {AuthContext} from "../context/AuthProvider.jsx";

function AdminDashboard() {

    const {usuario} = useContext(AuthContext);

    return (
        <div className="container mt-5">
            <h2>Admin Dashboard</h2>
            <hr />
            <h4>{usuario.correo}</h4>
        </div>
    );
}

export default AdminDashboard;