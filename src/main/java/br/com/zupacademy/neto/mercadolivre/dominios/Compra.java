package br.com.zupacademy.neto.mercadolivre.dominios;

import antlr.collections.List;
import br.com.zupacademy.neto.mercadolivre.compartilhado.MetodoPagamento;
import br.com.zupacademy.neto.mercadolivre.compartilhado.RetornoPagamento;
import br.com.zupacademy.neto.mercadolivre.compartilhado.StatusCompra;
import br.com.zupacademy.neto.mercadolivre.requests.CompraRequest;
import br.com.zupacademy.neto.mercadolivre.requests.TransacaoPagseguroRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes = new HashSet<>();

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

    public void addTransacao(@Valid RetornoPagamento request) {
        Transacao novaTransacao = request.toTrasacao(this);
        Assert.isTrue(!this.transacoes.contains(novaTransacao), "Já existe uma transação igual a essa processada" + novaTransacao);

        Assert.isTrue(trasacoesConcluidasComSucesso().isEmpty(), "Essa compra já foi concluída com sucesso");
        this.transacoes.add(novaTransacao);
    }

    @NotNull
    private Set<Transacao> trasacoesConcluidasComSucesso() {
        Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream().filter(Transacao::concluidaComSucesso).collect(Collectors.toSet());
        Assert.isTrue(transacoesConcluidasComSucesso.size() <= 1, "Falha no sistema, alguém me desconfigurou!\n" +
                "existe mais de uma transação concluída com sucesso para esta compra id:" + this.id);
        return transacoesConcluidasComSucesso;
    }

    public boolean processadaComSucesso() {
        return !this.trasacoesConcluidasComSucesso().isEmpty();
    }

    public Usuario getComprador() {
        return this.comprador;
    }

    public Usuario getVendedor() {
        return this.produto.getAnunciante();
    }
}
