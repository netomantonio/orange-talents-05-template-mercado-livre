package br.com.zupacademy.neto.mercadolivre.requests;

import br.com.zupacademy.neto.mercadolivre.dominios.Pergunta;
import br.com.zupacademy.neto.mercadolivre.dominios.Produto;
import br.com.zupacademy.neto.mercadolivre.dominios.Usuario;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class PerguntaRequest {

/*
Necessidades
    A pergunta tem um título
    Tem instante de criação
    O usuário que fez a pergunta
    O produto relacionado a pergunta
    O vendedor recebe um email com a pergunta nova
    o email não precisa ser de verdade. Pode ser apenas um print no console do servidor com o corpo.

Restrições
    O título é obrigatório
    O produto é obrigatório
    O usuário é obrigatório

*/

    @NotBlank(message = "O título é obrigatório")
    private String titulo;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public PerguntaRequest(@Valid @NotBlank String titulo) {
        this.titulo = titulo;
    }

    public Pergunta toModel(Produto produto, Usuario curioso) {
        return new Pergunta(this.titulo, curioso, produto);
    }
}
