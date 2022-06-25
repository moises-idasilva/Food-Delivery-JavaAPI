package com.moises.foodapp.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "sub_total", nullable = false)
    private BigDecimal subTotal;

    @Column(name = "taxa_frete", nullable = false)
    private BigDecimal taxaFrete;

    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;

    @CreationTimestamp
    @Column(name = "data_criacao", columnDefinition = "datetime", nullable = false)
    private OffsetDateTime dataCriacao;

    @Column(name = "data_confirmacao", columnDefinition = "datetime")
    private OffsetDateTime dataConfirmacao;

    @Column(name = "data_cancelamento", columnDefinition = "datetime")
    private  OffsetDateTime dataCancelamento;

    @Column(name = "data_entrega", columnDefinition = "datetime")
    private OffsetDateTime dataEntrega;

    @Embedded
    @Column(name = "endereco_entrega")
    private Endereco enderecoEntrega;


    @Column(name = "status_pedido")
    private StatusPedido statusPedido;

    @ManyToOne
    @JoinColumn(nullable = false)
    private FormaPagamento formaPagamento;

    @ManyToOne
    @JoinColumn(name = "usuario_cliente_id", nullable = false)
    private Usuario cliente;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens = new ArrayList<>();

}
