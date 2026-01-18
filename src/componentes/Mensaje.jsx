import "./Mensaje.css";

export default function Mensaje({ tipo = "error", children }) {
  return <p className={`mensaje mensaje-${tipo}`}>{children}</p>;
}
