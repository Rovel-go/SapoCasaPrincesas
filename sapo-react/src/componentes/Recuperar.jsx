function Recuperar() {
  return (
    <div>
      <h2>Recuperar contraseña</h2>
      <p>Ingresa tu correo electrónico </p>
      <form>
        <input type="email" placeholder="email" required />
        <button type="submit">Enviar enlace</button>
      </form>
    </div>
  );
}

export default Recuperar;
