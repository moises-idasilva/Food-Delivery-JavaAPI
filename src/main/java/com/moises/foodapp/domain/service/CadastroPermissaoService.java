package com.moises.foodapp.domain.service;

import com.moises.foodapp.domain.exception.PermissaoNaoEncontradaException;
import com.moises.foodapp.domain.model.Permissao;
import com.moises.foodapp.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroPermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    public Permissao buscarOuFalhar(Long permissaoId) {
        return permissaoRepository.findById(permissaoId)
                .orElseThrow(() -> new PermissaoNaoEncontradaException(permissaoId));
    }
}
