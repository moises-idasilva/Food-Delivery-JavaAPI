package com.moises.foodapp.api.controller;

import com.moises.foodapp.api.assembler.EstadoInputDisassembler;
import com.moises.foodapp.api.assembler.EstadoModelAssembler;
import com.moises.foodapp.api.dto.EstadoModel;
import com.moises.foodapp.api.dto.input.EstadoInput;
import com.moises.foodapp.domain.model.Estado;
import com.moises.foodapp.domain.repository.EstadoRepository;
import com.moises.foodapp.domain.service.CadastroEstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroEstadoService cadastroEstadoService;

    @Autowired
    private EstadoModelAssembler estadoModelAssembler;

    @Autowired
    private EstadoInputDisassembler estadoInputDisassembler;

    @GetMapping
    public List<EstadoModel> listar() {

        List<Estado> todosEstados = estadoRepository.findAll();

        return estadoModelAssembler.toCollectionModel(todosEstados);
    }

    @GetMapping("/{id}")
    public EstadoModel buscar(@PathVariable Long id) {

        Estado estado = cadastroEstadoService.buscarOuFalhar(id);

        return estadoModelAssembler.toModel(estado);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoModel adicionar(@RequestBody @Valid EstadoInput estadoInput) {

        Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);

        estado = cadastroEstadoService.salvar(estado);

        return estadoModelAssembler.toModel(estado);
    }

    @PutMapping("/{id}")
    public Estado atualizar(@PathVariable Long id, @RequestBody @Valid Estado estado) {

        Estado estadoAtual = cadastroEstadoService.buscarOuFalhar(id);

        BeanUtils.copyProperties(estado, estadoAtual, "id");

        return cadastroEstadoService.salvar(estadoAtual);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {

        cadastroEstadoService.excluir(id);

    }


}
