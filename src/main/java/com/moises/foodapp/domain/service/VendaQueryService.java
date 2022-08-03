package com.moises.foodapp.domain.service;

import com.moises.foodapp.domain.filter.VendaDiariaFilter;
import com.moises.foodapp.domain.model.dto.VendaDiaria;

import java.util.List;


public interface VendaQueryService {

    List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro);

}
