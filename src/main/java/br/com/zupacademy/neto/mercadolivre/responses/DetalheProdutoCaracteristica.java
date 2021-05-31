package br.com.zupacademy.neto.mercadolivre.responses;

import br.com.zupacademy.neto.mercadolivre.dominios.CaracteristicaProduto;

public class DetalheProdutoCaracteristica {
    private final String descricao;
    private final String nome;

    public DetalheProdutoCaracteristica(CaracteristicaProduto caracteristica) {
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNome() {
        return nome;
    }
}
