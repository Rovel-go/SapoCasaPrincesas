import React from "react";
import ReactDOM from "react-dom/client";
import { BrowserRouter } from "react-router-dom";
import App from "./App.jsx";
import "./index.css";

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    {/* BrowserRouter envuelve toda la app para habilitar navegación con rutas */}
    <BrowserRouter>
      {/* Componente principal que contiene todas las rutas */}
      <App />
    </BrowserRouter>
  </React.StrictMode>
);
