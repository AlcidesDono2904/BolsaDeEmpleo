function AdminDashboard() {

    const usuario =
        JSON.parse(localStorage.getItem("usuario"));

    return (

        <div className="container mt-5">

            <h2>Admin Dashboard</h2>

            <hr />

            <h4>{usuario?.correo}</h4>

        </div>
    );
}

export default AdminDashboard;