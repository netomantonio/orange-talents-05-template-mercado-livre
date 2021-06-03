package br.com.zupacademy.neto.mercadolivre.compartilhado;

public enum StatusCompraPagseguro {
    SUCESSO,
    ERRO;

    public StatusTransacao normaliza() {
        if (this.equals(SUCESSO)) return StatusTransacao.sucesso;

        return StatusTransacao.erro;
    }
}
