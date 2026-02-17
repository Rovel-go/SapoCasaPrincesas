import { useState } from "react";
import "./Servicios.css";

function Servicios() {
  const [categoriaActiva, setCategoriaActiva] = useState(null);
  const [subservicios, setSubservicios] = useState([]);
  const [subservicioSeleccionado, setSubservicioSeleccionado] = useState(null);

  const categoriasFijas = [
    {
      id: 1,
      nombre: "Color",
      imagen:
        "http://localhost:8081/imagenes/serviciosCategoria/imagen-color.png",
    },
    {
      id: 2,
      nombre: "Cortar",
      imagen:
        "http://localhost:8081/imagenes/serviciosCategoria/imagen-cortar.png",
    },
    {
      id: 3,
      nombre: "Peinar",
      imagen:
        "http://localhost:8081/imagenes/serviciosCategoria/imagen-peinar.png",
    },
  ];

  const cargarSubservicios = (idCategoria) => {
    if (categoriaActiva === idCategoria) {
      setCategoriaActiva(null);
      setSubservicioSeleccionado(null);
      return;
    }

    fetch(
      `http://localhost:8081/servicios/subservicios/categoria/${idCategoria}`
    )
      .then((res) => res.json())
      .then((data) => {
        setSubservicios(data);
        setCategoriaActiva(idCategoria);
        setSubservicioSeleccionado(null);
      })
      .catch((err) => console.error("Error cargando subservicios:", err));
  };

  return (
    <div className="salones-page">
      <div className="salon-card-container">
        {categoriasFijas.map((cat) => (
          <div key={cat.id} className="salon-card">
            <div
              className="salon-img"
              onClick={() => cargarSubservicios(cat.id)}
            >
              <img src={cat.imagen} alt={cat.nombre} />
            </div>

            <div className="salon-leyenda">
              <h1 onClick={() => cargarSubservicios(cat.id)}>{cat.nombre}</h1>

              {categoriaActiva === cat.id && (
                <div className="salon-detalle open">
                  <ul>
                    {subservicios.map((s) => (
                      <li
                        key={s.id}
                        onClick={() => setSubservicioSeleccionado(s)}
                      >
                        {s.nombre}
                      </li>
                    ))}
                  </ul>
                </div>
              )}

              {subservicioSeleccionado && categoriaActiva === cat.id && (
                <div className="salon-detalle open">
                  <h2>{subservicioSeleccionado.nombre}</h2>

                  <p className="precio">
                    {subservicioSeleccionado.precio.toLocaleString("es-CO", {
                      style: "currency",
                      currency: "COP",
                    })}
                  </p>
                </div>
              )}
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default Servicios;
