package com.moises.foodapp.api.assembler;

import com.moises.foodapp.api.dto.FotoProdutoModel;
import com.moises.foodapp.domain.model.FotoProduto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FotoProdutoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public FotoProdutoModel toModel(FotoProduto  foto) {

        return modelMapper.map(foto, FotoProdutoModel.class);
    }


}
