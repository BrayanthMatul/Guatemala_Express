/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.servicios.gasto_taller;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.mycompany.guatemala_express_proyecto.modelos.Bus;
import com.mycompany.guatemala_express_proyecto.daos.GastoTallerDAO;
import com.mycompany.guatemala_express_proyecto.modelos.GastoTaller;
import com.mycompany.guatemala_express_proyecto.servicios.buses.BusServicio;
import com.mycompany.guatemala_express_proyecto.exceptions.DatosIncompletosException;
import com.mycompany.guatemala_express_proyecto.exceptions.NoGuardadoEnBDException;

/**
 *
 * @author matul
 */
public class GastoTallerServicio {

    private final GastoTallerDAO gastoTallerDAO;
    private final BusServicio busServicio;

    public GastoTallerServicio() {
        this.gastoTallerDAO = new GastoTallerDAO();
        this.busServicio = new BusServicio();
    }

    public boolean registrarGastoTaller(GastoTaller gastoTaller, int idSucursal)
            throws DatosIncompletosException, NoGuardadoEnBDException, SQLException {

        validarDatos(gastoTaller, idSucursal);
        String numeroPlaca = gastoTaller.getBus().getNumeroPlaca();
        Optional<Bus> busOptional = busServicio.obtenerBusPorNumeroPlaca(numeroPlaca);
        if (busOptional.isEmpty()) {
            throw new NoGuardadoEnBDException("No se encontró el bus seleccionado.");
        }

        Bus bus = busOptional.get();

        if (!bus.isEstado()) {
            throw new NoGuardadoEnBDException("No se puede registrar un gasto para un bus desactivado.");
        }

        if (bus.getSucursal() == null || bus.getSucursal().getId() != idSucursal) {
            throw new NoGuardadoEnBDException("El bus seleccionado no pertenece a su sucursal.");
        }

        gastoTaller.setBus(bus);
        gastoTaller.setFechaMantenimiento(LocalDate.now());

        boolean registrado = gastoTallerDAO.registrarGastoTaller(gastoTaller);

        if (!registrado) {
            throw new NoGuardadoEnBDException("No fue posible registrar el gasto de taller.");
        }

        return true;
    }

    public List<GastoTaller> obtenerGastosPorSucursal(int idSucursal) throws SQLException {
        return gastoTallerDAO.obtenerGastosPorSucursal(idSucursal);
    }

    private void validarDatos(GastoTaller gastoTaller, int idSucursal) throws DatosIncompletosException {

        if (gastoTaller == null || gastoTaller.getBus() == null || gastoTaller.getBus().getNumeroPlaca() == null
                || gastoTaller.getBus().getNumeroPlaca().isBlank() || gastoTaller.getMontoManoDeObra() == null
                || gastoTaller.getMontoRepuestos() == null || idSucursal <= 0) {
            throw new DatosIncompletosException("Por favor, complete todos los campos requeridos.");
        }

        if (gastoTaller.getMontoManoDeObra().compareTo(BigDecimal.ZERO) < 0) {
            throw new DatosIncompletosException("El monto de mano de obra no puede ser negativo.");
        }

        if (gastoTaller.getMontoRepuestos().compareTo(BigDecimal.ZERO) < 0) {
            throw new DatosIncompletosException("El monto de repuestos no puede ser negativo.");
        }
    }

}
