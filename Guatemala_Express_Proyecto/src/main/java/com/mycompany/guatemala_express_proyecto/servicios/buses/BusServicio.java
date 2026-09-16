/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.servicios.buses;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import com.mycompany.guatemala_express_proyecto.daos.BusDAO;
import com.mycompany.guatemala_express_proyecto.enums.EstadoOperativo;
import com.mycompany.guatemala_express_proyecto.exceptions.DatosIncompletosException;
import com.mycompany.guatemala_express_proyecto.exceptions.EntidadYaRegistradaException;
import com.mycompany.guatemala_express_proyecto.exceptions.NoGuardadoEnBDException;
import com.mycompany.guatemala_express_proyecto.modelos.Bus;

/**
 *
 * @author matul
 */
public class BusServicio {

    private final BusDAO busDAO;

    public BusServicio() {
        this.busDAO = new BusDAO();
    }

    public boolean registrarBus(Bus bus)
            throws DatosIncompletosException, EntidadYaRegistradaException, NoGuardadoEnBDException, SQLException {

        if (datosVacios(bus, true, true)) {
            throw new DatosIncompletosException("Por favor, complete todos los campos requeridos.");
        }

        if (busDAO.obtenerBusPorNumeroPlaca(bus.getNumeroPlaca()).isPresent()) {
            throw new EntidadYaRegistradaException("El número de placa ya está registrado.");
        }

        bus.setEstado(true);
        bus.setEstadoOperativo(EstadoOperativo.DISPONIBLE);

        boolean registrado = busDAO.registrarBus(bus);

        if (!registrado) {
            throw new NoGuardadoEnBDException("No fue posible registrar el bus.");
        }

        return true;
    }

    public boolean editarBus(Bus bus)
            throws DatosIncompletosException, NoGuardadoEnBDException,
            SQLException {

        if (datosVacios(bus, false, false)) {
            throw new DatosIncompletosException("Por favor, complete todos los campos requeridos.");
        }

        if (busDAO.obtenerBusPorNumeroPlaca(bus.getNumeroPlaca()).isEmpty()) {
            throw new NoGuardadoEnBDException("No se encontró el bus que desea actualizar.");
        }

        boolean actualizado = busDAO.actualizarBus(bus);

        if (!actualizado) {
            throw new NoGuardadoEnBDException("No fue posible actualizar el bus.");
        }

        return true;
    }

    public List<Bus> obtenerBuses() throws SQLException {

        List<Bus> buses = busDAO.obtenerBuses();

        for (Bus bus : buses) {

            if (bus.getFotografia() != null && bus.getFotografia().length > 0) {
                bus.setFotografiaBase64(Base64.getEncoder().encodeToString(bus.getFotografia()));
            }
        }

        return buses;
    }

    public Optional<Bus> obtenerBusPorNumeroPlaca(String numeroPlaca)
            throws SQLException {

        return busDAO.obtenerBusPorNumeroPlaca(numeroPlaca);
    }

    public boolean cambiarEstadoBus(String numeroPlaca, boolean nuevoEstado)
            throws DatosIncompletosException, NoGuardadoEnBDException,
            SQLException {

        if (numeroPlaca == null || numeroPlaca.isBlank()) {
            throw new DatosIncompletosException(
                    "No se indicó el número de placa.");
        }

        boolean actualizado = busDAO.actualizarEstadoBus(
                numeroPlaca,
                nuevoEstado);

        if (!actualizado) {
            throw new NoGuardadoEnBDException(
                    "No fue posible actualizar el estado del bus.");
        }

        return true;
    }

    public List<Bus> obtenerBusesPorSucursal(int idSucursal) throws SQLException {

        List<Bus> buses = busDAO.obtenerBusesPorSucursal(idSucursal);

        for (Bus bus : buses) {
            if (bus.getFotografia() != null && bus.getFotografia().length > 0) {
                bus.setFotografiaBase64(Base64.getEncoder().encodeToString(bus.getFotografia()));
            }
        }

        return buses;
    }

    public boolean cambiarEstadoOperativoBus(
            String numeroPlaca,
            EstadoOperativo estadoOperativo)
            throws DatosIncompletosException, NoGuardadoEnBDException,
            SQLException {

        if (numeroPlaca == null || numeroPlaca.isBlank()
                || estadoOperativo == null) {

            throw new DatosIncompletosException(
                    "No se indicaron los datos para cambiar el estado operativo.");
        }

        boolean actualizado = busDAO.actualizarEstadoOperativoBus(
                numeroPlaca,
                estadoOperativo);

        if (!actualizado) {
            throw new NoGuardadoEnBDException(
                    "No fue posible actualizar el estado operativo del bus.");
        }

        return true;
    }

    public boolean sumarKilometrajeBus(String numeroPlaca, BigDecimal kilometrajeRecorrido)
            throws DatosIncompletosException, NoGuardadoEnBDException, SQLException {

        if (numeroPlaca == null || numeroPlaca.isBlank() || kilometrajeRecorrido == null
                || kilometrajeRecorrido.compareTo(BigDecimal.ZERO) <= 0) {
            throw new DatosIncompletosException(
                    "El número de placa y el kilometraje recorrido son obligatorios.");
        }

        boolean actualizado = busDAO.sumarKilometrajeBus(numeroPlaca, kilometrajeRecorrido);

        if (!actualizado) {
            throw new NoGuardadoEnBDException("No fue posible actualizar el kilometraje del bus.");
        }

        return true;
    }

    private boolean datosVacios(Bus bus, boolean revisarFotografia, boolean revisarKilometraje) {

        if (bus == null) {
            return true;
        }

        if (bus.getNumeroPlaca() == null || bus.getNumeroPlaca().isBlank()) {
            return true;
        }

        if (bus.getSucursal() == null
                || bus.getSucursal().getId() <= 0) {
            return true;
        }

        if (bus.getMarca() == null || bus.getMarca().isBlank()) {
            return true;
        }

        if (bus.getModelo() == null || bus.getModelo().isBlank()) {
            return true;
        }

        if (bus.getAnioFabricacion() <= 0) {
            return true;
        }

        if (bus.getCapacidadPasajeros() <= 0) {
            return true;
        }

        if (revisarKilometraje
                && (bus.getKilometrajeActual() == null || bus.getKilometrajeActual().compareTo(BigDecimal.ZERO) < 0)) {
            return true;
        }

        if (revisarFotografia && (bus.getFotografia() == null || bus.getFotografia().length == 0)) {
            return true;
        }

        return false;
    }

}
