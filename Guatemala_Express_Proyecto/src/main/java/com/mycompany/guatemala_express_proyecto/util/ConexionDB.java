/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.conexion_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author matul
 */
public class ConexionDB {
    
    private static final String URL_MYSQL = "jdbc:mysql://localhost:3306/guatemala_express";
    private static final String USER = "proyecto_1";
    private static final String PASSWORD = "123";
    private static Connection conexion;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error al cargar el driver JDBC", e);
        }
    }
    
    private ConexionDB() {}
    
    public static Connection getConeccion() throws SQLException{
        if (conexion == null || conexion.isClosed()) {
        synchronized (ConexionDB.class) {
            if (conexion == null || conexion.isClosed()) {
                conexion = DriverManager.getConnection(URL_MYSQL, USER, PASSWORD);
            }
        }
    }
    return conexion;
    }
    
}


