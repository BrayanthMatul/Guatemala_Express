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

    public Usuario iniciarSesion(String correo, String contrasenia)
            throws UsuarioNoEncontradoException, DatosIncompletosException, CredencialesInvalidasException,
            UsuarioDesactivadoException, SQLException {

        if (datosVacios(correo, contrasenia)) {
            throw new DatosIncompletosException("El correo o la contraseña están vacíos.");
        }

        String correoLimpio = correo.trim();
        Optional<Usuario> usuarioOptional = usuarioDAO.obtenerUsuarioPorCorreo(correoLimpio);

        if (usuarioOptional.isEmpty()) {
            throw new UsuarioNoEncontradoException("Usuario no encontrado con el correo proporcionado.");
        }

        Usuario usuario = usuarioOptional.get();

        if (!usuario.getContrasenia().equals(contrasenia)) {
            throw new CredencialesInvalidasException("La contraseña proporcionada es incorrecta.");
        }

        if (!usuario.isEstado()) {
            throw new UsuarioDesactivadoException("El usuario está desactivado. Por favor, contacte al administrador.");
        }

        return usuario;
    }

    private boolean datosVacios(String correo, String contrasenia) {
        return correo == null || correo.isBlank() || contrasenia == null || contrasenia.isBlank();
    }
}
