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

import com.mycompany.guatemala_express_proyecto.exceptions.NoGuardadoEnBDException;
import com.mycompany.guatemala_express_proyecto.modelos.Sucursal;
import com.mycompany.guatemala_express_proyecto.util.ConexionDB;

/**
 *
 * @author matul
 */
public class SucursalDAO {

    public boolean crearSucursal(Sucursal sucursal) throws NoGuardadoEnBDException {
        String sql = "INSERT INTO sucursal (nombre, departamento, municipio, longitud, latitud, telefono, fecha_apertura) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection coneccion = ConexionDB.getConeccion();
                PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {

            preparedStatement.setString(1, sucursal.getNombre());
            preparedStatement.setString(2, sucursal.getDepartamento());
            preparedStatement.setString(3, sucursal.getMunicipio());
            preparedStatement.setBigDecimal(4, sucursal.getLongitud());
            preparedStatement.setBigDecimal(5, sucursal.getLatitud());
            preparedStatement.setString(6, sucursal.getTelefono());
            preparedStatement.setDate(7, java.sql.Date.valueOf(sucursal.getFechaApertura()));

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new NoGuardadoEnBDException(
                    "Error al crear la sucursal: " + e.getMessage());
        }
    }

    public boolean actualizarSucursal(Sucursal sucursal) throws SQLException {
        String sql = "UPDATE sucursal SET nombre = ?, departamento = ?, municipio = ?, longitud = ?, latitud = ?, telefono = ? WHERE id = ?";

        try (Connection coneccion = ConexionDB.getConeccion();
                PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {

            preparedStatement.setString(1, sucursal.getNombre());
            preparedStatement.setString(2, sucursal.getDepartamento());
            preparedStatement.setString(3, sucursal.getMunicipio());
            preparedStatement.setBigDecimal(4, sucursal.getLongitud());
            preparedStatement.setBigDecimal(5, sucursal.getLatitud());
            preparedStatement.setString(6, sucursal.getTelefono());
            preparedStatement.setInt(7, sucursal.getId());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new SQLException(
                    "Error al actualizar la sucursal: " + e.getMessage());
        }
    }

    public Optional<Sucursal> obtenerSucursalPorId(int id) throws SQLException {
        String sql = "SELECT * FROM sucursal WHERE id = ?";

        try (Connection coneccion = ConexionDB.getConeccion();
                PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            try (ResultSet result = preparedStatement.executeQuery()) {
                if (result.next()) {
                    Sucursal sucursal = new Sucursal();

                    sucursal.setId(result.getInt("id"));
                    sucursal.setNombre(result.getString("nombre"));
                    sucursal.setDepartamento(result.getString("departamento"));
                    sucursal.setMunicipio(result.getString("municipio"));
                    sucursal.setLongitud(result.getBigDecimal("longitud"));
                    sucursal.setLatitud(result.getBigDecimal("latitud"));
                    sucursal.setTelefono(result.getString("telefono"));
                    sucursal.setFechaApertura(result.getDate("fecha_apertura").toLocalDate());
                    return Optional.of(sucursal);
                }
            }

        } catch (SQLException e) {
            throw new SQLException(
                    "Error al obtener la sucursal por ID: " + e.getMessage());
        }

        return Optional.empty();
    }

    public List<Sucursal> obtenerSucursales() throws SQLException {
        List<Sucursal> sucursales = new ArrayList<>();

        String sql = "SELECT * FROM sucursal";

        try (Connection coneccion = ConexionDB.getConeccion();
                PreparedStatement preparedStatement = coneccion.prepareStatement(sql);
                ResultSet result = preparedStatement.executeQuery()) {

            while (result.next()) {
                Sucursal sucursal = new Sucursal();

                sucursal.setId(result.getInt("id"));
                sucursal.setNombre(result.getString("nombre"));
                sucursal.setDepartamento(result.getString("departamento"));
                sucursal.setMunicipio(result.getString("municipio"));
                sucursal.setLongitud(result.getBigDecimal("longitud"));
                sucursal.setLatitud(result.getBigDecimal("latitud"));
                sucursal.setTelefono(result.getString("telefono"));
                sucursal.setFechaApertura(result.getDate("fecha_apertura").toLocalDate());
                sucursales.add(sucursal);
            }

        } catch (SQLException e) {
            throw new SQLException(
                    "Error al obtener las sucursales: " + e.getMessage());
        }

        return sucursales;
    }

    public Optional<Sucursal> obtenerSucursalPorNombre(String nombre)
            throws SQLException {

        String sql = "SELECT * FROM sucursal WHERE nombre = ?";

        try (Connection coneccion = ConexionDB.getConeccion();
                PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {

            preparedStatement.setString(1, nombre);

            try (ResultSet result = preparedStatement.executeQuery()) {
                if (result.next()) {
                    Sucursal sucursal = new Sucursal();

                    sucursal.setId(result.getInt("id"));
                    sucursal.setNombre(result.getString("nombre"));
                    sucursal.setDepartamento(result.getString("departamento"));
                    sucursal.setMunicipio(result.getString("municipio"));
                    sucursal.setLongitud(result.getBigDecimal("longitud"));
                    sucursal.setLatitud(result.getBigDecimal("latitud"));
                    sucursal.setTelefono(result.getString("telefono"));
                    sucursal.setFechaApertura(
                            result.getDate("fecha_apertura").toLocalDate());

                    return Optional.of(sucursal);
                }
            }

        } catch (SQLException e) {
            throw new SQLException(
                    "Error al obtener la sucursal por nombre: "
                            + e.getMessage());
        }

        return Optional.empty();
    }

    public Optional<Sucursal> obtenerSucursalPorTelefono(String telefono)
            throws SQLException {

        String sql = "SELECT * FROM sucursal WHERE telefono = ?";

        try (Connection coneccion = ConexionDB.getConeccion();
                PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {

            preparedStatement.setString(1, telefono);

            try (ResultSet result = preparedStatement.executeQuery()) {
                if (result.next()) {
                    Sucursal sucursal = new Sucursal();

                    sucursal.setId(result.getInt("id"));
                    sucursal.setNombre(result.getString("nombre"));
                    sucursal.setDepartamento(result.getString("departamento"));
                    sucursal.setMunicipio(result.getString("municipio"));
                    sucursal.setLongitud(result.getBigDecimal("longitud"));
                    sucursal.setLatitud(result.getBigDecimal("latitud"));
                    sucursal.setTelefono(result.getString("telefono"));
                    sucursal.setFechaApertura(
                            result.getDate("fecha_apertura").toLocalDate());

                    return Optional.of(sucursal);
                }
            }

        } catch (SQLException e) {
            throw new SQLException(
                    "Error al obtener la sucursal por teléfono: "
                            + e.getMessage());
        }

        return Optional.empty();
    }

    public Optional<Sucursal> obtenerSucursalPorCoordenadas(
            BigDecimal longitud, BigDecimal latitud) throws SQLException {

        String sql = "SELECT * FROM sucursal "
                + "WHERE longitud = ? AND latitud = ?";

        try (Connection coneccion = ConexionDB.getConeccion();
                PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {

            preparedStatement.setBigDecimal(1, longitud);
            preparedStatement.setBigDecimal(2, latitud);

            try (ResultSet result = preparedStatement.executeQuery()) {
                if (result.next()) {
                    Sucursal sucursal = new Sucursal();

                    sucursal.setId(result.getInt("id"));
                    sucursal.setNombre(result.getString("nombre"));
                    sucursal.setDepartamento(result.getString("departamento"));
                    sucursal.setMunicipio(result.getString("municipio"));
                    sucursal.setLongitud(result.getBigDecimal("longitud"));
                    sucursal.setLatitud(result.getBigDecimal("latitud"));
                    sucursal.setTelefono(result.getString("telefono"));
                    sucursal.setFechaApertura(
                            result.getDate("fecha_apertura").toLocalDate());

                    return Optional.of(sucursal);
                }
            }

        } catch (SQLException e) {
            throw new SQLException(
                    "Error al obtener la sucursal por coordenadas: "
                            + e.getMessage());
        }

        return Optional.empty();
    }

}
