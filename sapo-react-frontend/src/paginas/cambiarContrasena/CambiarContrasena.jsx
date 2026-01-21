import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../../estilos/formulario.css";
import "./CambiarContrasena.css";
import { FaAnglesRight } from "react-icons/fa6";
import Mensaje from "../../componentes/Mensaje.jsx";

export default function CambiarContrasena() {
  const [mensaje, setMensaje] = useState("");
  const [tipoMensaje, setTipoMensaje] = useState("error");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    const email = e.target.email.value.trim();

    setMensaje("");

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
      const respuesta = await fetch("http://localhost:8081/api/recuperar", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email }),
      });

      const data = await respuesta.text();

      if (!respuesta.ok) {
        setMensaje(data || "No se pudo procesar la solicitud.");
        setTipoMensaje("error");
        return;
      }

      // ÉXITO
      setMensaje(data);
      setTipoMensaje("exito");

      // Esperar 5 segundos y redirigir a Login
      setTimeout(() => {
        navigate("/login");
      }, 5000);
    } catch (error) {
      setMensaje("Error de conexión con el servidor.");
      setTipoMensaje("error");
    }
  };

  return (
    <div id="conitem2">
      <h1 className="titulo-cuenta">Recuperar Contraseña</h1>

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
