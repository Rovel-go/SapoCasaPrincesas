import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import "../../estilos/formulario.css";
import "./Login.css";
import { FaAnglesRight } from "react-icons/fa6";
import Mensaje from "../../componentes/Mensaje.jsx";

export default function Login() {
  const navigate = useNavigate();

  // Estado para mostrar mensajes dinámicos (error o éxito)
  const [mensaje, setMensaje] = useState("");
  const [tipoMensaje, setTipoMensaje] = useState("error");

  const handleSubmit = async (e) => {
    e.preventDefault();

    const email = e.target.email.value.trim();
    const contrasena = e.target.contrasena.value.trim();

    setMensaje(""); // limpio mensaje previo

    // Validación básica
    if (!email || !contrasena) {
      setMensaje("Debe ingresar correo y contraseña.");
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
      // Envío de credenciales al backend
      const respuesta = await fetch("http://localhost:8081/api/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, contrasena }),
      });

      const data = await respuesta.text();

      // Si el backend responde con error
      if (!respuesta.ok) {
        setMensaje(data || "Credenciales inválidas.");
        setTipoMensaje("error");
        return;
      }

      // Guardar sesión simple en el navegador
      sessionStorage.setItem("logged", "true");

      setMensaje("Inicio de sesión exitoso.");
      setTipoMensaje("exito");

      // Pequeña pausa para mostrar el mensaje antes de navegar
      setTimeout(() => {
        navigate("/home");
      }, 800);
    } catch (error) {
      // Error de conexión
      setMensaje("Error de conexión con el servidor.");
      setTipoMensaje("error");
    }
  };

  return (
    <div id="conitem2">
      <h1 className="titulo-cuenta">Iniciar Sesión</h1>

      {/* Formulario de login */}
      <form className="formulario-registro" onSubmit={handleSubmit} noValidate>
        <div className="datos-personales">
          <input
            name="email"
            type="text"
            placeholder="Correo electrónico"
            required
          />
          <input
            name="contrasena"
            type="password"
            placeholder="Contraseña"
            required
          />
        </div>

        {/* Enlace para recuperar contraseña */}
        <div className="olvide-contraseña">
          <Link to="/CambiarContrasena" className="link-olvide">
            Olvidé mi contraseña
          </Link>
        </div>

        {/* Mensaje dinámico */}
        {mensaje && <Mensaje tipo={tipoMensaje}>{mensaje}</Mensaje>}

        {/* Botón de enviar */}
        <div className="ingresar">
          <h3 className="leyenda-ingresar">Iniciar Sesión</h3>

          <button type="submit" className="enviar">
            <FaAnglesRight className="icono-flecha" />
          </button>
        </div>

        {/* Enlace a registro */}
        <div className="Registro">
          <p className="cuenta">No tengo cuenta </p>
          <Link to="/registro">Quiero Registrarme!</Link>
        </div>
      </form>
    </div>
  );
}
