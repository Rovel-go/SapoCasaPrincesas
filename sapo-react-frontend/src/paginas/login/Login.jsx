import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import "../../estilos/formulario.css";
import "./Login.css";
import { FaAnglesRight } from "react-icons/fa6";
import Mensaje from "../../componentes/Mensaje.jsx";

export default function Login() {
  const navigate = useNavigate();

  const [mensaje, setMensaje] = useState("");
  const [tipoMensaje, setTipoMensaje] = useState("error");

  const handleSubmit = async (e) => {
    e.preventDefault();

    const email = e.target.email.value.trim();
    const contrasena = e.target.contrasena.value.trim();

    setMensaje("");

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
      const respuesta = await fetch("https://backenddespliegue.fly.dev/api/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, contrasena }),
      });

      // Intentar leer JSON SIEMPRE (tu backend siempre devuelve JSON)
      let data = null;
      try {
        data = await respuesta.json();
      } catch {
        data = null;
      }

      // Si la respuesta NO es OK → mostrar mensaje del backend
      if (!respuesta.ok) {
        const mensajeError = data?.mensaje || "Credenciales inválidas.";
        setMensaje(mensajeError);
        setTipoMensaje("error");
        return;
      }

      // Login exitoso → guardar sesión
      sessionStorage.setItem("logged", "true");
      sessionStorage.setItem("usuario", JSON.stringify(data));

      // Mensaje de bienvenida
      sessionStorage.setItem(
        "mensajeBienvenida",
        "Bienvenido, " + data.nombre + " " + data.apellidos
      );

      setMensaje("Inicio de sesión exitoso.");
      setTipoMensaje("exito");

      // Navegar después de un pequeño delay
      setTimeout(() => {
        navigate("/home");
      }, 800);
    } catch (error) {
      setMensaje("Error de conexión con el servidor.");
      setTipoMensaje("error");
    }
  };

  return (
    <div id="conitem2">
      <h1 className="titulo-cuenta">Iniciar Sesión</h1>

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

        <div className="olvide-contraseña">
          <Link to="/CambiarContrasena" className="link-olvide">
            Olvidé mi contraseña
          </Link>
        </div>

        {mensaje && <Mensaje tipo={tipoMensaje}>{mensaje}</Mensaje>}

        <div className="ingresar">
          <h3 className="leyenda-ingresar">Iniciar Sesión</h3>

          <button type="submit" className="enviar">
            <FaAnglesRight className="icono-flecha" />
          </button>
        </div>

        <div className="Registro">
          <p className="cuenta">No tengo cuenta </p>
          <Link to="/registro">Quiero Registrarme!</Link>
        </div>
      </form>
    </div>
  );
}
