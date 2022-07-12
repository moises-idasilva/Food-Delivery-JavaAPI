package com.moises.foodapp.domain.model;

import com.moises.foodapp.core.validation.ValorZeroIncluiDescricao;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ValorZeroIncluiDescricao(valorField = "taxaFrete",
        descricaoField = "nome", descricaoObrigatoria = "Frete Grátis")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    //@NotNull
   // @PositiveOrZero
    //@TaxaFrete
    //@Multiplo(numero = 5)
    @Column(name = "taxa_frete", nullable = false)
    private BigDecimal taxaFrete;

//    @Valid
//    @ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
//    @NotNull
    @ManyToOne
    @JoinColumn(name = "cozinha_id", nullable = false)
    private Cozinha cozinha;

    @Embedded
    private Endereco endereco;

    private Boolean ativo = Boolean.TRUE;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataCadastro;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataAtualizacao;

    @ManyToMany
    @JoinTable(name = "restaurante_forma_pagamento",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private Set<FormaPagamento> formasPagamento = new HashSet<>();

    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "restaurante_usuario_responsavel",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private Set<Usuario> responsaveis = new HashSet<>();

    public void ativar(){
        setAtivo(true);
    }

    public void inativar(){
        setAtivo(false);
    }

    private Boolean aberto = Boolean.FALSE;

    public boolean removerFormaPagamento(FormaPagamento formaPagamento) {
        return getFormasPagamento().remove(formaPagamento);
    }

    public boolean adicionarFormaPagamento(FormaPagamento formaPagamento) {
        return getFormasPagamento().add(formaPagamento);
    }

    public void abrir() {
        setAberto(true);
    }

    public void fechar() {
        setAberto(false);
    }

    // métodos para adicionar e remover Responsáveis
    public boolean removerResponsavel(Usuario usuario) {
        return getResponsaveis().remove(usuario);
    }

    public boolean adicionarResponsavel(Usuario usuario) {
        return getResponsaveis().add(usuario);
    }

}
