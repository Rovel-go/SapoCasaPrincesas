package com.evidencia.api_registro_login.controller;

import com.evidencia.api_registro_login.model.Usuario;
import com.evidencia.api_registro_login.service.ServicioAutenticacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// Controlador que expone los servicios web de registro e inicio de sesión
@RestController
@RequestMapping("/api")
public class ControladorAutenticacion {

    // Inyección del servicio que contiene la lógica de autenticación
    @Autowired
    private ServicioAutenticacion servicioAutenticacion;

    // Endpoint para registrar un nuevo usuario
    @PostMapping("/registro")
    public String registrar(@RequestBody Usuario usuario) {
        // Delegamos la lógica al servicio y devolvemos el mensaje resultante
        return servicioAutenticacion.registrar(usuario);
    }

    // Endpoint para iniciar sesión
    @PostMapping("/login")
    public String login(@RequestBody Usuario usuario) {
        // Delegamos la lógica al servicio y devolvemos el mensaje resultante
        return servicioAutenticacion.login(usuario);
    }
}