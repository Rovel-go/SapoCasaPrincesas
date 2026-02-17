import { Navigate } from "react-router-dom";

export default function RutaProtegida({ children }) {
  // Verifico si existe una sesión activa guardada en sessionStorage
  const isLogged = sessionStorage.getItem("logged") === "true";

  // Si no está logueado, lo redirijo inmediatamente al login
  if (!isLogged) {
    return <Navigate to="/login" replace />;
  }

  // Si está autenticado, permito el acceso al contenido protegido
  return children;
}
