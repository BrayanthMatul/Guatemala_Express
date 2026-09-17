/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mycompany.guatemala_express_proyecto.modelos.RutaRegular;
import com.mycompany.guatemala_express_proyecto.modelos.Sucursal;
import com.mycompany.guatemala_express_proyecto.util.ConexionDB;

/**
 *
 * @author matul
 */
public class RutaRegularDAO {

    public boolean registrarNuevaRuta(RutaRegular ruta) throws SQLException {
        String sql = "INSERT INTO ruta_regular (id_sucursal_origen, id_sucursal_destino, distancia_aproximada_km, precio_boleto, duracion_estimada) VALUES (?, ?, ?, ?, ?)";

        try (Connection coneccion = ConexionDB.getConeccion();
                PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {
            preparedStatement.setInt(1, ruta.getSucursalOrigen().getId());
            preparedStatement.setInt(2, ruta.getSucursalDestino().getId());
            preparedStatement.setBigDecimal(3, ruta.getDistanciaAproximadaKm());
            preparedStatement.setBigDecimal(4, ruta.getPrecioBoleto());
            preparedStatement.setTime(5, Time.valueOf(ruta.getDuracionEstimada()));

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new SQLException("Error al registrar la ruta: " + e.getMessage());
        }
    }

    public boolean actualizarRuta(RutaRegular ruta) throws SQLException {
        String sql = "UPDATE ruta_regular SET id_sucursal_destino = ?, distancia_aproximada_km = ?, precio_boleto = ?, duracion_estimada = ? WHERE id = ?";

        try (Connection coneccion = ConexionDB.getConeccion();
                PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {
            preparedStatement.setInt(1, ruta.getSucursalDestino().getId());
            preparedStatement.setBigDecimal(2, ruta.getDistanciaAproximadaKm());
            preparedStatement.setBigDecimal(3, ruta.getPrecioBoleto());
            preparedStatement.setTime(4, Time.valueOf(ruta.getDuracionEstimada()));
            preparedStatement.setInt(5, ruta.getId());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new SQLException("Error al actualizar la ruta: " + e.getMessage());
        }
    }

    public Optional<RutaRegular> obtenerRutaPorId(int id) throws SQLException {
        String sqlSelect = "SELECT r.id, so.id AS id_origen, so.nombre AS nombre_origen, sd.id AS id_destino, sd.nombre AS nombre_destino, r.distancia_aproximada_km, r.precio_boleto, r.duracion_estimada, r.estado ";
        String sqlFrom = "FROM ruta_regular r ";
        String sqlJoin = "INNER JOIN sucursal so ON r.id_sucursal_origen = so.id INNER JOIN sucursal sd ON r.id_sucursal_destino = sd.id WHERE r.id = ?";
        String sql = sqlSelect + sqlFrom + sqlJoin;

        try (Connection coneccion = ConexionDB.getConeccion();
                PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            try (ResultSet result = preparedStatement.executeQuery()) {
                if (result.next()) {
                    return Optional.of(construirRuta(result));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener la ruta: " + e.getMessage());
        }

        return Optional.empty();
    }

    public boolean actualizarEstado(int id, boolean nuevoEstado) throws SQLException {
        String sql = "UPDATE ruta_regular SET estado = ? WHERE id = ?";

        try (Connection coneccion = ConexionDB.getConeccion();
                PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {

            preparedStatement.setBoolean(1, nuevoEstado);
            preparedStatement.setInt(2, id);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new SQLException("Error al actualizar el estado de la ruta: " + e.getMessage());
        }
    }

    public List<RutaRegular> obtenerRutasPorSucursal(
            int idSucursal) throws SQLException {

        List<RutaRegular> rutas = new ArrayList<>();

        String sqlSelect = "SELECT r.id, so.id AS id_origen, so.nombre AS nombre_origen, sd.id AS id_destino, sd.nombre AS nombre_destino, r.distancia_aproximada_km, r.precio_boleto, r.duracion_estimada, r.estado ";
        String sqlFrom = "FROM ruta_regular r ";
        String sqlJoin = "INNER JOIN sucursal so ON r.id_sucursal_origen = so.id INNER JOIN sucursal sd ON r.id_sucursal_destino = sd.id WHERE r.id_sucursal_origen = ?";
        String sql = sqlSelect + sqlFrom + sqlJoin;

        try (Connection coneccion = ConexionDB.getConeccion();
                PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {

            preparedStatement.setInt(1, idSucursal);

            try (ResultSet result = preparedStatement.executeQuery()) {

                while (result.next()) {
                    rutas.add(construirRuta(result));
                }
            }

        } catch (SQLException e) {
            throw new SQLException("Error al obtener las rutas de la sucursal: " + e.getMessage());
        }

        return rutas;
    }

    public Optional<RutaRegular> obtenerRutaPorOrigenYDestino(int idSucursalOrigen, int idSucursalDestino)
            throws SQLException {

        String sql = "SELECT id FROM ruta_regular WHERE id_sucursal_origen = ? AND id_sucursal_destino = ?";

        try (Connection coneccion = ConexionDB.getConeccion();
                PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {

            preparedStatement.setInt(1, idSucursalOrigen);
            preparedStatement.setInt(2, idSucursalDestino);

            try (ResultSet result = preparedStatement.executeQuery()) {

                if (result.next()) {
                    RutaRegular ruta = new RutaRegular();
                    ruta.setId(result.getInt("id"));

                    return Optional.of(ruta);
                }
            }

        } catch (SQLException e) {
            throw new SQLException("Error al verificar la ruta: " + e.getMessage());
        }

        return Optional.empty();
    }

    private RutaRegular construirRuta(ResultSet result) throws SQLException {
        Sucursal sucursalOrigen = new Sucursal();
        sucursalOrigen.setId(result.getInt("id_origen"));
        sucursalOrigen.setNombre(result.getString("nombre_origen"));

        Sucursal sucursalDestino = new Sucursal();
        sucursalDestino.setId(result.getInt("id_destino"));
        sucursalDestino.setNombre(result.getString("nombre_destino"));

        RutaRegular ruta = new RutaRegular();
        ruta.setId(result.getInt("id"));
        ruta.setSucursalOrigen(sucursalOrigen);
        ruta.setSucursalDestino(sucursalDestino);
        ruta.setDistanciaAproximadaKm(result.getBigDecimal("distancia_aproximada_km"));
        ruta.setPrecioBoleto(result.getBigDecimal("precio_boleto"));
        ruta.setDuracionEstimada(result.getTime("duracion_estimada").toLocalTime());
        ruta.setEstado(result.getBoolean("estado"));

        return ruta;
    }

}
