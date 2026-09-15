/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mycompany.guatemala_express_proyecto.modelos.AdministradorSucursal;
import com.mycompany.guatemala_express_proyecto.modelos.Sucursal;
import com.mycompany.guatemala_express_proyecto.modelos.Usuario;
import com.mycompany.guatemala_express_proyecto.util.ConexionDB;

/**
 *
 * @author matul
 */
public class AdministradorSucursalDAO {

    public boolean crearAdministradorSucursal(Connection coneccion, AdministradorSucursal administradorSucursal)
            throws SQLException {

        String sql = "INSERT INTO administrador_sucursal (nombre_usuario, sucursal_id) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {

            preparedStatement.setString(1, administradorSucursal.getUsuario().getNombreUsuario());
            preparedStatement.setInt(2, administradorSucursal.getSucursal().getId());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new SQLException("Error al crear el administrador de sucursal: " + e.getMessage());
        }
    }

    public boolean actualizarAdministradorSucursal(Connection coneccion, AdministradorSucursal administradorSucursal)
            throws SQLException {

        String sql = "UPDATE administrador_sucursal SET sucursal_id = ? WHERE nombre_usuario = ?";

        try (PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {
            preparedStatement.setInt(1, administradorSucursal.getSucursal().getId());
            preparedStatement.setString(2, administradorSucursal.getUsuario().getNombreUsuario());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new SQLException("Error al actualizar el administrador de sucursal: " + e.getMessage());
        }
    }

    public List<AdministradorSucursal> obtenerAdministradoresSucursal() throws SQLException {
        List<AdministradorSucursal> administradoresSucursal = new ArrayList<>();
        String sqlSelect = "SELECT u.nombre_usuario, u.nit, u.dpi, u.nombre_completo, u.telefono, u.direccion, u.correo_electronico, u.estado, s.id, s.nombre ";
        String sqlFrom = "FROM administrador_sucursal ";
        String sqlJoin = "INNER JOIN usuario u ON administrador_sucursal.nombre_usuario = u.nombre_usuario INNER JOIN sucursal s ON administrador_sucursal.sucursal_id = s.id";
        String sql = sqlSelect + sqlFrom + sqlJoin;

        try (Connection coneccion = ConexionDB.getConeccion();
                PreparedStatement preparedStatement = coneccion.prepareStatement(sql);
                ResultSet result = preparedStatement.executeQuery()) {

            while (result.next()) {
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
                sucursal.setId(result.getInt("id"));
                sucursal.setNombre(result.getString("nombre"));

                AdministradorSucursal administradorSucursal = new AdministradorSucursal();

                administradorSucursal.setUsuario(usuario);
                administradorSucursal.setSucursal(sucursal);

                administradoresSucursal.add(administradorSucursal);
            }

        } catch (SQLException e) {
            throw new SQLException("Error al obtener los administradores de sucursal: " + e.getMessage());
        }

        return administradoresSucursal;
    }

    public Optional<AdministradorSucursal> obtenerAdministradorSucursalPorNombreUsuario(
            String nombreUsuario) throws SQLException {

        String sqlSelect = "SELECT u.nombre_usuario, u.nit, u.dpi, u.nombre_completo, u.telefono, u.direccion, u.correo_electronico, u.estado, s.id, s.nombre ";
        String sqlFrom = "FROM administrador_sucursal ";
        String sqlJoin = "INNER JOIN usuario u ON administrador_sucursal.nombre_usuario = u.nombre_usuario INNER JOIN sucursal s ON administrador_sucursal.sucursal_id = s.id ";
        String sqlWhere = "WHERE u.nombre_usuario = ?";

        String sql = sqlSelect + sqlFrom + sqlJoin + sqlWhere;

        try (Connection coneccion = ConexionDB.getConeccion();
                PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {

            preparedStatement.setString(1, nombreUsuario);

            try (ResultSet result = preparedStatement.executeQuery()) {
                if (result.next()) {
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

                    sucursal.setId(result.getInt("id"));
                    sucursal.setNombre(result.getString("nombre"));

                    AdministradorSucursal administradorSucursal = new AdministradorSucursal();

                    administradorSucursal.setUsuario(usuario);
                    administradorSucursal.setSucursal(sucursal);

                    return Optional.of(administradorSucursal);
                }
            }

        } catch (SQLException e) {
            throw new SQLException("Error al obtener el administrador de sucursal: " + e.getMessage());
        }

        return Optional.empty();
    }

}
