/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.servicios.generales.mi_perfil;

import java.sql.SQLException;
import java.util.Optional;

import com.mycompany.guatemala_express_proyecto.daos.UsuarioDAO;
import com.mycompany.guatemala_express_proyecto.exceptions.UsuarioNoEncontradoException;
import com.mycompany.guatemala_express_proyecto.modelos.Usuario;

/**
 *
 * @author matul
 */
public class ConsultarPerfilServicio {

    public Usuario obtenerUsuarioPorId(int id) throws SQLException, UsuarioNoEncontradoException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Optional<Usuario> usuario = usuarioDAO.obtenerUsuarioPorId(id);
                
        if(usuario.isPresent()) {
            return usuario.get();
        } else {
            throw new UsuarioNoEncontradoException("Usuario con ID " + id + " no encontrado.");
        }
    }
}
