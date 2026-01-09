// COMPONENTE REUTILIZABLE DE MENSAJES DE ERROR
function ErrorMensaje({ children }) {
  return (
    <div
      style={{
        backgroundColor: "#ffdddd",
        color: "#a30000",
        padding: "10px",
        borderRadius: "5px",
        marginBottom: "15px",
        border: "1px solid #a30000",
        fontWeight: "bold",
      }}
    >
      {children}
    </div>
  );
}

export default ErrorMensaje;
