package com.moises.foodapp.api.controller;

import com.moises.foodapp.api.assembler.CozinhaInputDisassembler;
import com.moises.foodapp.api.assembler.CozinhaModelAssembler;
import com.moises.foodapp.api.dto.CozinhaModel;
import com.moises.foodapp.api.dto.input.CozinhaInput;
import com.moises.foodapp.api.model.CozinhasXmlWrapper;
import com.moises.foodapp.domain.model.Cozinha;
import com.moises.foodapp.domain.repository.CozinhaRepository;
import com.moises.foodapp.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    private Cozinha cozinha;

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    @Autowired
    private CozinhaModelAssembler cozinhaModelAssembler;

    @Autowired
    private CozinhaInputDisassembler cozinhaInputDisassembler;


    @GetMapping
    public Page<CozinhaModel> listar(@PageableDefault(size = 10) Pageable pageable) {
        Page<Cozinha> cozinhasPage = cozinhaRepository.findAll(pageable);

        List<CozinhaModel> cozinhasModel = cozinhaModelAssembler.toCollectionModel(cozinhasPage.getContent());

        Page<CozinhaModel> cozinhasModelPage = new PageImpl<>(cozinhasModel, pageable, cozinhasPage.getTotalElements());

        return cozinhasModelPage;
    }

    // Apenas para referência de como retornar um XML
    @GetMapping(produces = "application/xml")
    public CozinhasXmlWrapper listarXML() {
        return new CozinhasXmlWrapper(cozinhaRepository.findAll());
    }

    @GetMapping("/{cozinhaId}")
    public CozinhaModel buscar(@PathVariable Long cozinhaId) {
        Cozinha cozinha = cadastroCozinhaService.buscarOuFalhar(cozinhaId);

        return cozinhaModelAssembler.toModel(cozinha);
    }

    @GetMapping("/busca-por-nome")
    public ResponseEntity<List<Cozinha>> cozinhasPorNome(String nome) {
        List<Cozinha> cozinha = cozinhaRepository.findByNomeIsContainingIgnoreCase(nome);

        if (!cozinha.isEmpty()) {
            return ResponseEntity.ok(cozinha);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaModel adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {
        Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInput);
        cozinha = cadastroCozinhaService.salvar(cozinha);

        return cozinhaModelAssembler.toModel(cozinha);
    }

    @PutMapping("/{id}")
    public Cozinha atualizar(@PathVariable Long id, @RequestBody @Valid Cozinha cozinha) {

        Cozinha cozinhaAtual = cadastroCozinhaService.buscarOuFalhar(id);

        //Utilizar o BeanUtils para copiar as propriedades de cozinha para cozinhaAtual.
        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");

        return cadastroCozinhaService.salvar(cozinhaAtual);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        cadastroCozinhaService.excluir(id);

    }


}
