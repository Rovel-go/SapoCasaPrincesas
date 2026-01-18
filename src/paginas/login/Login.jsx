import { useState } from "react";
import { Link } from "react-router-dom";
import "../../estilos/formulario.css";
import "./Login.css";
import Mensaje from "../../componentes/Mensaje";

export default function Login() {
  const [mensaje, setMensaje] = useState("");
  const [tipoMensaje, setTipoMensaje] = useState("error");

  const handleSubmit = async (e) => {
    e.preventDefault();

    const email = e.target.email.value.trim();
    const password = e.target.passwordHash.value.trim();

    if (!email || !password) {
      setMensaje("Por favor, completa este campo.");
      setTipoMensaje("error");
      return;
    }

    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
      setMensaje("Ingrese un formato válido: email@mail.com");
      setTipoMensaje("error");
      return;
    }

    try {
      const res = await fetch("http://localhost:8081/login", {
        method: "POST",
        body: new FormData(e.target),
      });

      if (!res.ok) {
        if (res.status === 404) {
          setMensaje(
            "Este usuario no está asociado a ninguna cuenta. ¡Regístrate!"
          );
        } else if (res.status === 401) {
          setMensaje("Contraseña incorrecta. ¿Intentamos de nuevo?");
        } else {
          setMensaje("Ups! Algo salió mal. Inténtalo más tarde, por favor.");
        }
        setTipoMensaje("error");
        return;
      }

      window.location.href = "/home";
    } catch (error) {
      setMensaje("Ups! Algo salió mal. Intenta más tarde, por favor.");
      setTipoMensaje("error");
    }
  };

  return (
    <div id="conitem1">
      <h1 className="titulo-login">Login</h1>

      <form
        id="loginForm"
        className="formulario-login"
        onSubmit={handleSubmit}
        noValidate
      >
        <div className="bloque-login">
          <div className="usuario-contraseña">
            <input
              className="usuario"
              id="email"
              name="email"
              type="text"
              placeholder="usuario (email)"
              required
            />

            <input
              className="contraseña"
              id="passwordHash"
              name="passwordHash"
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
        </div>

        {mensaje && <Mensaje tipo={tipoMensaje}>{mensaje}</Mensaje>}

        <div id="inicio_sesion">
          <button type="submit" className="iniciar-sesion">
            Iniciar Sesión
          </button>
        </div>
      </form>

      <div className="Registro">
        <p className="cuenta">
          No tengo cuenta,
          <Link id="link" to="/registro">
            {" "}
            ¡Quiero Registrarme!
          </Link>
        </p>
      </div>
    </div>
  );
}
