package br.com.zupacademy.neto.mercadolivre.responses;

import br.com.zupacademy.neto.mercadolivre.dominios.Pergunta;

public class DetalheProdutoPergunta {

    private final String titulo;
    private final String interessado;

    public DetalheProdutoPergunta(Pergunta pergunta) {
        this.titulo = pergunta.getTitulo();
        this.interessado = pergunta.getCurioso();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getInteressado() {
        return interessado;
    }
}
