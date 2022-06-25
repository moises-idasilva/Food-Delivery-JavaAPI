package com.moises.foodapp.domain.service;

import com.moises.foodapp.domain.exception.EntidadeEmUsoException;
import com.moises.foodapp.domain.exception.EntidadeNaoEncontradaException;
import com.moises.foodapp.domain.exception.EstadoNaoEncontradoException;
import com.moises.foodapp.domain.model.Estado;
import com.moises.foodapp.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroEstadoService {

//    public static final String MSG_ESTADO_NAO_ENCONTRADO = "Não existe um cadastro de Estado com ID:  %d";

    public static final String MSG_ESTADO_EM_USO = "Estado de código ID %d não pode ser removida, pois está em uso";

    @Autowired
    private EstadoRepository estadoRepository;

    @Transactional
    public Estado salvar(Estado estado) {

        return estadoRepository.save(estado);

    }

    public Estado buscarOuFalhar(Long estadoId){

        return estadoRepository.findById(estadoId)
                .orElseThrow(() -> new EstadoNaoEncontradoException(estadoId));
    }

    @Transactional
    public void excluir(Long id) {

        try {
            estadoRepository.deleteById(id);
            estadoRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EstadoNaoEncontradoException(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_ESTADO_EM_USO, id).toUpperCase());
        }

    }

}
