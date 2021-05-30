package br.com.zupacademy.neto.mercadolivre.requests;


import br.com.zupacademy.neto.mercadolivre.dominios.OpiniaoProduto;
import br.com.zupacademy.neto.mercadolivre.dominios.Produto;
import br.com.zupacademy.neto.mercadolivre.dominios.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/*
*   Tem uma nota que vai de 1 a 5
    Tem um título. Ex: espetacular, horrível...
    Tem uma descrição
    O usuário logado que fez a pergunta (aqui pode usar
        o approach de definir um usuário na primeira linha
        do controller e depois trabalhar com o logado de verdade)
    O produto que para o qual a pergunta foi direcionada
* */
public class OpiniaoRequest {

    @Min(value = 1, message = "valor minimo é 1")
    @Max(value = 5, message = "valor máximo é 5")
    private Integer nota;

    @NotBlank(message = "O Título não pode estar em branco")
    @Length(max = 60, message = "Titulo deve ter no máximo 60 caractéres")
    private String titulo;

    @NotBlank(message = "A descrição não pode estar em branco")
    @Length(max = 500, message = "Descrição deve ter no máximo 500 caractéres")
    private String descricao;

    public OpiniaoRequest(Integer nota, @NotBlank String titulo, @NotBlank String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public OpiniaoProduto toModel(Produto produto, Usuario user) {
        return new OpiniaoProduto(produto, user, this.nota, this.titulo, this.descricao);
    }
}
