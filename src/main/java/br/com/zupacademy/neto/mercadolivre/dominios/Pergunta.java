package br.com.zupacademy.neto.mercadolivre.dominios;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @NotNull
    @ManyToOne
    private Usuario curioso;

    @NotNull
    @ManyToOne
    private Produto produto;
    private LocalDateTime criadoEm = LocalDateTime.now();

    @Deprecated
    public Pergunta() {
    }

    public Pergunta(String titulo, Usuario curioso, Produto produto) {
        this.titulo = titulo;
        this.curioso = curioso;
        this.produto = produto;
    }


    public String getTitulo() {
        return titulo;
    }

    public String getCurioso() {
        return curioso.getUsername();
    }

    public String getEmailAnunciante() {
        return this.produto.getAnunciante().getUsername();
    }

    public String getNomeProduto() {
        return this.produto.getNome();
    }
}
