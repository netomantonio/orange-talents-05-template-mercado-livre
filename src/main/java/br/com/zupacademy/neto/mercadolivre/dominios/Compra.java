package br.com.zupacademy.neto.mercadolivre.dominios;

import br.com.zupacademy.neto.mercadolivre.compartilhado.MetodoPagamento;
import br.com.zupacademy.neto.mercadolivre.compartilhado.StatusCompra;
import br.com.zupacademy.neto.mercadolivre.requests.CompraRequest;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private StatusCompra status;

    @Enumerated(EnumType.STRING)
    private MetodoPagamento metodoPagamento;
    @ManyToOne
    private Produto produto;
    @Positive
    private Integer quantidade;
    @ManyToOne
    private Usuario comprador;
    @Positive
    private BigDecimal valor;

    @Deprecated
    public Compra() {
    }

    public Compra(Produto produtoCompra, Usuario comprador, CompraRequest compraRequest) {
        this.metodoPagamento = compraRequest.getMetodoPagamento();
        this.produto = produtoCompra;
        this.quantidade = compraRequest.getQuantidade();
        this.comprador = comprador;
        this.valor = produtoCompra.getValor();
        this.status = StatusCompra.INICIADA;
    }

    public String getEmailComprador() {
        return this.comprador.getUsername();
    }

    public String getNomeProduto() {
        return this.produto.getNome();
    }

    public String getMetodoPagamento() {
        return metodoPagamento.toString();
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getStatus() {
        return status.toString();
    }

    public Long getId() {
        return id;
    }
}
