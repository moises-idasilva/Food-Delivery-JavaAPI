package com.moises.foodapp.api.controller;

import com.moises.foodapp.api.model.CozinhasXmlWrapper;
import com.moises.foodapp.domain.model.Cozinha;
import com.moises.foodapp.domain.repository.CozinhaRepository;
import com.moises.foodapp.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    private Cozinha cozinha;

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;


    @GetMapping(produces = "application/json")
    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    // Apenas para referência de como retornar um XML
    @GetMapping(produces = "application/xml")
    public CozinhasXmlWrapper listarXML() {
        return new CozinhasXmlWrapper(cozinhaRepository.findAll());
    }

    @GetMapping("/{cozinhaId}")
    public Cozinha buscar(@PathVariable Long cozinhaId) {

        return cadastroCozinhaService.buscarOuFalhar(cozinhaId);
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
    public Cozinha adicionar(@RequestBody Cozinha cozinha) {

        return cadastroCozinhaService.salvar(cozinha);
    }

    @PutMapping("/{id}")
    public Cozinha atualizar(@PathVariable Long id, @RequestBody Cozinha cozinha) {

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
