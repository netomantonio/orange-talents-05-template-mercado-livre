package br.com.zupacademy.neto.mercadolivre.requests;

import br.com.zupacademy.neto.mercadolivre.compartilhado.RetornoPagamento;
import br.com.zupacademy.neto.mercadolivre.compartilhado.StatusTransacao;
import br.com.zupacademy.neto.mercadolivre.dominios.Compra;
import br.com.zupacademy.neto.mercadolivre.dominios.Transacao;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class TransacaoPaypalRequest implements RetornoPagamento {

    @NotBlank
    private String idTransacao;

    @Min(0)
    @Max(1)
    private int statusCompra;

    public TransacaoPaypalRequest(String idTransacao, int statusCompra, Compra compra) {
        this.idTransacao = idTransacao;
        this.statusCompra = statusCompra;
    }

    public Transacao toTrasacao(Compra compra) {

        StatusTransacao statusNormalizado = statusCompra == 0 ? StatusTransacao.erro : StatusTransacao.sucesso;

        return new Transacao(statusNormalizado, idTransacao, compra);
    }
}
