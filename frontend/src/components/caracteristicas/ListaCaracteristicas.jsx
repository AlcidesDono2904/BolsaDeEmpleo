import {useEffect, useState} from "react";

function ListaCaracteristicas({ caracteristicas = [] }) {
    // agregar enrutamiento segun admin u oferente
    // arbol de caracteristicas
    //pasr a a botoones
    // tabla de caracteristicas
    // cat seleccionada -> subcategorias...

    const [caracteristicasArbol, setCaracteristicasArbol] = useState([]);
    const [caracteristicaSeleccionada, setCaracteristicaSeleccionada] = useState(null);
    const [caracteristicasHijas, setCaracteristicasHijas] = useState([]);

    useEffect(() => {
        setCaracteristicasHijas(caracteristicas.filter(c => c.padre === caracteristicaSeleccionada?.id  || caracteristicaSeleccionada === null));
    }, [caracteristicaSeleccionada,caracteristicas]);

    function handleCaracteristicaClick(caracteristica) {
        const arbol = [];
        let actual = caracteristica;

        while (actual != null) {
            arbol.push(actual);
            actual = caracteristicas.find(c => c.id === actual.padre);
        }

        arbol.reverse();
        setCaracteristicasArbol(arbol);
        setCaracteristicaSeleccionada(caracteristica);
    }


    return (
        <div className="card p-3" style={{backgroundColor: "#e8f5e9"}}>
            <b>Ruta:</b>
            <div className="mt-2">
                {caracteristicasArbol.length === 0 ? (
                    <span className="me-2">
                        <a className="btn btn-outline-secondary btn-sm">
                            Raíces
                        </a>
                    </span>
                    ) : (
                    <>
                    <span className="me-2">
                        <button
                            className="btn btn-outline-secondary btn-sm"
                            onClick={() => {
                                setCaracteristicasArbol([]);
                                setCaracteristicaSeleccionada(null);
                            }}
                        >
                            Raíces
                        </button>
                    </span>
                    <span className={"mx-1"}>/</span>
                    {caracteristicasArbol.map((c, index) => (
                    <span key={`${c.id ?? c.nombre}-${index}`} className="me-2">
                        <button className="btn btn-outline-primary btn-sm"
                           onClick={() => handleCaracteristicaClick(c)}
                        >
                            {c.nombre}
                        </button>
                        {index < caracteristicasArbol.length - 1 && <span className="mx-1">/</span>}
                    </span>
                    ))}
                    </>
                )}
            </div>

            {caracteristicaSeleccionada == null &&
                <p className="mt-2 text-muted">Categorías:</p>}
            {caracteristicaSeleccionada != null &&
                <p className="mt-2">Subcategorías de: <strong>{caracteristicaSeleccionada.nombre}</strong></p>}

            <table className="table table-sm">
                <tbody>
                {caracteristicasHijas.map((c, index) => (
                    <tr key={`${c.id ?? c.nombre}-${index}`}>
                        <td>{c.nombre}</td>
                        <td className="text-end">
                            <button className="btn btn-outline-primary btn-sm"
                               onClick={() => handleCaracteristicaClick(c)}
                            >
                                Entrar
                            </button>
                        </td>
                    </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default ListaCaracteristicas;