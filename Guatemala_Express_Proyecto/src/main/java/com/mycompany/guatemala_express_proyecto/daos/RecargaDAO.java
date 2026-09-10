/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mycompany.guatemala_express_proyecto.modelos.Recarga;

/**
 *
 * @author matul
 */
public class RecargaDAO {

    public void insertarRecarga(Recarga recarga, Connection coneccion) throws SQLException {
        String sql = "INSERT INTO recarga (id_usuario, fecha_hora_recarga, monto) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {
            preparedStatement.setInt(1, recarga.getIdUsuario());
            preparedStatement.setObject(2, recarga.getFechaHoraRecarga());
            preparedStatement.setBigDecimal(3, recarga.getMonto());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al insertar la recarga en la base de datos: " + e.getMessage(), e);
        }
    }

}

// CREATE TABLE

// recarga (
// id INT PRIMARY KEY AUTO_INCREMENT,
// id_usuario INT NOT NULL,
// fecha_hora_recarga DATETIME NOT NULL,

// monto DECIMAL(10, 2) NOT NULL,

// FOREIGN KEY (id_usuario)

// REFERENCES usuario(id)
// );