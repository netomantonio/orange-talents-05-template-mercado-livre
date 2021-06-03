package br.com.zupacademy.neto.mercadolivre.compartilhado;

import br.com.zupacademy.neto.mercadolivre.dominios.Compra;
import br.com.zupacademy.neto.mercadolivre.dominios.Transacao;

public interface RetornoPagamento {
    Transacao toTrasacao(Compra compra);
}
