import { useState } from "react";
import "../../estilos/formulario.css";
import "./CambiarContrasena.css";
import { FaAnglesRight } from "react-icons/fa6";
import Mensaje from "../../componentes/Mensaje.jsx";

export default function CambiarContrasena() {
  const [mensaje, setMensaje] = useState("");
  const [tipoMensaje, setTipoMensaje] = useState("error");

  const handleSubmit = (e) => {
    e.preventDefault();

    const email = e.target.email.value.trim();

    // Validación 1: campo vacío
    if (!email) {
      setMensaje("Debe ingresar su correo electrónico.");
      setTipoMensaje("error");
      return;
    }

    // Validación 2: formato de email
    if (!/^[^@\s]+@[^@\s]+\.[^@\s]+$/.test(email)) {
      setMensaje("Ingrese un correo electrónico válido.");
      setTipoMensaje("error");
      return;
    }

    // ⭐ SIMULACIÓN DEL ENVÍO (sin backend)
    setMensaje(
      `Se ha enviado un correo a ${email}. Siga el enlace para cambiar su contraseña.`
    );
    setTipoMensaje("exito");
  };

  return (
    <div id="conitem2">
      <h1 className="titulo-cuenta">Cambiar Contraseña</h1>

      <form className="formulario-registro" onSubmit={handleSubmit} noValidate>
        <div className="datos-personales">
          <input
            name="email"
            type="text"
            placeholder="Ingrese su correo"
            required
          />
        </div>

        {mensaje && <Mensaje tipo={tipoMensaje}>{mensaje}</Mensaje>}

        <div className="ingresar">
          <h3 className="leyenda-ingresar">Enviar</h3>

          <button type="submit" className="enviar">
            <FaAnglesRight className="icono-flecha" />
          </button>
        </div>
      </form>
    </div>
  );
}
