package com.moises.foodapp.api.assembler;

import com.moises.foodapp.api.dto.input.RestauranteInput;
import com.moises.foodapp.domain.model.Cozinha;
import com.moises.foodapp.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Restaurante toDomainObject(RestauranteInput restauranteInput) {
//        Restaurante restaurante = new Restaurante();
//        restaurante.setNome(restauranteInput.getNome());
//        restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());
//
//        Cozinha cozinha = new Cozinha();
//        cozinha.setId(restauranteInput.getCozinha().getId());
//
//        restaurante.setCozinha(cozinha);
//
//        return restaurante;

        return modelMapper.map(restauranteInput, Restaurante.class);

    }

    public void copyToDomainObject(RestauranteInput restauranteInput, Restaurante restaurante) {

        //Para evitar Exception e poder atualizar a cozinha de um restaurante:
        //org.hibernate.HibernateException: identifier of an instance of com.moises.foodapp.domain.model.Cozinha was altered from 1 to 2
        restaurante.setCozinha(new Cozinha());

        modelMapper.map(restauranteInput, restaurante);
    }

}
