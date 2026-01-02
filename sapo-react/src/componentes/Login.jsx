import { Link } from "react-router-dom";

function Login() {
  return (
    <div>
      <h2>Login</h2>
      <form>
        <input type="email" placeholder="usuario (email)" required />
        <input type="password" placeholder="Contraseña" required />
        <button type="submit">Iniciar sesión</button>
      </form>

      <div style={{ marginTop: "1rem" }}>
        <Link to="/recuperar">Olvidé mi Contraseña</Link>
      </div>

      <div style={{ marginTop: "0.7rem" }}>
        <Link to="/registro">No tengo cuenta, Quiero Registrarme</Link>
      </div>
    </div>
  );
}

export default Login;

{
  /*import { Link } from "react-router-dom";
function Login() {
  return (
    <div>
      <h2>Login</h2>
      <form>
        <input type="email" placeholder="usuario (email)" required />
        <input type="password" placeholder="Contraseña" required />
        <button type="submit">Iniciar sesión</button>
      </form>

      <div style={{ marginTop: "1rem" }}>
        <Link to="/recuperar">Olvide mi Contraseña</Link>
      </div>
      <div style={{ marginTop: "0.7rem" }}>
        <Link to="/registro">No tengo cuenta, Quiero Registrarme</Link>
      </div>
    </div>
  );
}

export default Login;*/
}
