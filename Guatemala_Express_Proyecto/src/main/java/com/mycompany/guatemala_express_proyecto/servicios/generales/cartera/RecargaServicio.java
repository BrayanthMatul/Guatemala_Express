/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.servicios.generales.cartera;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import com.mycompany.guatemala_express_proyecto.daos.RecargaDAO;
import com.mycompany.guatemala_express_proyecto.daos.UsuarioDAO;
import com.mycompany.guatemala_express_proyecto.exceptions.DatoInvalidoException;
import com.mycompany.guatemala_express_proyecto.modelos.Recarga;
import com.mycompany.guatemala_express_proyecto.util.ConexionDB;

/**
 *
 * @author matul
 */
public class RecargaServicio {

    private final RecargaDAO recargaDAO;
    private final UsuarioDAO usuarioDAO;

    public RecargaServicio() {
        this.recargaDAO = new RecargaDAO();
        this.usuarioDAO = new UsuarioDAO();
    }

    public void guardarRecarga(Recarga recarga) throws DatoInvalidoException, SQLException {
        verificarDatosRecarga(recarga);

        realizarTransaccionRecarga(recarga);
    }

    public void verificarDatosRecarga(Recarga recarga) throws DatoInvalidoException {

        if (recarga.getMonto().compareTo(BigDecimal.ZERO) <= 0) {
            throw new DatoInvalidoException("El monto de la recarga debe ser mayor a cero.");
        }

        if (recarga.getFechaHoraRecarga() == null ||
                recarga.getFechaHoraRecarga().toLocalDate().isBefore(LocalDate.now())) {

            throw new DatoInvalidoException("La fecha no puede ser anterior al día de hoy.");
        }
    }

    private void realizarTransaccionRecarga(Recarga recarga) throws SQLException {

        try (Connection connection = ConexionDB.getConeccion()) {
            connection.setAutoCommit(false);

            try {
                recargaDAO.insertarRecarga(recarga, connection);
                usuarioDAO.sumarSaldo(recarga.getIdUsuario(), recarga.getMonto(), connection);
                connection.commit();
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackException) {
                    e.addSuppressed(rollbackException);
                }

                throw new SQLException("Error al realizar la transacción de recarga: ", e);
            }
        }
    }

}
