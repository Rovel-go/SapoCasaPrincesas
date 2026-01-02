import "./Registro.css";

function Registro() {
  return (
    <div className="registro-container">
      <h2>Registro</h2>
      <p>Crea tu Cuenta</p>
      <form>
        <input type="text" placeholder="Nombre" required />
        <input type="email" placeholder="Correo electrónico" required />
        <input type="password" placeholder="Contraseña" required />
        <input type="password" placeholder="Confirmar contraseña" required />
        <button type="submit">Ingresar</button>
      </form>
    </div>
  );
}

export default Registro;
