// src/paginas/contactanos/Contactanos.jsx
import "./Contactanos.css";

function Contactanos() {
  // Esta página es informativa, así que mantengo la estructura simple
  return (
    <main className="contacto-container">
      <h1>Contáctanos</h1>

      <p>
        <strong>Dirección:</strong> Carrera 00 # 00 - 00, Ciudad
      </p>
      <p>
        <strong>Teléfono:</strong> (000) 000 0000
      </p>
      <p>
        <strong>WhatsApp:</strong> +57 300 000 0000
      </p>
      <p>
        <strong>Correo:</strong> contacto@casadeprincesas.com
      </p>

      <p>
        <strong>Horario de atención:</strong>
        <br />
        Lunes a Sábado: 8:00 a.m. - 7:00 p.m.
        <br />
        Domingos y festivos: 9:00 a.m. - 4:00 p.m.
      </p>

      <p>
        Estamos aquí para acompañarte, asesorarte y ayudarte a vivir la
        experiencia Casa de Princesas. ¡Será un gusto atenderte!
      </p>
    </main>
  );
}

export default Contactanos;
