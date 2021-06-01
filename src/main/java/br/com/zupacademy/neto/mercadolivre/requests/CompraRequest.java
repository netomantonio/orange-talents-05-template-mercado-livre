package br.com.zupacademy.neto.mercadolivre.requests;

import br.com.zupacademy.neto.mercadolivre.compartilhado.MetodoPagamento;
import br.com.zupacademy.neto.mercadolivre.config.anotacoes.ExisteID;
import br.com.zupacademy.neto.mercadolivre.dominios.Compra;
import br.com.zupacademy.neto.mercadolivre.dominios.Produto;
import br.com.zupacademy.neto.mercadolivre.dominios.Usuario;

import javax.validation.constraints.Positive;

public class CompraRequest {


    private MetodoPagamento gatewayPagamento;

    @ExisteID(domainClass = Produto.class, message = "Produto não existe")
    private Long idProduto;

    @Positive
    private Integer quantidade;

    public CompraRequest(MetodoPagamento gatewayPagamento, Long idProduto, Integer quantidade) {
        this.gatewayPagamento = gatewayPagamento;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Compra toModel(Produto produtoCompra, Usuario usuarioLogado) {
        return new Compra(produtoCompra, usuarioLogado, this);
    }

    public MetodoPagamento getMetodoPagamento() {
        return gatewayPagamento;
    }
}
