/*import CambiarContrasena from "./paginas/cambiarContrasena/CambiarContrasena";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/test" element={<CambiarContrasena />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;*/

/*import CambiarContrasena from "./paginas/cambiarContrasena/CambiarContrasena";

function App() {
  return (
    <>
      <h1>TEST</h1>
      <CambiarContrasena />
    </>
  );
}

export default App;*/

import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./paginas/login/Login";
import Registro from "./paginas/registro/Registro";
import CambiarContrasena from "./paginas/cambiarContrasena/CambiarContrasena";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/login" element={<Login />} />
        <Route path="/registro" element={<Registro />} />
        <Route path="/cambiarContrasena" element={<CambiarContrasena />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
