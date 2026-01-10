import "./Registro.css";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom"; // navegación
import ErrorMensaje from "../componentes/ErrorMensaje";

function Registro() {
  const navigate = useNavigate(); // redirección

  // estado del formulario
  const [formData, setFormData] = useState({
    nombre: "",
    apellidos: "",
    email: "",
    contrasena: "",
    confirmarContrasena: "",
  });

  // estado para errores visibles
  const [error, setError] = useState("");

  // actualiza campos
  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  // envía formulario
  const handleSubmit = async (e) => {
    e.preventDefault();

    setError(""); // limpia error previo

    // validaciones básicas
    if (!formData.nombre.trim()) {
      setError("El nombre es obligatorio");
      return;
    }

    if (!formData.apellidos.trim()) {
      setError("Los apellidos son obligatorios");
      return;
    }

    if (!formData.email.trim()) {
      setError("El correo es obligatorio");
      return;
    }

    // validación personalizada del @
    if (!formData.email.includes("@")) {
      setError('Ingrese un formato de email válido "@"');
      return;
    }

    // validación completa de email
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(formData.email)) {
      setError('Ingrese un formato de email válido "@"');
      return;
    }

    if (!formData.contrasena.trim()) {
      setError("La contraseña es obligatoria");
      return;
    }

    if (formData.contrasena !== formData.confirmarContrasena) {
      setError("Las contraseñas no coinciden");
      return;
    }

    // petición al backend
    const response = await fetch("http://localhost:8081/api/registro", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        nombre: formData.nombre,
        apellidos: formData.apellidos,
        email: formData.email,
        contraseña: formData.contrasena, // coincide con backend
      }),
    });

    const text = await response.text();

    if (response.ok) {
      navigate("/login"); // redirección automática
      return;
    }

    setError(text); // error del backend
  };

  return (
    <div className="registro-container">
      <h2>Crea tu cuenta</h2>

      {/* mensaje de error profesional */}
      {error && <ErrorMensaje>{error}</ErrorMensaje>}

      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="nombre"
          placeholder="Nombre"
          value={formData.nombre}
          onChange={handleChange}
        />

        <input
          type="text"
          name="apellidos"
          placeholder="Apellidos"
          value={formData.apellidos}
          onChange={handleChange}
        />

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

        <input
          type="password"
          name="confirmarContrasena"
          placeholder="Confirmar contraseña"
          value={formData.confirmarContrasena}
          onChange={handleChange}
        />

        <button type="submit">Ingresar</button>
      </form>
    </div>
  );
}

export default Registro;
