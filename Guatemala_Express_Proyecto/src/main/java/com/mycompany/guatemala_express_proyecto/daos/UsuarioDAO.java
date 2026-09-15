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

import com.mycompany.guatemala_express_proyecto.enums.Rol;
import com.mycompany.guatemala_express_proyecto.exceptions.NoGuardadoEnBDException;
import com.mycompany.guatemala_express_proyecto.modelos.Usuario;
import com.mycompany.guatemala_express_proyecto.util.ConexionDB;

/**
 *
 * @author matul
 */
public class UsuarioDAO {

    public boolean crearUsuario(Usuario usuario) throws NoGuardadoEnBDException {
        String sql = "INSERT INTO usuario (nombre_usuario, nit, dpi, nombre_completo, telefono, direccion, correo_electronico, contrasenia, rol) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection coneccion = ConexionDB.getConeccion();
                PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {
            preparedStatement.setString(1, usuario.getNombreUsuario());
            preparedStatement.setString(2, usuario.getNit());
            preparedStatement.setString(3, usuario.getDpi());
            preparedStatement.setString(4, usuario.getNombreCompleto());
            preparedStatement.setString(5, usuario.getTelefono());
            preparedStatement.setString(6, usuario.getDireccion());
            preparedStatement.setString(7, usuario.getCorreoElectronico());
            preparedStatement.setString(8, usuario.getContrasenia());
            preparedStatement.setString(9, usuario.getRol().name());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new NoGuardadoEnBDException("Error al crear el usuario: " + e.getMessage());
        }

    }

    public boolean actualizarUsuario(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuario SET nit = ?, dpi = ?, nombre_completo = ?, telefono = ?, direccion = ?, correo_electronico = ? WHERE nombre_usuario = ?";
        try (Connection coneccion = ConexionDB.getConeccion();
                PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {
            preparedStatement.setString(1, usuario.getNit());
            preparedStatement.setString(2, usuario.getDpi());
            preparedStatement.setString(3, usuario.getNombreCompleto());
            preparedStatement.setString(4, usuario.getTelefono());
            preparedStatement.setString(5, usuario.getDireccion());
            preparedStatement.setString(6, usuario.getCorreoElectronico());
            preparedStatement.setString(7, usuario.getNombreUsuario());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new SQLException("Error al actualizar el usuario: " + e.getMessage());
        }
    }

    public boolean actualizarEstadoUsuario(String nombreUsuario, boolean estado) throws SQLException {
        String sql = "UPDATE usuario SET estado = ? WHERE nombre_usuario = ?";
        try (Connection coneccion = ConexionDB.getConeccion();
                PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {
            preparedStatement.setBoolean(1, estado);
            preparedStatement.setString(2, nombreUsuario);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new SQLException("Error al actualizar el estado del usuario: " + e.getMessage());
        }
    }

    public Optional<Usuario> obtenerUsuarioPorNombreUsuario(String nombreUsuario) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE nombre_usuario = ?";
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
                    usuario.setContrasenia(result.getString("contrasenia"));
                    usuario.setRol(Rol.valueOf(result.getString("rol")));
                    usuario.setSaldo(result.getBigDecimal("saldo"));
                    usuario.setEstado(result.getBoolean("estado"));
                    Optional<Usuario> usuarioOptional = Optional.of(usuario);
                    return usuarioOptional;
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener el usuario por ID: " + e.getMessage());
        }
        return Optional.empty();
    }

    public Optional<Usuario> obtenerUsuarioPorCorreo(String correo) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE correo_electronico = ?";
        try (Connection coneccion = ConexionDB.getConeccion();
                PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {
            preparedStatement.setString(1, correo);
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
                    usuario.setContrasenia(result.getString("contrasenia"));
                    usuario.setRol(Rol.valueOf(result.getString("rol")));
                    usuario.setSaldo(result.getBigDecimal("saldo"));
                    usuario.setEstado(result.getBoolean("estado"));
                    Optional<Usuario> usuarioOptional = Optional.of(usuario);
                    return usuarioOptional;
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener el usuario por correo: " + e.getMessage());
        }
        return Optional.empty();
    }

    public Optional<Usuario> obtenerUsuarioPorNit(String nit) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE nit = ?";
        try (Connection coneccion = ConexionDB.getConeccion();
                PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {
            preparedStatement.setString(1, nit);
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
                    usuario.setContrasenia(result.getString("contrasenia"));
                    usuario.setRol(Rol.valueOf(result.getString("rol")));
                    usuario.setSaldo(result.getBigDecimal("saldo"));
                    usuario.setEstado(result.getBoolean("estado"));
                    Optional<Usuario> usuarioOptional = Optional.of(usuario);
                    return usuarioOptional;
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener el usuario por ID: " + e.getMessage());
        }
        return Optional.empty();
    }

    public Optional<Usuario> obtenerUsuarioPorDpi(String dpi) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE dpi = ?";
        try (Connection coneccion = ConexionDB.getConeccion();
                PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {
            preparedStatement.setString(1, dpi);
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
                    usuario.setContrasenia(result.getString("contrasenia"));
                    usuario.setRol(Rol.valueOf(result.getString("rol")));
                    usuario.setSaldo(result.getBigDecimal("saldo"));
                    usuario.setEstado(result.getBoolean("estado"));
                    Optional<Usuario> usuarioOptional = Optional.of(usuario);
                    return usuarioOptional;
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener el usuario por ID: " + e.getMessage());
        }
        return Optional.empty();
    }

    public List<Usuario> obtenerUsuariosPorRol(Rol rol) throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario WHERE rol = ?";
        try (Connection coneccion = ConexionDB.getConeccion();
                PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {
            preparedStatement.setString(1, rol.name());
            try (ResultSet result = preparedStatement.executeQuery()) {
                while (result.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setNombreUsuario(result.getString("nombre_usuario"));
                    usuario.setNit(result.getString("nit"));
                    usuario.setDpi(result.getString("dpi"));
                    usuario.setNombreCompleto(result.getString("nombre_completo"));
                    usuario.setTelefono(result.getString("telefono"));
                    usuario.setDireccion(result.getString("direccion"));
                    usuario.setCorreoElectronico(result.getString("correo_electronico"));
                    usuario.setContrasenia(result.getString("contrasenia"));
                    usuario.setRol(Rol.valueOf(result.getString("rol")));
                    usuario.setSaldo(result.getBigDecimal("saldo"));
                    usuario.setEstado(result.getBoolean("estado"));
                    usuarios.add(usuario);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener los usuarios por rol: " + e.getMessage());
        }
        return usuarios;
    }

    public int activosPorRol(Rol rol) throws SQLException {
        String sql = "SELECT COUNT(*) AS total FROM usuario WHERE rol = ? AND estado = TRUE";
        try (Connection coneccion = ConexionDB.getConeccion();
                PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {
            preparedStatement.setString(1, rol.name());
            try (ResultSet result = preparedStatement.executeQuery()) {
                if (result.next()) {
                    return result.getInt("total");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al contar los usuarios activos por rol: " + e.getMessage());
        }
        return 0;
    }

    public boolean sumarSaldo(String nombreUsuario, BigDecimal monto, Connection coneccion) throws SQLException {
        String sql = "UPDATE usuario SET saldo = saldo + ? WHERE nombre_usuario = ?";
        try (PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {
            preparedStatement.setBigDecimal(1, monto);
            preparedStatement.setString(2, nombreUsuario);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new SQLException("Error al sumar el saldo del usuario: " + e.getMessage());
        }
    }

    public BigDecimal obtenerSaldo(String nombreUsuario) throws SQLException {
        String sql = "SELECT saldo FROM usuario WHERE nombre_usuario = ?";
        try (Connection coneccion = ConexionDB.getConeccion();
                PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {

            preparedStatement.setString(1, nombreUsuario);

            try (ResultSet result = preparedStatement.executeQuery()) {
                if (result.next()) {
                    return result.getBigDecimal("saldo");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener el saldo del usuario: " + e.getMessage());
        }

        return BigDecimal.ZERO;
    }

    public boolean registrarUsuario(Connection coneccion, Usuario usuario) throws SQLException {

        String sql = "INSERT INTO usuario (nombre_usuario, nit, dpi, nombre_completo, telefono, direccion, correo_electronico, contrasenia, rol) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {
            preparedStatement.setString(1, usuario.getNombreUsuario());
            preparedStatement.setString(2, usuario.getNit());
            preparedStatement.setString(3, usuario.getDpi());
            preparedStatement.setString(4, usuario.getNombreCompleto());
            preparedStatement.setString(5, usuario.getTelefono());
            preparedStatement.setString(6, usuario.getDireccion());
            preparedStatement.setString(7, usuario.getCorreoElectronico());
            preparedStatement.setString(8, usuario.getContrasenia());
            preparedStatement.setString(9, usuario.getRol().name());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new SQLException(
                    "Error al registrar el usuario: " + e.getMessage());
        }
    }

    public boolean actualizarUsuario(
            Connection coneccion, Usuario usuario) throws SQLException {

        String sql = "UPDATE usuario SET nit = ?, dpi = ?, nombre_completo = ?, telefono = ?, direccion = ?, correo_electronico = ? WHERE nombre_usuario = ?";

        try (PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {
            preparedStatement.setString(1, usuario.getNit());
            preparedStatement.setString(2, usuario.getDpi());
            preparedStatement.setString(3, usuario.getNombreCompleto());
            preparedStatement.setString(4, usuario.getTelefono());
            preparedStatement.setString(5, usuario.getDireccion());
            preparedStatement.setString(6, usuario.getCorreoElectronico());
            preparedStatement.setString(7, usuario.getNombreUsuario());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new SQLException(
                    "Error al actualizar el usuario: " + e.getMessage());
        }
    }

}

// usuario(
// nombre_usuario VARCHAR(50) NOT NULL PRIMARY KEY,
// nit VARCHAR(20) NOT NULL,
// dpi VARCHAR(20) NOT NULL,
// nombre_completo VARCHAR(255) NOT NULL,
// telefono VARCHAR(15) NOT NULL,
// direccion VARCHAR(255) NOT NULL,
// correo_electronico VARCHAR(255) NOT NULL,
// contrasenia VARCHAR(255) NOT NULL,
// rol VARCHAR(50) NOT NULL,
// saldo DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
// estado BOOLEAN NOT NULL DEFAULT TRUE,
// UNIQUE (nit, dpi, correo_electronico)
// );
