package com.moises.foodapp.domain.service;

import com.moises.foodapp.domain.filter.VendaDiariaFilter;

public interface VendaReportService {

    byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset);

}
