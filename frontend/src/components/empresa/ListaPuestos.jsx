function ListaPuestos({ puestos }) {

    return (

        <table className="table table-striped">

            <thead>
            <tr>
                <th>ID</th>
                <th>Descripción</th>
                <th>Salario</th>
                <th>Activo</th>
            </tr>
            </thead>

            <tbody>

            {puestos.map((p) => (

                <tr key={p.id}>

                    <td>{p.id}</td>

                    <td>{p.descripcion}</td>

                    <td>{p.salarioUsd}</td>

                    <td>
                        {p.activo ? "Sí" : "No"}
                    </td>

                </tr>

            ))}

            </tbody>

        </table>
    );
}

export default ListaPuestos;