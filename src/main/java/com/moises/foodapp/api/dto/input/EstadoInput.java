package com.moises.foodapp.api.dto.input;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class EstadoInput {

    @NotBlank
    private String nome;

}
