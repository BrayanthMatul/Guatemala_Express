/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.servicios.sucursales;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.mycompany.guatemala_express_proyecto.daos.SucursalDAO;
import com.mycompany.guatemala_express_proyecto.exceptions.DatosIncompletosException;
import com.mycompany.guatemala_express_proyecto.exceptions.EntidadYaRegistradaException;
import com.mycompany.guatemala_express_proyecto.exceptions.NoGuardadoEnBDException;
import com.mycompany.guatemala_express_proyecto.modelos.Sucursal;

/**
 *
 * @author matul
 */
public class SucursalServicio {

    private final SucursalDAO sucursalDAO;

    public SucursalServicio() {
        this.sucursalDAO = new SucursalDAO();
    }

    public boolean crearSucursal(Sucursal sucursal)
            throws DatosIncompletosException, EntidadYaRegistradaException,
            NoGuardadoEnBDException, SQLException {

        if (datosVacios(sucursal)) {
            throw new DatosIncompletosException("Por favor, complete todos los campos requeridos.");
        }

        if (nombreYaRegistrado(sucursal)) {
            throw new EntidadYaRegistradaException("El nombre de la sucursal ya está registrado.");
        }

        if (telefonoYaRegistrado(sucursal)) {
            throw new EntidadYaRegistradaException("El teléfono ya está registrado.");
        }

        if (coordenadasYaRegistradas(sucursal)) {
            throw new EntidadYaRegistradaException("Ya existe una sucursal registrada en esas coordenadas.");
        }

        sucursal.setFechaApertura(LocalDate.now());

        return sucursalDAO.crearSucursal(sucursal);
    }

    public boolean editarSucursal(Sucursal sucursal)
            throws DatosIncompletosException, EntidadYaRegistradaException,
            NoGuardadoEnBDException, SQLException {

        if (sucursal == null || sucursal.getId() <= 0) {
            throw new DatosIncompletosException("No se indicó la sucursal que se desea editar.");
        }

        if (datosVacios(sucursal)) {
            throw new DatosIncompletosException("Por favor, complete todos los campos requeridos.");
        }

        if (nombreYaRegistrado(sucursal)) {
            throw new EntidadYaRegistradaException("El nombre de la sucursal ya está registrado.");
        }

        if (telefonoYaRegistrado(sucursal)) {
            throw new EntidadYaRegistradaException("El teléfono ya está registrado.");
        }

        if (coordenadasYaRegistradas(sucursal)) {
            throw new EntidadYaRegistradaException("Ya existe otra sucursal registrada en esas coordenadas.");
        }

        if (!sucursalDAO.actualizarSucursal(sucursal)) {
            throw new NoGuardadoEnBDException("No fue posible actualizar la sucursal.");
        }

        return true;
    }

    public List<Sucursal> obtenerSucursales() throws SQLException {
        return sucursalDAO.obtenerSucursales();
    }

    private boolean datosVacios(Sucursal sucursal) {
        if (sucursal == null) {
            return true;
        }

        if (sucursal.getNombre() == null || sucursal.getNombre().isBlank()) {
            return true;
        }

        if (sucursal.getDepartamento() == null || sucursal.getDepartamento().isBlank()) {
            return true;
        }

        if (sucursal.getMunicipio() == null || sucursal.getMunicipio().isBlank()) {
            return true;
        }

        if (sucursal.getLongitud() == null) {
            return true;
        }

        if (sucursal.getLatitud() == null) {
            return true;
        }

        if (sucursal.getTelefono() == null
                || sucursal.getTelefono().isBlank()) {
            return true;
        }

        return false;
    }

    private boolean nombreYaRegistrado(Sucursal sucursal) throws SQLException {
        Optional<Sucursal> sucursalExistente = sucursalDAO.obtenerSucursalPorNombre(sucursal.getNombre());
        return sucursalExistente.isPresent() && sucursalExistente.get().getId() != sucursal.getId();
    }

    private boolean telefonoYaRegistrado(Sucursal sucursal) throws SQLException {
        Optional<Sucursal> sucursalExistente = sucursalDAO.obtenerSucursalPorTelefono(sucursal.getTelefono());
        return sucursalExistente.isPresent() && sucursalExistente.get().getId() != sucursal.getId();
    }

    private boolean coordenadasYaRegistradas(Sucursal sucursal) throws SQLException {
        Optional<Sucursal> sucursalExistente = sucursalDAO.obtenerSucursalPorCoordenadas(sucursal.getLongitud(),
                sucursal.getLatitud());
        return sucursalExistente.isPresent() && sucursalExistente.get().getId() != sucursal.getId();
    }

}
