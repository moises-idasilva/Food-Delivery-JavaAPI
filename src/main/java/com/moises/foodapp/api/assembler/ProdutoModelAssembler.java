package com.moises.foodapp.api.assembler;

import com.moises.foodapp.api.dto.ProdutoModel;
import com.moises.foodapp.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public ProdutoModel toModel(Produto produto) {
        return modelMapper.map(produto, ProdutoModel.class);
    }

    public List<ProdutoModel> toCollectionModel(List<Produto> produtoList) {
        return produtoList.stream()
                .map(produto -> toModel(produto))
                .collect(Collectors.toList());
    }


}
