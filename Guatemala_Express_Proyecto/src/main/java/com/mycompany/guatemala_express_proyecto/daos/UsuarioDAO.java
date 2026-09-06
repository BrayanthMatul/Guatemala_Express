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

import com.mycompany.guatemala_express_proyecto.enums.Rol;
import com.mycompany.guatemala_express_proyecto.exceptions.UsuarioNoEncontradoException;
import com.mycompany.guatemala_express_proyecto.modelos.Usuario;
import com.mycompany.guatemala_express_proyecto.util.ConexionDB;

/**
 *
 * @author matul
 */
public class UsuarioDAO {

    public Optional<Usuario> obtenerUsuarioPorCorreo(String correo) throws UsuarioNoEncontradoException {
        String sql = "SELECT * FROM usuario WHERE correo_electronico = ?";
        try (Connection coneccion = ConexionDB.getConeccion();
                PreparedStatement preparedStatement = coneccion.prepareStatement(sql)) {
            preparedStatement.setString(1, correo);
            try (ResultSet result = preparedStatement.executeQuery()) {
                if (result.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setNombreCompleto(result.getString("nombre_completo"));
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
            throw new UsuarioNoEncontradoException("Error al obtener el usuario por correo: " + e.getMessage());
        }
        return Optional.empty();
    }

}

// usuario(
// id INT
// PRIMARY KEY AUTO_INCREMENT,
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
