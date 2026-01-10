import React from "react";
import ReactDOM from "react-dom/client";
import { BrowserRouter } from "react-router-dom";
import App from "./App.jsx";

//Mensaje de depuración par confirmar que el proceso de montaje inicial de react a comenzado. //
console.log("React está montando...");

// Punto de enrada principal de la aplicación. //
// "createRoot" habilita el modo concurrente de React. //
ReactDOM.createRoot(document.getElementById("root")).render(
  // "BrowserRouter" envuelve toda la aplicación para habilitar navegación SPA sin recargas. //
  <BrowserRouter>
    <App />
  </BrowserRouter>
);
