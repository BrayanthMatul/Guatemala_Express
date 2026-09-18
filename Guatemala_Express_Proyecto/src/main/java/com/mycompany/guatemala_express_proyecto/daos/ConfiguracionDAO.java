/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.mycompany.guatemala_express_proyecto.modelos.Configuracion;
import com.mycompany.guatemala_express_proyecto.util.ConexionDB;

/**
 *
 * @author matul
 */
public class ConfiguracionDAO {

    public Optional<Configuracion> obtenerConfiguracionPorId(int id) throws SQLException {

        String sql = "SELECT id, descripcion, valor FROM configuracion WHERE id = ?";

        try (Connection coneccion = ConexionDB.getConeccion();
                PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            try (ResultSet result = preparedStatement.executeQuery()) {

                if (result.next()) {
                    Configuracion configuracion = new Configuracion();
                    configuracion.setId(result.getInt("id"));
                    configuracion.setDescripcion(result.getString("descripcion"));
                    configuracion.setValor(result.getBigDecimal("valor"));

                    return Optional.of(configuracion);
                }
            }

        } catch (SQLException e) {

            throw new SQLException(
                    "Error al obtener la configuración: "
                            + e.getMessage(),
                    e);
        }

        return Optional.empty();
    }

    public boolean actualizarValor(Configuracion configuracion)
            throws SQLException {

        String sql = "UPDATE configuracion SET valor = ? WHERE id = ?";

        try (Connection coneccion = ConexionDB.getConeccion();
                PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {
            preparedStatement.setBigDecimal(1, configuracion.getValor());
            preparedStatement.setInt(2, configuracion.getId());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new SQLException("Error al actualizar la configuración: " + e.getMessage(), e);
        }
    }

}
