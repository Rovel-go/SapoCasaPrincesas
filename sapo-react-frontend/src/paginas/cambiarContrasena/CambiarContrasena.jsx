import { useState } from "react";
import "../../estilos/formulario.css";
import "./CambiarContrasena.css";
import { FaAnglesRight } from "react-icons/fa6";
import Mensaje from "../../componentes/Mensaje.jsx";

export default function CambiarContrasena() {
  // Estado para mostrar mensajes de error o éxito
  const [mensaje, setMensaje] = useState("");
  const [tipoMensaje, setTipoMensaje] = useState("error");

  const handleSubmit = async (e) => {
    e.preventDefault();

    const email = e.target.email.value.trim();

    setMensaje(""); // limpio mensaje previo

    // Validación básica
    if (!email) {
      setMensaje("Debe ingresar su correo.");
      setTipoMensaje("error");
      return;
    }

    // Validación de formato de correo
    if (!/^[^@\s]+@[^@\s]+\.[^@\s]+$/.test(email)) {
      setMensaje("Ingrese un correo electrónico válido.");
      setTipoMensaje("error");
      return;
    }

    try {
      // Envío del correo al backend
      const respuesta = await fetch("https://backenddespliegue.fly.dev/api/recuperar", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email }),
      });

      const data = await respuesta.text();

      // Si el backend responde con error
      if (!respuesta.ok) {
        setMensaje(data || "No se pudo procesar la solicitud.");
        setTipoMensaje("error");
        return;
      }

      // Si todo sale bien
      setMensaje(data);
      setTipoMensaje("exito");
    } catch (error) {
      // Error de conexión
      setMensaje("Error de conexión con el servidor.");
      setTipoMensaje("error");
    }
  };

  return (
    <div id="conitem2">
      <h1 className="titulo-cuenta">Recuperar Contraseña</h1>

      {/* Formulario de recuperación */}
      <form className="formulario-registro" onSubmit={handleSubmit} noValidate>
        <div className="datos-personales">
          <input
            name="email"
            type="text"
            placeholder="Ingrese su correo"
            required
          />
        </div>

        {/* Mensaje dinámico */}
        {mensaje && <Mensaje tipo={tipoMensaje}>{mensaje}</Mensaje>}

        {/* Botón enviar */}
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
