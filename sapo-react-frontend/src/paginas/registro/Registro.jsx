import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../../estilos/formulario.css";
import "./Registro.css";
import { FaAnglesRight } from "react-icons/fa6";
import Mensaje from "../../componentes/Mensaje.jsx";

export default function Registro() {
  const navigate = useNavigate();

  // Estado para mostrar mensajes dinámicos (error o éxito)
  const [mensaje, setMensaje] = useState("");
  const [tipoMensaje, setTipoMensaje] = useState("error");

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Obtengo los valores del formulario
    const nombre = e.target.nombre.value.trim();
    const apellidos = e.target.apellidos.value.trim(); // nombre EXACTO del input
    const email = e.target.email.value.trim();
    const contrasena = e.target.passwordHash.value.trim(); // nombre EXACTO del input
    const confirmar = e.target.confirmarPassword.value.trim();

    // Validación básica
    if (!nombre || !apellidos || !email || !contrasena || !confirmar) {
      setMensaje("Todos los campos son obligatorios.");
      setTipoMensaje("error");
      return;
    }

    // Validación de coincidencia de contraseñas
    if (contrasena !== confirmar) {
      setMensaje("Las contraseñas no coinciden.");
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
      // Envío de datos al backend
      const respuesta = await fetch("https://backenddespliegue.fly.dev/api/registro", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          nombre,
          apellidos, // nombres EXACTOS que el backend espera
          email,
          contrasena, // coincide con el campo del backend
        }),
      });

      const data = await respuesta.text();

      // Si el backend responde con error
      if (!respuesta.ok) {
        setMensaje(data);
        setTipoMensaje("error");
        return;
      }

      // Registro exitoso
      setMensaje("Usuario registrado correctamente.");
      setTipoMensaje("exito");

      // Pausa breve para mostrar el mensaje antes de redirigir
      setTimeout(() => {
        navigate("/login");
      }, 1000);
    } catch (error) {
      // Error de conexión
      setMensaje("Error de conexión con el servidor.");
      setTipoMensaje("error");
    }
  };

  return (
    <div id="conitem2">
      <h1 className="titulo-cuenta">Crear Cuenta</h1>

      {/* Formulario de registro */}
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

        {/* Mensaje dinámico */}
        {mensaje && <Mensaje tipo={tipoMensaje}>{mensaje}</Mensaje>}

        {/* Botón enviar */}
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
