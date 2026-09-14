/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.servicios;

import java.sql.SQLException;

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
    private final VerificadorDatosUsuarioServicio verificador;

    public CrearUsuarioServicio() {
        this.usuarioDAO = new UsuarioDAO();
        this.verificador = new VerificadorDatosUsuarioServicio();
    }

    public boolean crearUsuario(Usuario usuario)
            throws DatosIncompletosException, NoGuardadoEnBDException, SQLException, EntidadYaRegistradaException {

        if (verificador.datosVacios(usuario, true)) {
            throw new DatosIncompletosException("Por favor, complete todos los campos requeridos.");
        }

        if (verificador.nombreUsuarioYaRegistrado(usuario)) {
            throw new EntidadYaRegistradaException("El nombre de usuario ya está registrado.");
        }

        if (verificador.correoYaRegistrado(usuario)) {
            throw new EntidadYaRegistradaException("El correo electrónico ya está registrado.");
        }

        if (verificador.nitYaRegistrado(usuario)) {
            throw new EntidadYaRegistradaException("El NIT ya está registrado.");
        }

        if (verificador.dpiYaRegistrado(usuario)) {
            throw new EntidadYaRegistradaException("El DPI ya está registrado.");
        }

        return usuarioDAO.crearUsuario(usuario);
    }

}
