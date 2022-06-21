package com.moises.foodapp.domain.service;

import com.moises.foodapp.domain.exception.CozihaNaoEncontradaException;
import com.moises.foodapp.domain.exception.EntidadeEmUsoException;
import com.moises.foodapp.domain.model.Cozinha;
import com.moises.foodapp.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCozinhaService {

    public static final String MSG_COZINHA_EM_USO = "Cozinha de código %d não pode ser removida, pois está em uso";

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha salvar(Cozinha cozinha) {

        return cozinhaRepository.save(cozinha);

    }

    public void excluir(Long cozinhaId) {

        try {
            cozinhaRepository.deleteById(cozinhaId);
        } catch (EmptyResultDataAccessException e) {
            throw new CozihaNaoEncontradaException(cozinhaId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(
                            MSG_COZINHA_EM_USO, cozinhaId).toUpperCase());
        }
    }

    public Cozinha buscarOuFalhar(Long cozinhaId) {
        return cozinhaRepository.findById(cozinhaId)
                .orElseThrow(() -> new CozihaNaoEncontradaException(cozinhaId));
    }

}
