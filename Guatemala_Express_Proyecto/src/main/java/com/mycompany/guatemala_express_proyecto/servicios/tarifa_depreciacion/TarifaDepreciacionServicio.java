/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guatemala_express_proyecto.servicios.tarifa_depreciacion;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Optional;

import com.mycompany.guatemala_express_proyecto.exceptions.DatosIncompletosException;
import com.mycompany.guatemala_express_proyecto.exceptions.NoGuardadoEnBDException;
import com.mycompany.guatemala_express_proyecto.daos.ConfiguracionDAO;
import com.mycompany.guatemala_express_proyecto.modelos.Configuracion;

/**
 *
 * @author matul
 */
public class TarifaDepreciacionServicio {

    private static final int ID_TARIFA_DEPRECIACION = 1;
    private final ConfiguracionDAO configuracionDAO;

    public TarifaDepreciacionServicio() {
        this.configuracionDAO = new ConfiguracionDAO();
    }

    public Optional<Configuracion> obtenerTarifaDepreciacion() throws SQLException {
        return configuracionDAO.obtenerConfiguracionPorId(ID_TARIFA_DEPRECIACION);
    }

    public boolean actualizarTarifaDepreciacion(Configuracion configuracion)
            throws DatosIncompletosException, NoGuardadoEnBDException, SQLException {

        validarTarifa(configuracion);
        configuracion.setId(ID_TARIFA_DEPRECIACION);

        boolean actualizada = configuracionDAO.actualizarValor(configuracion);

        if (!actualizada) {
            throw new NoGuardadoEnBDException("No fue posible actualizar la tarifa de depreciación.");
        }

        return true;
    }

    private void validarTarifa(Configuracion configuracion) throws DatosIncompletosException {

        if (configuracion == null || configuracion.getValor() == null) {
            throw new DatosIncompletosException("Debe ingresar la tarifa de depreciación.");
        }

        if (configuracion.getValor().compareTo(BigDecimal.ZERO) < 0) {
            throw new DatosIncompletosException("La tarifa de depreciación no puede ser negativa.");
        }
    }

}
