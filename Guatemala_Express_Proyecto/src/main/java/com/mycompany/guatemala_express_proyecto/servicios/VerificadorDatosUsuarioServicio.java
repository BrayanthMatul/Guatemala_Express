/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.servicios;

import java.sql.SQLException;
import java.util.Optional;

import com.mycompany.guatemala_express_proyecto.daos.UsuarioDAO;
import com.mycompany.guatemala_express_proyecto.modelos.Usuario;

/**
 *
 * @author matul
 */
public class VerificadorDatosUsuarioServicio {

    private final UsuarioDAO usuarioDAO;

    public VerificadorDatosUsuarioServicio() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public boolean datosVacios(Usuario usuario, boolean revisarContrasenia) {
        if (usuario == null) {
            return true;
        }

        if (usuario.getNombreUsuario() == null || usuario.getNombreUsuario().isBlank()) {
            return true;
        }

        if (usuario.getNit() == null || usuario.getNit().isBlank()) {
            return true;
        }

        if (usuario.getDpi() == null || usuario.getDpi().isBlank()) {
            return true;
        }

        if (usuario.getNombreCompleto() == null || usuario.getNombreCompleto().isBlank()) {
            return true;
        }

        if (usuario.getTelefono() == null || usuario.getTelefono().isBlank()) {
            return true;
        }

        if (usuario.getDireccion() == null || usuario.getDireccion().isBlank()) {
            return true;
        }

        if (usuario.getCorreoElectronico() == null || usuario.getCorreoElectronico().isBlank()) {
            return true;
        }

        if (revisarContrasenia) {
            if (usuario.getContrasenia() == null || usuario.getContrasenia().isBlank()) {
                return true;
            }

        }

        return false;
    }

    public boolean nombreUsuarioYaRegistrado(Usuario usuario) throws SQLException {
        String nombreUsuario = usuario.getNombreUsuario();
        Optional<Usuario> usuarioExistente = usuarioDAO.obtenerUsuarioPorNombreUsuario(nombreUsuario);

        return usuarioExistente.isPresent();
    }

    public boolean correoYaRegistrado(Usuario usuario) throws SQLException {
        String correo = usuario.getCorreoElectronico();
        Optional<Usuario> usuarioExistente = usuarioDAO.obtenerUsuarioPorCorreo(correo);

        return usuarioExistente.isPresent();
    }

    public boolean nitYaRegistrado(Usuario usuario) throws SQLException {
        String nit = usuario.getNit();
        Optional<Usuario> usuarioExistente = usuarioDAO.obtenerUsuarioPorNit(nit);

        return usuarioExistente.isPresent();
    }

    public boolean dpiYaRegistrado(Usuario usuario) throws SQLException {
        String dpi = usuario.getDpi();
        Optional<Usuario> usuarioExistente = usuarioDAO.obtenerUsuarioPorDpi(dpi);

        return usuarioExistente.isPresent();
    }

}
