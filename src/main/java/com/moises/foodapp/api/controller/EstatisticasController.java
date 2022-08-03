package com.moises.foodapp.api.controller;

import com.moises.foodapp.domain.filter.VendaDiariaFilter;
import com.moises.foodapp.domain.model.dto.VendaDiaria;
import com.moises.foodapp.domain.service.VendaQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/estatisticas")
public class EstatisticasController {

    @Autowired
    private VendaQueryService vendaQueryService;

    @GetMapping("/vendas-diarias")
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro){

        return vendaQueryService.consultarVendasDiarias(filtro);
    }

}
