import { useState } from "react";
import { Link } from "react-router-dom";
import "../../estilos/formulario.css";
import "./Registro.css";
import { FaAnglesRight } from "react-icons/fa6";
import Mensaje from "../../componentes/Mensaje.jsx";

export default function Registro() {
  const [mensaje, setMensaje] = useState("");
  const [tipoMensaje, setTipoMensaje] = useState("error");

  const handleSubmit = async (e) => {
    e.preventDefault();

    const form = e.target;
    const nombre = form.nombre.value.trim();
    const apellidos = form.apellidos.value.trim();
    const email = form.email.value.trim();
    const pass = form.passwordHash.value.trim();
    const confirm = form.confirmarPassword.value.trim();

    if (!pass) {
      setMensaje("La contraseña es obligatoria.");
      setTipoMensaje("error");
      return;
    }

    if (!/^[0-9]{8}$/.test(pass)) {
      setMensaje(
        "La contraseña debe contener exactamente 8 dígitos numéricos."
      );
      setTipoMensaje("error");
      return;
    }

    if (!confirm) {
      setMensaje("Debe confirmar la contraseña.");
      setTipoMensaje("error");
      return;
    }

    if (pass !== confirm) {
      setMensaje("Las contraseñas no coinciden.");
      setTipoMensaje("error");
      return;
    }

    try {
      const respuesta = await fetch("http://localhost:8081/registro", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          nombre,
          apellidos,
          email,
          passwordHash: pass,
        }),
      });

      const data = await respuesta.json();

      if (!respuesta.ok) {
        setMensaje(data.mensaje || "Error en el registro.");
        setTipoMensaje("error");
        return;
      }

      setMensaje("Registro exitoso. Redirigiendo...");
      setTipoMensaje("exito");

      setTimeout(() => {
        window.location.href = "/login";
      }, 1000);
    } catch (error) {
      setMensaje("Error de conexión con el servidor.");
      setTipoMensaje("error");
    }
  };

  return (
    <div id="conitem2">
      <h1 className="titulo-cuenta">Crea Tu Cuenta</h1>

      <form className="formulario-registro" onSubmit={handleSubmit} noValidate>
        <div className="datos-personales">
          <input name="nombre" type="text" placeholder="Nombre" required />
          <input
            name="apellidos"
            type="text"
            placeholder="Apellidos"
            required
          />
          <input name="email" type="text" placeholder="E-mail" required />
          <input
            name="passwordHash"
            type="password"
            placeholder="Contraseña"
            required
          />
          <input
            name="confirmarPassword"
            type="password"
            placeholder="Confirmar contraseña"
            required
          />
        </div>

        {mensaje && <Mensaje tipo={tipoMensaje}>{mensaje}</Mensaje>}

        <div className="ingresar">
          <h3 className="leyenda-ingresar">Ingresar</h3>
          <button type="submit" className="enviar">
            <FaAnglesRight className="icono-flecha" />
          </button>
        </div>
      </form>
    </div>
  );
}
