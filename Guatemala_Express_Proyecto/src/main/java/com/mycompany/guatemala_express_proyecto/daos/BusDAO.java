/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.daos;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mycompany.guatemala_express_proyecto.enums.EstadoOperativo;
import com.mycompany.guatemala_express_proyecto.modelos.Bus;
import com.mycompany.guatemala_express_proyecto.modelos.Sucursal;
import com.mycompany.guatemala_express_proyecto.util.ConexionDB;

/**
 *
 * @author matul
 */
public class BusDAO {

    public boolean registrarBus(Bus bus) throws SQLException {

        String sql = "INSERT INTO bus (numero_placa, id_sucursal, marca, modelo, anio_fabricacion, capacidad_pasajeros, kilometraje_actual, fotografia) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection coneccion = ConexionDB.getConeccion();
                PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {

            preparedStatement.setString(1, bus.getNumeroPlaca());
            preparedStatement.setInt(2, bus.getSucursal().getId());
            preparedStatement.setString(3, bus.getMarca());
            preparedStatement.setString(4, bus.getModelo());
            preparedStatement.setInt(5, bus.getAnioFabricacion());
            preparedStatement.setInt(6, bus.getCapacidadPasajeros());
            preparedStatement.setBigDecimal(7, bus.getKilometrajeActual());
            preparedStatement.setBytes(8, bus.getFotografia());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new SQLException("Error al registrar el bus: " + e.getMessage());
        }
    }

    public boolean actualizarBus(Bus bus) throws SQLException {

        boolean actualizarFotografia = bus.getFotografia() != null && bus.getFotografia().length > 0;

        String sql;

        if (actualizarFotografia) {
            sql = "UPDATE bus SET id_sucursal = ?, marca = ?, modelo = ?, anio_fabricacion = ?, capacidad_pasajeros = ?, fotografia = ? WHERE numero_placa = ?";
        } else {
            sql = "UPDATE bus SET id_sucursal = ?, marca = ?, modelo = ?, anio_fabricacion = ?, capacidad_pasajeros = ? WHERE numero_placa = ?";
        }

        try (Connection coneccion = ConexionDB.getConeccion();
                PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {

            preparedStatement.setInt(1, bus.getSucursal().getId());
            preparedStatement.setString(2, bus.getMarca());
            preparedStatement.setString(3, bus.getModelo());
            preparedStatement.setInt(4, bus.getAnioFabricacion());
            preparedStatement.setInt(5, bus.getCapacidadPasajeros());

            if (actualizarFotografia) {
                preparedStatement.setBytes(6, bus.getFotografia());
                preparedStatement.setString(7, bus.getNumeroPlaca());
            } else {
                preparedStatement.setString(6, bus.getNumeroPlaca());
            }

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new SQLException(
                    "Error al actualizar el bus: " + e.getMessage());
        }
    }

    public List<Bus> obtenerBuses() throws SQLException {

        List<Bus> buses = new ArrayList<>();

        String sqlSelect = "SELECT b.numero_placa, b.id_sucursal, b.marca, b.modelo, b.anio_fabricacion, b.capacidad_pasajeros, b.kilometraje_actual, b.estado, b.fotografia, b.estado_operativo, s.nombre ";
        String sqlFrom = "FROM bus b ";
        String inner = "INNER JOIN sucursal s ON b.id_sucursal = s.id";
        String sql = sqlSelect + sqlFrom + inner;

        try (Connection coneccion = ConexionDB.getConeccion();
                PreparedStatement preparedStatement = coneccion.prepareStatement(sql);
                ResultSet result = preparedStatement.executeQuery()) {

            while (result.next()) {

                Sucursal sucursal = new Sucursal();
                sucursal.setId(result.getInt("id_sucursal"));
                sucursal.setNombre(result.getString("nombre"));

                Bus bus = new Bus();
                bus.setNumeroPlaca(result.getString("numero_placa"));
                bus.setSucursal(sucursal);
                bus.setMarca(result.getString("marca"));
                bus.setModelo(result.getString("modelo"));
                bus.setAnioFabricacion(result.getInt("anio_fabricacion"));
                bus.setCapacidadPasajeros(result.getInt("capacidad_pasajeros"));
                bus.setKilometrajeActual(result.getBigDecimal("kilometraje_actual"));
                bus.setEstado(result.getBoolean("estado"));
                bus.setFotografia(result.getBytes("fotografia"));
                bus.setEstadoOperativo(EstadoOperativo.valueOf(result.getString("estado_operativo")));
                buses.add(bus);
            }

        } catch (SQLException e) {
            throw new SQLException("Error al obtener los buses: " + e.getMessage());
        }

        return buses;
    }

    public Optional<Bus> obtenerBusPorNumeroPlaca(String numeroPlaca) throws SQLException {
        String sqlSelect = "SELECT b.numero_placa, b.id_sucursal, b.marca, b.modelo, b.anio_fabricacion, b.capacidad_pasajeros, b.kilometraje_actual, b.estado, b.estado_operativo, s.nombre ";
        String sqlFrom = "FROM bus b ";
        String innerWhere = "INNER JOIN sucursal s ON b.id_sucursal = s.id WHERE b.numero_placa = ?";
        String sql = sqlSelect + sqlFrom + innerWhere;

        try (Connection coneccion = ConexionDB.getConeccion();
                PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {

            preparedStatement.setString(1, numeroPlaca);

            try (ResultSet result = preparedStatement.executeQuery()) {

                if (result.next()) {

                    Sucursal sucursal = new Sucursal();
                    sucursal.setId(result.getInt("id_sucursal"));
                    sucursal.setNombre(result.getString("nombre"));

                    Bus bus = new Bus();
                    bus.setNumeroPlaca(result.getString("numero_placa"));
                    bus.setSucursal(sucursal);
                    bus.setMarca(result.getString("marca"));
                    bus.setModelo(result.getString("modelo"));
                    bus.setAnioFabricacion(result.getInt("anio_fabricacion"));
                    bus.setCapacidadPasajeros(result.getInt("capacidad_pasajeros"));
                    bus.setKilometrajeActual(result.getBigDecimal("kilometraje_actual"));
                    bus.setEstado(result.getBoolean("estado"));
                    bus.setEstadoOperativo(EstadoOperativo.valueOf(result.getString("estado_operativo")));

                    return Optional.of(bus);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener el bus: " + e.getMessage());
        }

        return Optional.empty();
    }

    public boolean actualizarEstadoBus(String numeroPlaca, boolean estado) throws SQLException {
        String sql = "UPDATE bus SET estado = ? WHERE numero_placa = ?";

        try (Connection coneccion = ConexionDB.getConeccion();
                PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {
            preparedStatement.setBoolean(1, estado);
            preparedStatement.setString(2, numeroPlaca);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new SQLException("Error al actualizar el estado del bus: " + e.getMessage());
        }
    }

    public boolean actualizarEstadoOperativoBus(String numeroPlaca, EstadoOperativo estadoOperativo)
            throws SQLException {

        String sql = "UPDATE bus SET estado_operativo = ? WHERE numero_placa = ?";

        try (Connection coneccion = ConexionDB.getConeccion();
                PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {
            preparedStatement.setString(1, estadoOperativo.name());
            preparedStatement.setString(2, numeroPlaca);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new SQLException("Error al actualizar el estado operativo del bus: " + e.getMessage());
        }
    }

    public List<Bus> obtenerBusesPorSucursal(int idSucursal) throws SQLException {

        List<Bus> buses = new ArrayList<>();

        String sqlSelect = "SELECT b.numero_placa, b.id_sucursal, b.marca, b.modelo, b.anio_fabricacion, b.capacidad_pasajeros, b.kilometraje_actual, b.estado, b.fotografia, b.estado_operativo, s.nombre ";
        String sqlFrom = "FROM bus b ";
        String sqlInnerWhere = "INNER JOIN sucursal s ON b.id_sucursal = s.id WHERE b.id_sucursal = ?";
        String sql = sqlSelect + sqlFrom + sqlInnerWhere;

        try (Connection coneccion = ConexionDB.getConeccion();
                PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {

            preparedStatement.setInt(1, idSucursal);

            try (ResultSet result = preparedStatement.executeQuery()) {
                while (result.next()) {

                    Sucursal sucursal = new Sucursal();
                    sucursal.setId(result.getInt("id_sucursal"));
                    sucursal.setNombre(result.getString("nombre"));

                    Bus bus = new Bus();
                    bus.setNumeroPlaca(result.getString("numero_placa"));
                    bus.setSucursal(sucursal);
                    bus.setMarca(result.getString("marca"));
                    bus.setModelo(result.getString("modelo"));
                    bus.setAnioFabricacion(result.getInt("anio_fabricacion"));
                    bus.setCapacidadPasajeros(result.getInt("capacidad_pasajeros"));
                    bus.setKilometrajeActual(result.getBigDecimal("kilometraje_actual"));
                    bus.setEstado(result.getBoolean("estado"));
                    bus.setFotografia(result.getBytes("fotografia"));
                    bus.setEstadoOperativo(EstadoOperativo.valueOf(result.getString("estado_operativo")));

                    buses.add(bus);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener los buses de la sucursal: " + e.getMessage());
        }

        return buses;
    }

    public boolean sumarKilometrajeBus(String numeroPlaca, BigDecimal kilometrajeRecorrido) throws SQLException {
        String sql = "UPDATE bus SET kilometraje_actual = kilometraje_actual + ? WHERE numero_placa = ?";

        try (Connection coneccion = ConexionDB.getConeccion();
                PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {
            preparedStatement.setBigDecimal(1, kilometrajeRecorrido);
            preparedStatement.setString(2, numeroPlaca);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new SQLException("Error al sumar el kilometraje del bus: " + e.getMessage());
        }
    }

}
