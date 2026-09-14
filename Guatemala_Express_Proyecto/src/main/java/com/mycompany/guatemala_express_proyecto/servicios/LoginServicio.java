/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.servicios;

import java.sql.SQLException;
import java.util.Optional;

import com.mycompany.guatemala_express_proyecto.daos.UsuarioDAO;
import com.mycompany.guatemala_express_proyecto.exceptions.CredencialesInvalidasException;
import com.mycompany.guatemala_express_proyecto.exceptions.DatosIncompletosException;
import com.mycompany.guatemala_express_proyecto.exceptions.UsuarioDesactivadoException;
import com.mycompany.guatemala_express_proyecto.exceptions.UsuarioNoEncontradoException;
import com.mycompany.guatemala_express_proyecto.modelos.Usuario;

/**
 *
 * @author matul
 */
public class LoginServicio {

    private final UsuarioDAO usuarioDAO;

    public LoginServicio() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public Usuario iniciarSesion(String identificador, String contrasenia)
            throws UsuarioNoEncontradoException, DatosIncompletosException, CredencialesInvalidasException,
            UsuarioDesactivadoException, SQLException {

        if (datosVacios(identificador, contrasenia)) {
            throw new DatosIncompletosException("Nombre de usuario / Correo o la contraseña están vacíos.");
        }

        String identificadorLimpio = identificador.trim();
        Optional<Usuario> usuarioOptionalNombreUsuario = usuarioDAO.obtenerUsuarioPorNombreUsuario(identificadorLimpio);
        Optional<Usuario> usuarioOptionalCorreo = usuarioDAO.obtenerUsuarioPorCorreo(identificadorLimpio);

        if (usuarioOptionalNombreUsuario.isEmpty() && usuarioOptionalCorreo.isEmpty()) {
            throw new UsuarioNoEncontradoException("Credenciales inválidas, ingrese nuevamente.");
        }

        Usuario usuario = null;

        if (usuarioOptionalNombreUsuario.isPresent()) {
            usuario = usuarioOptionalNombreUsuario.get();
        } else {
            usuario = usuarioOptionalCorreo.get();
        }

        if (!usuario.getContrasenia().equals(contrasenia)) {
            throw new CredencialesInvalidasException("Credenciales inválidas, ingrese nuevamente.");
        }

        if (!usuario.isEstado()) {
            throw new UsuarioDesactivadoException("El usuario está desactivado. Por favor, contacte al administrador.");
        }

        return usuario;
    }

    private boolean datosVacios(String identificador, String contrasenia) {
        return identificador == null || identificador.isBlank() || contrasenia == null || contrasenia.isBlank();
    }
}
