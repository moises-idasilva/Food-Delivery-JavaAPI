package com.moises.foodapp.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moises.foodapp.api.assembler.RestauranteModelAssembler;
import com.moises.foodapp.api.assembler.RestauranteInputDisassembler;
import com.moises.foodapp.api.dto.RestauranteModel;
import com.moises.foodapp.api.dto.input.RestauranteInput;
import com.moises.foodapp.domain.exception.CozihaNaoEncontradaException;
import com.moises.foodapp.domain.exception.NegocioException;
import com.moises.foodapp.domain.model.Restaurante;
import com.moises.foodapp.domain.repository.RestauranteRepository;
import com.moises.foodapp.domain.service.CadastroRestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @Autowired
    private RestauranteModelAssembler restauranteModelAssembler;

    @Autowired
    private RestauranteInputDisassembler restauranteInputDisassembler;

    @GetMapping
    public List<RestauranteModel> listar() {

        return restauranteModelAssembler.toCollectionModel(restauranteRepository.findAll());
    }

    @GetMapping("/{id}")
    public RestauranteModel buscar(@PathVariable Long id) {

        Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(id);

        RestauranteModel restauranteModel = restauranteModelAssembler.toModel(restaurante);

        return restauranteModel;

    }

    @GetMapping("/por-taxa-frete")
    public List<Restaurante> restaurantesPorTaxaFrete(
            BigDecimal taxaInicial, BigDecimal taxaFinal) {
        return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
    }

    @GetMapping("/por-nome")
    public List<Restaurante> restaurantesPorNome(String nome) {
        return restauranteRepository.findByNomeContainingIgnoreCase(nome);
    }

    @GetMapping("/por-nome-e-cozinhaid")
    public List<Restaurante> restaurantesPorNomeECozinhaId(String nome, Long cozinhaId) {
        return restauranteRepository.listarPorNomeECozinhaId(nome, cozinhaId);
        //return restauranteRepository.findByNomeContainingAndCozinhaId(nome, cozinhaId);
    }

    @GetMapping("/por-nome-e-ou-frete")
    public List<Restaurante> restaurantesPorNomeFrete(String nome,
                                                      BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
        return restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteModel adicionar(@RequestBody @Valid RestauranteInput restauranteInput) {

        try {
            Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);

            return restauranteModelAssembler.toModel(cadastroRestauranteService.salvar(restaurante));
        } catch (CozihaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public RestauranteModel atualizar(@PathVariable Long id, @RequestBody @Valid RestauranteInput restauranteInput) {

        try {
//            Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);

            Restaurante restauranteAtual = cadastroRestauranteService.buscarOuFalhar(id);

            restauranteInputDisassembler.copyToDomainObject(restauranteInput, restauranteAtual);

//            BeanUtils.copyProperties(restaurante, restauranteAtual,
//                    "id", "formasPagamento", "endereco", "dataCadastro", "produtos");

            return restauranteModelAssembler.toModel(cadastroRestauranteService.salvar(restauranteAtual));
        } catch (CozihaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }

    }

    // Para atualização parcial apenas para referencia
//    @PatchMapping("/{id}")
//    public RestauranteModel atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos) {
//
//        Restaurante restauranteAtual = cadastroRestauranteService.buscarOuFalhar(id);
//
//        //chamar o método merge() que ira mesclar as informações da entidade chamada com a entidade temporária
//        merge(campos, restauranteAtual);
//
//        return toModel(atualizar(id, restauranteAtual));
//    }

    private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {

        // objectMapper para fazer a conversão automática dos valores
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

        dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {

            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            field.setAccessible(true); //para acessar uma variável privada

            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

            System.out.println(nomePropriedade + " = " + novoValor);

            ReflectionUtils.setField(field, restauranteDestino, novoValor);
        });
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {

        cadastroRestauranteService.excluir(id);
    }


}
