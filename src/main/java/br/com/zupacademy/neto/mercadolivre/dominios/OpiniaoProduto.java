package br.com.zupacademy.neto.mercadolivre.dominios;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
public class OpiniaoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Usuario user;

    private @Min(1) @Max(5) Integer nota;
    @NotBlank
    private String titulo;
    @NotBlank
    @Length(max = 500)
    private String descricao;

    @Deprecated
    public OpiniaoProduto() {
    }

    public OpiniaoProduto(Produto produto,
                          Usuario user,
                          Integer nota,
                          @NotBlank @Length(max = 60) String titulo,
                          @NotBlank @Length(max = 500) String descricao) {
        this.produto = produto;
        this.user = user;
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }
}
