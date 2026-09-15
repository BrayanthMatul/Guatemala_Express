/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.servicios.administrador_sucursal;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.mycompany.guatemala_express_proyecto.daos.AdministradorSucursalDAO;
import com.mycompany.guatemala_express_proyecto.daos.UsuarioDAO;
import com.mycompany.guatemala_express_proyecto.enums.Rol;
import com.mycompany.guatemala_express_proyecto.exceptions.DatosIncompletosException;
import com.mycompany.guatemala_express_proyecto.exceptions.EntidadYaRegistradaException;
import com.mycompany.guatemala_express_proyecto.exceptions.NoGuardadoEnBDException;
import com.mycompany.guatemala_express_proyecto.modelos.AdministradorSucursal;
import com.mycompany.guatemala_express_proyecto.modelos.Usuario;
import com.mycompany.guatemala_express_proyecto.servicios.usuarios.VerificadorDatosUsuarioServicio;
import com.mycompany.guatemala_express_proyecto.util.ConexionDB;

/**
 *
 * @author matul
 */
public class AdministradorSucursalServicio {

    private final UsuarioDAO usuarioDAO;

    private final AdministradorSucursalDAO administradorSucursalDAO;

    private final VerificadorDatosUsuarioServicio verificador;

    public AdministradorSucursalServicio() {
        this.usuarioDAO = new UsuarioDAO();
        this.administradorSucursalDAO = new AdministradorSucursalDAO();
        this.verificador = new VerificadorDatosUsuarioServicio();
    }

    public boolean crearAdministradorSucursal(AdministradorSucursal administradorSucursal)
            throws DatosIncompletosException, EntidadYaRegistradaException, NoGuardadoEnBDException, SQLException {

        if (administradorSucursal == null || administradorSucursal.getSucursal() == null
                || administradorSucursal.getSucursal().getId() <= 0) {
            throw new DatosIncompletosException("Debe seleccionar una sucursal.");
        }

        Usuario usuario = administradorSucursal.getUsuario();

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

        usuario.setRol(Rol.ADMINISTRADOR_SUCURSAL);

        try (Connection coneccion = ConexionDB.getConeccion()) {
            coneccion.setAutoCommit(false);

            try {
                boolean usuarioCreado = usuarioDAO.registrarUsuario(coneccion, usuario);
                boolean administradorCreado = administradorSucursalDAO.crearAdministradorSucursal(coneccion,
                        administradorSucursal);

                if (!usuarioCreado || !administradorCreado) {
                    throw new NoGuardadoEnBDException("No fue posible registrar el administrador de sucursal.");
                }

                coneccion.commit();

                return true;

            } catch (SQLException | NoGuardadoEnBDException e) {
                try {
                    coneccion.rollback();
                } catch (SQLException rollbackException) {
                    e.addSuppressed(rollbackException);
                }

                throw e;
            }
        }
    }

    public boolean editarAdministradorSucursal(AdministradorSucursal administradorSucursal)
            throws DatosIncompletosException, EntidadYaRegistradaException, NoGuardadoEnBDException, SQLException {

        if (administradorSucursal == null || administradorSucursal.getSucursal() == null
                || administradorSucursal.getSucursal().getId() <= 0) {
            throw new DatosIncompletosException("Debe seleccionar una sucursal.");
        }

        Usuario usuario = administradorSucursal.getUsuario();

        if (verificador.datosVacios(usuario, false)) {
            throw new DatosIncompletosException("Por favor, complete todos los campos requeridos.");
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

        try (Connection coneccion = ConexionDB.getConeccion()) {
            coneccion.setAutoCommit(false);

            try {
                boolean usuarioActualizado = usuarioDAO.actualizarUsuario(coneccion, usuario);

                boolean administradorActualizado = administradorSucursalDAO.actualizarAdministradorSucursal(coneccion,
                        administradorSucursal);

                if (!usuarioActualizado || !administradorActualizado) {
                    throw new NoGuardadoEnBDException("No fue posible actualizar el administrador " + "de sucursal.");
                }

                coneccion.commit();
                return true;

            } catch (SQLException | NoGuardadoEnBDException e) {
                try {
                    coneccion.rollback();
                } catch (SQLException rollbackException) {
                    e.addSuppressed(rollbackException);
                }

                throw e;
            }
        }
    }

    public List<AdministradorSucursal> obtenerAdministradoresSucursal() throws SQLException {
        return administradorSucursalDAO.obtenerAdministradoresSucursal();
    }

}
