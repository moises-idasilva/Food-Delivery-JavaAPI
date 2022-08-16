package com.moises.foodapp.domain.repository;

import com.moises.foodapp.domain.model.FotoProduto;

public interface ProdutoRepositoryQueries {

    FotoProduto save(FotoProduto foto);

    void delete(FotoProduto foto);

}
