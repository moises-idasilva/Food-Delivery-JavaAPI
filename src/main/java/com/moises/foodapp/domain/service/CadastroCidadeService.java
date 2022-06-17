package com.moises.foodapp.domain.service;

import com.moises.foodapp.domain.exception.EntidadeEmUsoException;
import com.moises.foodapp.domain.exception.EntidadeNaoEncontradaException;
import com.moises.foodapp.domain.model.Cidade;
import com.moises.foodapp.domain.model.Estado;
import com.moises.foodapp.domain.repository.CidadeRepository;
import com.moises.foodapp.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCidadeService {

    public static final String MSG_CIDADE_NAO_ENCONTRADA = "Não existe cadastro de estado com código %d";

    public static final String MSG_CIDADE_EM_USO = "Cidade de código ID %d não pode ser removida, pois está em uso";

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroEstadoService cadastroEstadoService;

    @Autowired
    private EstadoRepository estadoRepository;

    public Cidade salvar(Cidade cidade) {

        Long estadoId = cidade.getEstado().getId();
        Estado estado = cadastroEstadoService.buscarOuFalhar(estadoId);

        cidade.setEstado(estado);

        return cidadeRepository.save(cidade);
    }

    public Cidade buscarOuFalhar(Long id){
        return cidadeRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MSG_CIDADE_NAO_ENCONTRADA, id)));
    }

    public void excluir(Long cidadeId) {

        try {
            cidadeRepository.deleteById(cidadeId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format(
                            MSG_CIDADE_NAO_ENCONTRADA, cidadeId).toUpperCase());
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(
                            MSG_CIDADE_EM_USO, cidadeId).toUpperCase());
        }

    }

}
