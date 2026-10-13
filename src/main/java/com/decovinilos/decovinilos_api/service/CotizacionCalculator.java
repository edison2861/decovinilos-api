package com.decovinilos.decovinilos_api.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CotizacionCalculator {

    private static final int PANEL_WIDTH_CM = 55;
    private static final int MARGIN_CM = 5;

    private CotizacionCalculator() {
    }

    public record Resultado(
            int anchoEfectivoCm,
            int altoEfectivoCm,
            BigDecimal areaM2,
            int cantidadPaneles,
            BigDecimal precioTotal
    ) {}

    public static Resultado calcular(int anchoCm, int altoCm, boolean incluyeMargen, BigDecimal precioPorM2) {
        int anchoSeguro = Math.max(50, anchoCm);
        int altoSeguro = Math.max(50, altoCm);

        int anchoEfectivo = incluyeMargen ? anchoSeguro + MARGIN_CM : anchoSeguro;
        int altoEfectivo = incluyeMargen ? altoSeguro + MARGIN_CM : altoSeguro;

        BigDecimal areaM2 = BigDecimal.valueOf(anchoEfectivo)
                .multiply(BigDecimal.valueOf(altoEfectivo))
                .divide(BigDecimal.valueOf(10000), 4, RoundingMode.HALF_UP);

        int cantidadPaneles = (int) Math.ceil(anchoEfectivo / (double) PANEL_WIDTH_CM);

        BigDecimal precioTotal = areaM2.multiply(precioPorM2).setScale(2, RoundingMode.HALF_UP);

        return new Resultado(
                anchoEfectivo,
                altoEfectivo,
                areaM2.setScale(2, RoundingMode.HALF_UP),
                cantidadPaneles,
                precioTotal
        );
    }
}