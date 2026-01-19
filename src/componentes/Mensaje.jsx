import "./Mensaje.css";

//componente para mostrar mensajes de error o éxito.

export default function Mensaje({ tipo = "error", children }) {
  //tipo:  usado para cambiar la clase de css según el mensaje que quiero mostrar.
  return <p className={`mensaje mensaje-${tipo}`}>{children}</p>; // "children" es el texto de mensaje que envío desde otros componentes.
}
