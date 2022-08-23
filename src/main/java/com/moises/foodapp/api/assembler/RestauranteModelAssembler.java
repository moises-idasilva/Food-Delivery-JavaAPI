package com.moises.foodapp.api.assembler;

import com.moises.foodapp.api.dto.RestauranteModel;
import com.moises.foodapp.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestauranteModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public RestauranteModel toModel(Restaurante restaurante) {
//        CozinhaModel cozinhaModel = new CozinhaModel();
//        cozinhaModel.setId(restaurante.getCozinha().getId());
//        cozinhaModel.setNome(restaurante.getCozinha().getNome());
//
//        RestauranteModel restauranteModel = new RestauranteModel();
//        restauranteModel.setId(restaurante.getId());
//        restauranteModel.setNome(restaurante.getNome());
//        restauranteModel.setTaxaFrete(restaurante.getTaxaFrete());
//        restauranteModel.setCozinha(cozinhaModel);
//        return restauranteModel;

        return modelMapper.map(restaurante, RestauranteModel.class);

    }

    public List<RestauranteModel> toCollectionModel(List<Restaurante> restaurantes) {
        return restaurantes.stream()
                .map(restaurante -> toModel(restaurante))
                .collect(Collectors.toList());
    }

}
