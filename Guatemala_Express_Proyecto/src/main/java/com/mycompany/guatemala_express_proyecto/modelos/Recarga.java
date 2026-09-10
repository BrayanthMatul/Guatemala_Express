/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.modelos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author matul
 */
public class Recarga {
    private int id;
    private int idUsuario;
    private LocalDateTime fechaHoraRecarga;
    private BigDecimal monto;

    public Recarga(int idUsuario, LocalDateTime fechaHoraRecarga, BigDecimal monto) {
        this.idUsuario = idUsuario;
        this.fechaHoraRecarga = fechaHoraRecarga;
        this.monto = monto;
    }

    public Recarga(int id, int idUsuario, LocalDateTime fechaHoraRecarga, BigDecimal monto) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.fechaHoraRecarga = fechaHoraRecarga;
        this.monto = monto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LocalDateTime getFechaHoraRecarga() {
        return fechaHoraRecarga;
    }

    public void setFechaHoraRecarga(LocalDateTime fechaHoraRecarga) {
        this.fechaHoraRecarga = fechaHoraRecarga;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
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
