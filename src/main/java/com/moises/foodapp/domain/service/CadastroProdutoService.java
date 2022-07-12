package com.moises.foodapp.domain.service;

import com.moises.foodapp.domain.exception.ProdutoNaoEncontradoException;
import com.moises.foodapp.domain.model.Produto;
import com.moises.foodapp.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroProdutoService {

    public static final String MSG_PRODUTO_EM_USO = "Produto de código ID %d não pode ser removida, pois está em uso";

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public Produto salvar(Produto produto) {

        return produtoRepository.save(produto);
    }

    public Produto buscarOuFalhar(Long restauranteId, Long produtoId) {

        return produtoRepository.findById(restauranteId, produtoId)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(restauranteId, produtoId));

    }


}
