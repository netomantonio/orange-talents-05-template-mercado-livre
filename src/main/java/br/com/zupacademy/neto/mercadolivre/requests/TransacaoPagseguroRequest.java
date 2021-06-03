package br.com.zupacademy.neto.mercadolivre.requests;

import br.com.zupacademy.neto.mercadolivre.compartilhado.RetornoPagamento;
import br.com.zupacademy.neto.mercadolivre.compartilhado.StatusCompraPagseguro;
import br.com.zupacademy.neto.mercadolivre.dominios.Compra;
import br.com.zupacademy.neto.mercadolivre.dominios.Transacao;

import javax.validation.constraints.NotBlank;

public class TransacaoPagseguroRequest implements RetornoPagamento {

    @NotBlank
    private String idTransacao;

    @NotBlank
    private StatusCompraPagseguro statusCompra;

    public TransacaoPagseguroRequest(String idTransacao, StatusCompraPagseguro statusCompra, Compra compra) {
        this.idTransacao = idTransacao;
        this.statusCompra = statusCompra;
    }

    public Transacao toTrasacao(Compra compra) {
        return new Transacao(statusCompra.normaliza(), idTransacao, compra);
    }
}
