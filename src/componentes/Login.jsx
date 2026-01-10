import "./Login.css";
import { Link, useNavigate } from "react-router-dom";
import { useState } from "react";
import ErrorMensaje from "../componentes/ErrorMensaje";

function Login() {
  const [formData, setFormData] = useState({
    email: "",
    contrasena: "",
  });

  const [error, setError] = useState("");

  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    setError("");

    if (!formData.email.trim()) {
      setError("Ingresa tu correo electrónico");
      return;
    }

    if (!formData.email.includes("@")) {
      setError('Ingrese un formato de email válido "@"');
      return;
    }

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(formData.email)) {
      setError('Ingrese un formato de email válido "@"');
      return;
    }

    if (!formData.contrasena.trim()) {
      setError("Ingresa tu contraseña");
      return;
    }

    //  RUTA CORRECTA DEL BACKEND
    const response = await fetch("http://localhost:8081/usuarios/login", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        email: formData.email,
        contrasena: formData.contrasena,
      }),
    });

    const text = await response.text();

    if (response.ok) {
      navigate("/home");
    } else {
      setError(text);
    }
  };

  return (
    <div className="login-container">
      <h2 className="titulo-login">Inicia sesión</h2>

      {error && <ErrorMensaje>{error}</ErrorMensaje>}

      <form className="formulario-login" onSubmit={handleSubmit}>
        <div className="usuario-contraseña">
          <input
            type="text"
            name="email"
            placeholder="Correo electrónico"
            value={formData.email}
            onChange={handleChange}
          />

          <input
            type="password"
            name="contrasena"
            placeholder="Contraseña"
            value={formData.contrasena}
            onChange={handleChange}
          />
        </div>

        <button type="submit" className="btn-login">
          Ingresar
          <div className="logo-angles">
            <i className="fa-solid fa-angles-right"></i>
          </div>
        </button>
      </form>

      {/*  ESTA RUTA SIGUE SIENDO CORRECTA */}
      <div className="olvide-contraseña">
        <Link to="/recuperar">¿Olvide mi contraseña?</Link>
      </div>

      <div className="crear-cuenta">
        <p className="cuenta">¿No tienes cuenta?</p>
        <Link to="/registro" className="link-registro">
          Crear cuenta
        </Link>
      </div>
    </div>
  );
}

export default Login;
