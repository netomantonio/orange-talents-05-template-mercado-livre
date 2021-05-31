package br.com.zupacademy.neto.mercadolivre.responses;

import br.com.zupacademy.neto.mercadolivre.dominios.OpiniaoProduto;

public class DetalheProdutoOpiniao {

    private final String titulo;
    private final String descricao;
    private final Integer nota;

    public DetalheProdutoOpiniao(OpiniaoProduto opiniaoProduto) {
        this.titulo = opiniaoProduto.getTitulo();
        this.descricao = opiniaoProduto.getDescricao();
        this.nota = opiniaoProduto.getNota();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getNota() {
        return nota;
    }
}
