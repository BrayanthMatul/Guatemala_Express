/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.servicios.generales.mi_perfil;

import java.sql.SQLException;
import java.util.Optional;

import com.mycompany.guatemala_express_proyecto.daos.UsuarioDAO;
import com.mycompany.guatemala_express_proyecto.exceptions.DatosIncompletosException;
import com.mycompany.guatemala_express_proyecto.exceptions.EntidadYaRegistradaException;
import com.mycompany.guatemala_express_proyecto.exceptions.NoGuardadoEnBDException;
import com.mycompany.guatemala_express_proyecto.exceptions.UsuarioNoEncontradoException;
import com.mycompany.guatemala_express_proyecto.modelos.Usuario;
import com.mycompany.guatemala_express_proyecto.servicios.usuarios.VerificadorDatosUsuarioServicio;

/**
 *
 * @author matul
 */
public class EditarPerfilServicio {

    private final UsuarioDAO usuarioDAO;
    private final VerificadorDatosUsuarioServicio verificador = new VerificadorDatosUsuarioServicio();

    public EditarPerfilServicio() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public boolean actualizarPerfil(Usuario usuario)
            throws DatosIncompletosException, NoGuardadoEnBDException, SQLException, EntidadYaRegistradaException,
            UsuarioNoEncontradoException {

        Optional<Usuario> usuarioRegistrado = usuarioDAO.obtenerUsuarioPorNombreUsuario(usuario.getNombreUsuario());

        if (usuarioRegistrado.isEmpty()) {
            throw new UsuarioNoEncontradoException("Usuario " + usuario.getNombreUsuario() + " no encontrado.");
        }

        if (verificador.datosVacios(usuario, false)) {
            throw new DatosIncompletosException("Por favor, complete todos los campos requeridos.");
        }

        Usuario usuarioExistente = usuarioRegistrado.get();
        boolean mismoCorreo = usuario.getCorreoElectronico().equals(usuarioExistente.getCorreoElectronico());
        boolean mismoNit = usuario.getNit().equals(usuarioExistente.getNit());
        boolean mismoDpi = usuario.getDpi().equals(usuarioExistente.getDpi());

        if (verificador.correoYaRegistrado(usuario) && !mismoCorreo) {
            throw new EntidadYaRegistradaException("El correo electrónico ya está registrado.");
        }

        if (verificador.nitYaRegistrado(usuario) && !mismoNit) {
            throw new EntidadYaRegistradaException("El NIT ya está registrado.");
        }

        if (verificador.dpiYaRegistrado(usuario) && !mismoDpi) {
            throw new EntidadYaRegistradaException("El DPI ya está registrado.");
        }

        return usuarioDAO.actualizarUsuario(usuario);
    }

}
