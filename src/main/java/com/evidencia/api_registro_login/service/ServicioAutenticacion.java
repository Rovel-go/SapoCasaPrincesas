package com.evidencia.api_registro_login.service;

import com.evidencia.api_registro_login.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Servicio que contiene la lógica de registro y autenticación
@Service
public class ServicioAutenticacion {

    // Lista en memoria para almacenar los usuarios registrados
    private final List<Usuario> usuariosRegistrados = new ArrayList<>();

    // Método para registrar un nuevo usuario
    public String registrar(Usuario usuario) {

        // Validación de campos vacíos
        if (usuario.getUsuario() == null || usuario.getUsuario().isBlank()) {
            return "Error: el usuario es obligatorio";
        }

        if (usuario.getPassword() == null || usuario.getPassword().isBlank()) {
            return "Error: la contraseña es obligatoria";
        }

        // Verificar si el usuario ya existe
        boolean existe = usuariosRegistrados.stream()
                .anyMatch(u -> u.getUsuario().equals(usuario.getUsuario()));

        if (existe) {
            return "Error: el usuario ya está registrado";
        }

        // Si pasa las validaciones, se agrega a la lista
        usuariosRegistrados.add(usuario);
        return "Registro exitoso";
    }

    // Método para iniciar sesión
    public String login(Usuario usuario) {

        // Buscar el usuario en la lista
        Usuario encontrado = usuariosRegistrados.stream()
                .filter(u -> u.getUsuario().equals(usuario.getUsuario()))
                .findFirst()
                .orElse(null);

        // Si no existe, error en la autenticación
        if (encontrado == null) {
            return "Error en la autenticación";
        }

        // Si la contraseña no coincide, error en la autenticación
        if (!encontrado.getPassword().equals(usuario.getPassword())) {
            return "Error en la autenticación";
        }

        // Si todo es correcto, autenticación satisfactoria
        return "Autenticación satisfactoria";
    }
}
