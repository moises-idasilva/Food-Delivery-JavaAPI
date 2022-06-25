package com.moises.foodapp.api.dto.input;

import com.moises.foodapp.api.dto.EstadoModel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadeModel {

    private Long id;
    private String nome;
    private EstadoModel estado;

}
