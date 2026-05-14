function Dashboard() {

    const usuario = JSON.parse(localStorage.getItem("usuario"));

    return (

        <div className="container mt-5">

            <h2>Dashboard</h2>

            <hr />

            <p>Bienvenido:</p>

            <h4>{usuario?.correo}</h4>

            <p>Rol: {usuario?.rol}</p>

        </div>
    );
}

export default Dashboard;