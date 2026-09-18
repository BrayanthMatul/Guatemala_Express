/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.guatemala_express_proyecto.modelos.GastoTaller;
import com.mycompany.guatemala_express_proyecto.modelos.Bus;
import com.mycompany.guatemala_express_proyecto.util.ConexionDB;

/**
 *
 * @author matul
 */
public class GastoTallerDAO {

    public boolean registrarGastoTaller(GastoTaller gastoTaller) throws SQLException {
        String sql = "INSERT INTO gasto_taller (placa_bus, monto_mano_de_obra, monto_repuestos, fecha_mantenimiento) VALUES (?, ?, ?, ?)";

        try (Connection coneccion = ConexionDB.getConeccion();
                PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {
            preparedStatement.setString(1, gastoTaller.getBus().getNumeroPlaca());
            preparedStatement.setBigDecimal(2, gastoTaller.getMontoManoDeObra());
            preparedStatement.setBigDecimal(3, gastoTaller.getMontoRepuestos());
            preparedStatement.setDate(4, Date.valueOf(gastoTaller.getFechaMantenimiento()));

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new SQLException("Error al registrar el gasto de taller: " + e.getMessage(), e);
        }
    }

    public List<GastoTaller> obtenerGastosPorSucursal(int idSucursal) throws SQLException {

        List<GastoTaller> gastosTaller = new ArrayList<>();

        String sqlSelect = "SELECT gasto_taller.id, gasto_taller.placa_bus, gasto_taller.monto_mano_de_obra, gasto_taller.monto_repuestos, gasto_taller.fecha_mantenimiento, bus.marca, bus.modelo ";
        String sqlFrom = "FROM gasto_taller ";
        String sqlJoin = "INNER JOIN bus ON gasto_taller.placa_bus = bus.numero_placa ";
        String sqlWhere = "WHERE bus.id_sucursal = ? ";
        String sqlOrder = "ORDER BY gasto_taller.fecha_mantenimiento DESC, gasto_taller.id DESC";
        String sql = sqlSelect + sqlFrom + sqlJoin + sqlWhere + sqlOrder;

        try (Connection coneccion = ConexionDB.getConeccion();
                PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {

            preparedStatement.setInt(1, idSucursal);

            try (ResultSet result = preparedStatement.executeQuery()) {

                while (result.next()) {
                    Bus bus = new Bus();
                    bus.setNumeroPlaca(result.getString("placa_bus"));
                    bus.setMarca(result.getString("marca"));
                    bus.setModelo(result.getString("modelo"));

                    GastoTaller gastoTaller = new GastoTaller();
                    gastoTaller.setId(result.getInt("id"));
                    gastoTaller.setBus(bus);
                    gastoTaller.setMontoManoDeObra(result.getBigDecimal("monto_mano_de_obra"));
                    gastoTaller.setMontoRepuestos(result.getBigDecimal("monto_repuestos"));
                    gastoTaller.setFechaMantenimiento(result.getDate("fecha_mantenimiento").toLocalDate());

                    gastosTaller.add(gastoTaller);
                }
            }

        } catch (SQLException e) {
            throw new SQLException("Error al obtener los gastos de taller: " + e.getMessage(), e);
        }

        return gastosTaller;
    }

}
