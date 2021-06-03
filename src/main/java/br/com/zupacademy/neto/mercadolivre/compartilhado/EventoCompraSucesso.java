package br.com.zupacademy.neto.mercadolivre.compartilhado;

import br.com.zupacademy.neto.mercadolivre.dominios.Compra;

public interface EventoCompraSucesso {
    void executa(Compra compra);
}
