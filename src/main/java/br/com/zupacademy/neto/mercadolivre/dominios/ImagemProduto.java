package br.com.zupacademy.neto.mercadolivre.dominios;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class ImagemProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String link;

    @ManyToOne
    private Produto produto;

    @Deprecated
    public ImagemProduto() {
    }

    public ImagemProduto(String link, Produto produto) {
        this.link = link;
        this.produto = produto;
    }

    public String getLink() {
        return link;
    }
}
