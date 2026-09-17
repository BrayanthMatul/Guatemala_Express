/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.servicios.ruta_regular;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.mycompany.guatemala_express_proyecto.daos.RutaRegularDAO;
import com.mycompany.guatemala_express_proyecto.exceptions.DatosIncompletosException;
import com.mycompany.guatemala_express_proyecto.exceptions.EntidadYaRegistradaException;
import com.mycompany.guatemala_express_proyecto.exceptions.NoGuardadoEnBDException;
import com.mycompany.guatemala_express_proyecto.modelos.RutaRegular;

/**
 *
 * @author matul
 */
public class RutaRegularServicio {

    private final RutaRegularDAO rutaRegularDAO;

    public RutaRegularServicio() {
        this.rutaRegularDAO = new RutaRegularDAO();
    }

    public boolean registrarNuevaRuta(RutaRegular ruta)
            throws DatosIncompletosException, EntidadYaRegistradaException, NoGuardadoEnBDException, SQLException {

        validarDatosRuta(ruta);

        if (rutaYaRegistrada(ruta)) {
            throw new EntidadYaRegistradaException(
                    "Ya existe una ruta con la misma sucursal de origen y destino.");
        }

        ruta.setEstado(true);

        boolean registrada = rutaRegularDAO.registrarNuevaRuta(ruta);

        if (!registrada) {
            throw new NoGuardadoEnBDException("No fue posible registrar la ruta.");
        }

        return true;
    }

    public boolean actualizarRuta(RutaRegular ruta)
            throws DatosIncompletosException, EntidadYaRegistradaException, NoGuardadoEnBDException, SQLException {

        if (ruta == null) {
            throw new DatosIncompletosException("No se recibieron los datos de la ruta.");
        }

        if (ruta.getId() <= 0) {
            throw new DatosIncompletosException("No se indicó la ruta que desea actualizar.");
        }

        validarDatosRuta(ruta);
        Optional<RutaRegular> rutaActualOptional = rutaRegularDAO.obtenerRutaPorId(ruta.getId());

        if (rutaActualOptional.isEmpty()) {
            throw new NoGuardadoEnBDException("No se encontró la ruta que desea actualizar.");
        }

        int idOrigenActual = rutaActualOptional.get().getSucursalOrigen().getId();
        int idOrigenRecibido = ruta.getSucursalOrigen().getId();

        if (idOrigenActual != idOrigenRecibido) {
            throw new NoGuardadoEnBDException("La ruta no pertenece a la sucursal indicada.");
        }

        if (rutaYaRegistrada(ruta)) {
            throw new EntidadYaRegistradaException("Ya existe una ruta con la misma sucursal de origen y destino.");
        }

        boolean actualizada = rutaRegularDAO.actualizarRuta(ruta);

        if (!actualizada) {
            throw new NoGuardadoEnBDException("No fue posible actualizar la ruta.");
        }

        return true;
    }

    public Optional<RutaRegular> obtenerRutaPorId(int id) throws SQLException {
        return rutaRegularDAO.obtenerRutaPorId(id);
    }

    public List<RutaRegular> obtenerRutasPorSucursal(int idSucursal) throws SQLException {
        return rutaRegularDAO.obtenerRutasPorSucursal(idSucursal);
    }

    public boolean actualizarEstado(int id, boolean nuevoEstado)
            throws DatosIncompletosException, NoGuardadoEnBDException, SQLException {

        if (id <= 0) {
            throw new DatosIncompletosException("No se indicó la ruta que desea actualizar.");
        }

        boolean actualizado = rutaRegularDAO.actualizarEstado(id, nuevoEstado);

        if (!actualizado) {
            throw new NoGuardadoEnBDException("No fue posible actualizar el estado de la ruta.");
        }

        return true;
    }

    private void validarDatosRuta(RutaRegular ruta) throws DatosIncompletosException {

        if (ruta == null || ruta.getSucursalOrigen() == null || ruta.getSucursalOrigen().getId() <= 0
                || ruta.getSucursalDestino() == null || ruta.getSucursalDestino().getId() <= 0
                || ruta.getDistanciaAproximadaKm() == null || ruta.getPrecioBoleto() == null
                || ruta.getDuracionEstimada() == null) {

            throw new DatosIncompletosException("Por favor, complete todos los campos requeridos.");
        }

        if (ruta.getSucursalOrigen().getId() == ruta.getSucursalDestino().getId()) {
            throw new DatosIncompletosException("La sucursal de origen y destino deben ser diferentes.");
        }

        if (ruta.getDistanciaAproximadaKm().compareTo(BigDecimal.ZERO) <= 0) {
            throw new DatosIncompletosException("La distancia aproximada debe ser mayor que cero.");
        }

        if (ruta.getPrecioBoleto().compareTo(BigDecimal.ZERO) <= 0) {
            throw new DatosIncompletosException("El precio del boleto debe ser mayor que cero.");
        }
    }

    private boolean rutaYaRegistrada(RutaRegular ruta) throws SQLException {
        Optional<RutaRegular> rutaExistente = rutaRegularDAO
                .obtenerRutaPorOrigenYDestino(ruta.getSucursalOrigen().getId(), ruta.getSucursalDestino().getId());

        return rutaExistente.isPresent() && rutaExistente.get().getId() != ruta.getId();
    }

}
