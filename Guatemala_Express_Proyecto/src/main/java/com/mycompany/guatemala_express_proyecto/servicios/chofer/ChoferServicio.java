/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.servicios.chofer;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import com.mycompany.guatemala_express_proyecto.daos.ChoferDAO;
import com.mycompany.guatemala_express_proyecto.daos.UsuarioDAO;
import com.mycompany.guatemala_express_proyecto.enums.Rol;
import com.mycompany.guatemala_express_proyecto.exceptions.DatosIncompletosException;
import com.mycompany.guatemala_express_proyecto.exceptions.EntidadYaRegistradaException;
import com.mycompany.guatemala_express_proyecto.exceptions.NoGuardadoEnBDException;
import com.mycompany.guatemala_express_proyecto.modelos.Chofer;
import com.mycompany.guatemala_express_proyecto.modelos.Usuario;
import com.mycompany.guatemala_express_proyecto.servicios.usuarios.VerificadorDatosUsuarioServicio;
import com.mycompany.guatemala_express_proyecto.util.ConexionDB;

/**
 *
 * @author matul
 */
public class ChoferServicio {
    private final UsuarioDAO usuarioDAO;
    private final ChoferDAO choferDAO;
    private final VerificadorDatosUsuarioServicio verificadorUsuario;

    public ChoferServicio() {
        this.usuarioDAO = new UsuarioDAO();
        this.choferDAO = new ChoferDAO();
        this.verificadorUsuario = new VerificadorDatosUsuarioServicio();
    }

    public boolean registrarChofer(Chofer chofer)
            throws DatosIncompletosException, EntidadYaRegistradaException, NoGuardadoEnBDException, SQLException {

        if (chofer == null || verificadorUsuario.datosVacios(chofer.getUsuario(), true)
                || datosChoferVacios(chofer, true)) {
            throw new DatosIncompletosException("Por favor, complete todos los campos requeridos.");
        }

        Usuario usuario = chofer.getUsuario();

        if (verificadorUsuario.nombreUsuarioYaRegistrado(usuario)) {
            throw new EntidadYaRegistradaException("El nombre de usuario ya está registrado.");
        }

        if (verificadorUsuario.correoYaRegistrado(usuario)) {
            throw new EntidadYaRegistradaException("El correo electrónico ya está registrado.");
        }

        if (verificadorUsuario.nitYaRegistrado(usuario)) {
            throw new EntidadYaRegistradaException("El NIT ya está registrado.");
        }

        if (verificadorUsuario.dpiYaRegistrado(usuario)) {
            throw new EntidadYaRegistradaException("El DPI ya está registrado.");
        }

        if (numeroLicenciaYaRegistrado(chofer)) {
            throw new EntidadYaRegistradaException("El número de licencia ya está registrado.");
        }

        usuario.setRol(Rol.CHOFER);

        try (Connection coneccion = ConexionDB.getConeccion()) {
            coneccion.setAutoCommit(false);

            try {

                boolean usuarioRegistrado = usuarioDAO.registrarUsuario(coneccion, usuario);
                boolean choferRegistrado = choferDAO.registrarChofer(coneccion, chofer);

                if (!usuarioRegistrado || !choferRegistrado) {
                    throw new NoGuardadoEnBDException("No fue posible registrar el chofer.");
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

    public boolean actualizarChofer(Chofer chofer)
            throws DatosIncompletosException, EntidadYaRegistradaException, NoGuardadoEnBDException, SQLException {

        if (chofer == null || verificadorUsuario.datosVacios(chofer.getUsuario(), false)
                || datosChoferVacios(chofer, false)) {
            throw new DatosIncompletosException("Por favor, complete todos los campos requeridos.");
        }

        Usuario usuario = chofer.getUsuario();

        if (verificadorUsuario.correoYaRegistrado(usuario)) {
            throw new EntidadYaRegistradaException("El correo electrónico ya está registrado.");
        }

        if (verificadorUsuario.nitYaRegistrado(usuario)) {
            throw new EntidadYaRegistradaException("El NIT ya está registrado.");
        }

        if (verificadorUsuario.dpiYaRegistrado(usuario)) {
            throw new EntidadYaRegistradaException("El DPI ya está registrado.");
        }

        if (numeroLicenciaYaRegistrado(chofer)) {
            throw new EntidadYaRegistradaException("El número de licencia ya está registrado.");
        }

        try (Connection coneccion = ConexionDB.getConeccion()) {

            coneccion.setAutoCommit(false);

            try {

                boolean usuarioActualizado = usuarioDAO.actualizarUsuario(coneccion, usuario);
                boolean choferActualizado = choferDAO.actualizarChofer(coneccion, chofer);

                if (!usuarioActualizado || !choferActualizado) {
                    throw new NoGuardadoEnBDException("No fue posible actualizar el chofer.");
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

    public List<Chofer> obtenerChoferesPorSucursal(int idSucursal) throws SQLException {

        List<Chofer> choferes = choferDAO.obtenerChoferesPorSucursal(idSucursal);

        for (Chofer chofer : choferes) {
            if (chofer.getFotografia() != null && chofer.getFotografia().length > 0) {
                chofer.setFotografiaBase64(Base64.getEncoder().encodeToString(chofer.getFotografia()));
            }
        }

        return choferes;
    }

    public Optional<Chofer> obtenerChoferPorNombreUsuario(String nombreUsuario) throws SQLException {
        return choferDAO.obtenerChoferPorNombreUsuario(nombreUsuario);
    }

    private boolean numeroLicenciaYaRegistrado(Chofer chofer) throws SQLException {
        Optional<Chofer> choferExistente = choferDAO.obtenerChoferPorNumeroLicencia(chofer.getNumeroLicencia());

        return choferExistente.isPresent() && !choferExistente.get().getUsuario().getNombreUsuario()
                .equals(chofer.getUsuario().getNombreUsuario());
    }

    private boolean datosChoferVacios(Chofer chofer, boolean revisarFotografia) {

        if (chofer.getSucursal() == null || chofer.getSucursal().getId() <= 0) {
            return true;
        }

        if (chofer.getNumeroLicencia() == null || chofer.getNumeroLicencia().isBlank()) {
            return true;
        }

        if (chofer.getTipoLicencia() == null || chofer.getTipoLicencia().isBlank()) {
            return true;
        }

        if (chofer.getFechaVencimientoLicencia() == null) {
            return true;
        }

        if (chofer.getSalarioBasePorViaje() == null
                || chofer.getSalarioBasePorViaje().compareTo(BigDecimal.ZERO) <= 0) {
            return true;
        }

        if (revisarFotografia && (chofer.getFotografia() == null || chofer.getFotografia().length == 0)) {
            return true;
        }

        return false;
    }

}
