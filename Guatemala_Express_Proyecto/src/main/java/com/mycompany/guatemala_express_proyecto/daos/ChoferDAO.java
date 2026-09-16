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
import java.util.Optional;

import com.mycompany.guatemala_express_proyecto.modelos.Chofer;
import com.mycompany.guatemala_express_proyecto.modelos.Sucursal;
import com.mycompany.guatemala_express_proyecto.modelos.Usuario;
import com.mycompany.guatemala_express_proyecto.util.ConexionDB;

/**
 *
 * @author matul
 */
public class ChoferDAO {

    public boolean registrarChofer(Connection coneccion, Chofer chofer) throws SQLException {

        String sql = "INSERT INTO chofer (nombre_usuario, id_sucursal, fotografia, numero_licencia, tipo_licencia, fecha_vencimiento_licencia, salario_base_por_viaje) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {
            preparedStatement.setString(1, chofer.getUsuario().getNombreUsuario());
            preparedStatement.setInt(2, chofer.getSucursal().getId());
            preparedStatement.setBytes(3, chofer.getFotografia());
            preparedStatement.setString(4, chofer.getNumeroLicencia());
            preparedStatement.setString(5, chofer.getTipoLicencia());
            preparedStatement.setDate(6, Date.valueOf(chofer.getFechaVencimientoLicencia()));
            preparedStatement.setBigDecimal(7, chofer.getSalarioBasePorViaje());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new SQLException("Error al registrar el chofer: " + e.getMessage());
        }
    }

    public boolean actualizarChofer(Connection coneccion, Chofer chofer) throws SQLException {

        boolean actualizarFotografia = chofer.getFotografia() != null && chofer.getFotografia().length > 0;

        String sql;

        if (actualizarFotografia) {
            sql = "UPDATE chofer SET id_sucursal = ?, fotografia = ?, numero_licencia = ?, tipo_licencia = ?, fecha_vencimiento_licencia = ?, salario_base_por_viaje = ? WHERE nombre_usuario = ?";
        } else {
            sql = "UPDATE chofer SET id_sucursal = ?, numero_licencia = ?, tipo_licencia = ?, fecha_vencimiento_licencia = ?, salario_base_por_viaje = ? WHERE nombre_usuario = ?";
        }

        try (PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {
            preparedStatement.setInt(1, chofer.getSucursal().getId());

            if (actualizarFotografia) {
                preparedStatement.setBytes(2, chofer.getFotografia());
                preparedStatement.setString(3, chofer.getNumeroLicencia());
                preparedStatement.setString(4, chofer.getTipoLicencia());
                preparedStatement.setDate(5, Date.valueOf(chofer.getFechaVencimientoLicencia()));
                preparedStatement.setBigDecimal(6, chofer.getSalarioBasePorViaje());
                preparedStatement.setString(7, chofer.getUsuario().getNombreUsuario());
            } else {
                preparedStatement.setString(2, chofer.getNumeroLicencia());
                preparedStatement.setString(3, chofer.getTipoLicencia());
                preparedStatement.setDate(4, Date.valueOf(chofer.getFechaVencimientoLicencia()));
                preparedStatement.setBigDecimal(5, chofer.getSalarioBasePorViaje());
                preparedStatement.setString(6, chofer.getUsuario().getNombreUsuario());
            }

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new SQLException("Error al actualizar el chofer: " + e.getMessage());
        }
    }

    public List<Chofer> obtenerChoferesPorSucursal(int idSucursal) throws SQLException {
        List<Chofer> choferes = new ArrayList<>();
        String sqlSelect = "SELECT u.nombre_usuario, u.nit, u.dpi, u.nombre_completo, u.telefono, u.direccion, u.correo_electronico, u.estado, c.id_sucursal, c.fotografia, c.numero_licencia, c.tipo_licencia, c.fecha_vencimiento_licencia, c.salario_base_por_viaje, s.nombre ";
        String sqlFrom = "FROM chofer c ";
        String sqlInnerWhere = "INNER JOIN usuario u ON c.nombre_usuario = u.nombre_usuario INNER JOIN sucursal s ON c.id_sucursal = s.id WHERE c.id_sucursal = ?";
        String sql = sqlSelect + sqlFrom + sqlInnerWhere;

        try (Connection coneccion = ConexionDB.getConeccion();
                PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {
            preparedStatement.setInt(1, idSucursal);

            try (ResultSet result = preparedStatement.executeQuery()) {
                while (result.next()) {
                    choferes.add(construirChofer(result));
                }
            }

        } catch (SQLException e) {
            throw new SQLException("Error al obtener los choferes de la sucursal: " + e.getMessage());
        }

        return choferes;
    }

    public Optional<Chofer> obtenerChoferPorNombreUsuario(String nombreUsuario) throws SQLException {
        String sqlSelect = "SELECT u.nombre_usuario, u.nit, u.dpi, u.nombre_completo, u.telefono, u.direccion, u.correo_electronico, u.estado, c.id_sucursal, c.fotografia, c.numero_licencia, c.tipo_licencia, c.fecha_vencimiento_licencia, c.salario_base_por_viaje, s.nombre ";
        String sqlFrom = "FROM chofer c ";
        String sqlInnerWhere = "INNER JOIN usuario u ON c.nombre_usuario = u.nombre_usuario INNER JOIN sucursal s ON c.id_sucursal = s.id WHERE c.nombre_usuario = ? ";
        String sql = sqlSelect + sqlFrom + sqlInnerWhere;

        try (Connection coneccion = ConexionDB.getConeccion();
                PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {
            preparedStatement.setString(1, nombreUsuario);

            try (ResultSet result = preparedStatement.executeQuery()) {

                if (result.next()) {
                    return Optional.of(construirChofer(result));
                }
            }

        } catch (SQLException e) {
            throw new SQLException("Error al obtener el chofer: " + e.getMessage());
        }

        return Optional.empty();
    }

    private Chofer construirChofer(ResultSet result) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(result.getString("nombre_usuario"));
        usuario.setNit(result.getString("nit"));
        usuario.setDpi(result.getString("dpi"));
        usuario.setNombreCompleto(result.getString("nombre_completo"));
        usuario.setTelefono(result.getString("telefono"));
        usuario.setDireccion(result.getString("direccion"));
        usuario.setCorreoElectronico(result.getString("correo_electronico"));
        usuario.setEstado(result.getBoolean("estado"));

        Sucursal sucursal = new Sucursal();
        sucursal.setId(result.getInt("id_sucursal"));
        sucursal.setNombre(result.getString("nombre"));

        Chofer chofer = new Chofer();
        chofer.setUsuario(usuario);
        chofer.setSucursal(sucursal);
        chofer.setFotografia(result.getBytes("fotografia"));
        chofer.setNumeroLicencia(result.getString("numero_licencia"));
        chofer.setTipoLicencia(result.getString("tipo_licencia"));
        chofer.setFechaVencimientoLicencia(result.getDate("fecha_vencimiento_licencia").toLocalDate());
        chofer.setSalarioBasePorViaje(result.getBigDecimal("salario_base_por_viaje"));

        return chofer;
    }

    public Optional<Chofer> obtenerChoferPorNumeroLicencia(String numeroLicencia) throws SQLException {
        String sql = "SELECT nombre_usuario, numero_licencia FROM chofer WHERE numero_licencia = ?";

        try (Connection coneccion = ConexionDB.getConeccion();
                PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {
            preparedStatement.setString(1, numeroLicencia);

            try (ResultSet result = preparedStatement.executeQuery()) {
                if (result.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setNombreUsuario(result.getString("nombre_usuario"));
                    Chofer chofer = new Chofer();
                    chofer.setUsuario(usuario);
                    chofer.setNumeroLicencia(result.getString("numero_licencia"));

                    return Optional.of(chofer);
                }
            }

        } catch (SQLException e) {
            throw new SQLException("Error al buscar el número de licencia: " + e.getMessage());
        }

        return Optional.empty();
    }

}
