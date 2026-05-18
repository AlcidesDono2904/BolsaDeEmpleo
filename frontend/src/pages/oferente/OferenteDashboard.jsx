function OferenteDashboard() {

    const usuario =
        JSON.parse(localStorage.getItem("usuario"));

    return (

        <div className="container mt-5">

            <h2>Oferente Dashboard</h2>

            <hr />

            <h4>{usuario?.correo}</h4>

        </div>
    );
}

export default OferenteDashboard;