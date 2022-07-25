package com.moises.foodapp.api.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.moises.foodapp.api.dto.view.RestauranteView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaModel {

    @JsonView(RestauranteView.Resumo.class)
    private Long id;

    @JsonView(RestauranteView.Resumo.class)
    private String nome;

}
