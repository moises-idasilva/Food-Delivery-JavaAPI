package com.moises.foodapp.api.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.List;

@Setter
@Getter
public class UsuarioModel {

    private Long id;
    private String nome;
    private String email;

}
