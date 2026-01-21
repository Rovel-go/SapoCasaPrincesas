Evidencia Ga7-AA4-EV03


Componente Frontend del proyecto Formativo
Rodrigo González Velásquez
ADSO 2977454

Esta evidencia corresponde al desarrollo del frontend parcial. El objetivo es implementar las pantallas esenciales del flujo de autenticación de usuarios:

Login
Registro
Recuperación de contraseña (cambiar contraseña)

Estas pantallas se desarrollaron en un proyecto React independiente, creado exclusivamente para esta evidencia, sin incluir el resto del sistema.


Estructura del módulo

/frontend-evidencia
│
├── src
│   ├── componentes
│   │   └── Mensaje.jsx
│   │
│   ├── estilos
│   │   └── formulario.css
│   │
│   ├── paginas
│   │   ├── login
│   │   │   ├── Login.jsx
│   │   │   └── Login.css
│   │   │
│   │   ├── registro
│   │   │   ├── Registro.jsx
│   │   │   └── Registro.css
│   │   │
│   │   └── cambiarContrasena
│   │       ├── CambiarContrasena.jsx
│   │       └── CambiarContrasena.css
│   │
│   ├── App.jsx
│   └── main.jsx
│
├── package.json
└── README.md


Tecnologías Utilizadas

React 18
React Router DOM 6
Vite
CSS modular


Flujo de Autenticación Implementado

Login
Validación de correo y contraseña
Mensajes dinámicos d error o éxito
El mensaje de éxito permanece visible (no desaparece)
No redirige automáticamente.

2. Registro
Validación de campos
Envío de datos al backend
Mensaje de éxito permanece visible
redirige automáticamente a /Login


Rutas del Módulo

/Login---------------> Pantalla de inicio de sesión
/Registro------------> Formulario de registro
/CambiarContrasena---> Recuperación de contraseña


Endpoints Utilizados

Endpoint	Método	Función
/login	POST	Validar credenciales
/registro	POST	Registrar Usuario
/cambiarContrasena	POST	Enviar correo de recuperación



Cómo Ejecutar el Proyecto

 Instalar dependencias:  npm install
 Ejecutar en Modo desarrollo: npm run dev
 Abrir en el navegador:  http://localhost:5173


Se implementaron mensajes dinámicos, validaciones y redirecciones.
Módulo listo para ser conectado a backend.

