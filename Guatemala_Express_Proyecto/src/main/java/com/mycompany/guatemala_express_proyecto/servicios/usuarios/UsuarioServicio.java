/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.servicios.usuarios;

import java.sql.SQLException;
import java.util.List;

import com.mycompany.guatemala_express_proyecto.daos.UsuarioDAO;
import com.mycompany.guatemala_express_proyecto.enums.Rol;
import com.mycompany.guatemala_express_proyecto.modelos.Usuario;

/**
 *
 * @author matul
 */
public class UsuarioServicio {

    private final UsuarioDAO usuarioDAO;

    public UsuarioServicio() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public List<Usuario> obtenerUsuariosPorRol(Rol rol) throws SQLException {
        return usuarioDAO.obtenerUsuariosPorRol(rol);
    }

    public boolean cambiarEstadoUsuario(String nombreUsuario, boolean nuevoEstado) throws SQLException {

        if (nuevoEstado == false && esUltimoAdministradorActivo()) {
            return false;
        }
        return usuarioDAO.actualizarEstadoUsuario(nombreUsuario, nuevoEstado);
    }

    private boolean esUltimoAdministradorActivo() throws SQLException {
        int cantidadAdministradoresActivos = usuarioDAO.activosPorRol(Rol.ADMINISTRADOR_SISTEMA);
        return cantidadAdministradoresActivos == 1;
    }

}
