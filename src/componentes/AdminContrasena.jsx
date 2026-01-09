import { useState } from "react";
import ErrorMensaje from "../componentes/ErrorMensaje";
import "./AdminContrasena.css"; // ← IMPORTANTE

function AdminContrasena({ modo }) {
  const [formData, setFormData] = useState({
    email: "",
    actual: "",
    nueva: "",
    confirmar: "",
  });

  const [error, setError] = useState("");
  const [exito, setExito] = useState("");

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setExito("");

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

    if (modo === "cambiar" && !formData.actual.trim()) {
      setError("Ingresa tu contraseña actual");
      return;
    }

    if (!formData.nueva.trim()) {
      setError("Ingresa tu nueva contraseña");
      return;
    }

    if (formData.nueva !== formData.confirmar) {
      setError("Las contraseñas no coinciden");
      return;
    }

    const endpoint =
      modo === "recuperar"
        ? "http://localhost:8081/usuarios/recuperar-contrasena"
        : "http://localhost:8081/usuarios/cambiar-contrasena";

    const response = await fetch(endpoint, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        email: formData.email,
        actual: formData.actual,
        nueva: formData.nueva,
      }),
    });

    const text = await response.text();

    if (response.ok) {
      setExito(text);
      return;
    }

    setError(text);
  };

  return (
    <div className="admin-contrasena-container">
      <h2 className="titulo-admin-contrasena">
        {modo === "recuperar" ? "Recuperar contraseña" : "Cambiar contraseña"}
      </h2>

      {error && <ErrorMensaje>{error}</ErrorMensaje>}

      {exito && (
        <div
          style={{
            backgroundColor: "#ddffdd",
            color: "#006600",
            padding: "10px",
            borderRadius: "5px",
            marginBottom: "15px",
            border: "1px solid #006600",
            fontWeight: "bold",
          }}
        >
          {exito}
        </div>
      )}

      <form className="formulario-admin-contrasena" onSubmit={handleSubmit}>
        <input
          type="text"
          name="email"
          placeholder="Correo electrónico"
          value={formData.email}
          onChange={handleChange}
        />

        {modo === "cambiar" && (
          <input
            type="password"
            name="actual"
            placeholder="Contraseña actual"
            value={formData.actual}
            onChange={handleChange}
          />
        )}

        <input
          type="password"
          name="nueva"
          placeholder="Nueva contraseña"
          value={formData.nueva}
          onChange={handleChange}
        />

        <input
          type="password"
          name="confirmar"
          placeholder="Confirmar contraseña"
          value={formData.confirmar}
          onChange={handleChange}
        />

        <button type="submit" className="btn-admin-contrasena">
          {modo === "recuperar" ? "Restablecer" : "Actualizar"}
        </button>
      </form>

      <div className="volver-login-admin">
        <a href="/login">Volver al login</a>
      </div>
    </div>
  );
}

export default AdminContrasena;
