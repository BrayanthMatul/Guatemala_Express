/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.servicios;

import java.sql.SQLException;
import java.util.Optional;

import com.mycompany.guatemala_express_proyecto.daos.UsuarioDAO;
import com.mycompany.guatemala_express_proyecto.exceptions.DatosIncompletosException;
import com.mycompany.guatemala_express_proyecto.exceptions.EntidadYaRegistradaException;
import com.mycompany.guatemala_express_proyecto.exceptions.NoGuardadoEnBDException;
import com.mycompany.guatemala_express_proyecto.modelos.Usuario;

/**
 *
 * @author matul
 */
public class CrearUsuarioServicio {

    private final UsuarioDAO usuarioDAO;

    public CrearUsuarioServicio() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public int crearUsuario(Usuario usuario)
            throws DatosIncompletosException, NoGuardadoEnBDException, SQLException, EntidadYaRegistradaException {

        if (datosVacios(usuario)) {
            throw new DatosIncompletosException("Por favor, complete todos los campos requeridos.");
        }

        if (correoYaRegistrado(usuario)) {
            throw new EntidadYaRegistradaException("El correo electrónico ya está registrado.");
        }

        if (nitYaRegistrado(usuario)) {
            throw new EntidadYaRegistradaException("El NIT ya está registrado.");
        }

        if (dpiYaRegistrado(usuario)) {
            throw new EntidadYaRegistradaException("El DPI ya está registrado.");
        }

        return usuarioDAO.crearUsuario(usuario);
    }

    private boolean datosVacios(Usuario usuario) {
        if (usuario == null) {
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

        if (usuario.getContrasenia() == null || usuario.getContrasenia().isBlank()) {
            return true;
        }

        return false;
    }

    private boolean correoYaRegistrado(Usuario usuario) throws SQLException {
        String correo = usuario.getCorreoElectronico();
        Optional<Usuario> usuarioExistente = usuarioDAO.obtenerUsuarioPorCorreo(correo);

        return usuarioExistente.isPresent();
    }

    private boolean nitYaRegistrado(Usuario usuario) throws SQLException {
        String nit = usuario.getNit();
        Optional<Usuario> usuarioExistente = usuarioDAO.obtenerUsuarioPorNit(nit);

        return usuarioExistente.isPresent();
    }

    private boolean dpiYaRegistrado(Usuario usuario) throws SQLException {
        String dpi = usuario.getDpi();
        Optional<Usuario> usuarioExistente = usuarioDAO.obtenerUsuarioPorDpi(dpi);

        return usuarioExistente.isPresent();
    }

}
