function Footer({ className = "" }) {
  return (
    <footer className={className ? `footer ${className}` : "footer"}>
      <div className="nosotros">
        <ul className="lista-nosotros">
          <li>
            <a href="#">Quiénes Somos</a>
          </li>
          <li>
            <a href="#">Misión</a>
          </li>
          <li>
            <a href="#">Visión</a>
          </li>
          <li>
            <a href="#">Contáctanos</a>
          </li>
        </ul>
      </div>
    </footer>
  );
}

export default Footer;
