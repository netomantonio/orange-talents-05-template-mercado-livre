package br.com.zupacademy.neto.mercadolivre.dominios;

import javax.persistence.*;
import javax.validation.constraints.Null;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @ManyToOne
    private Categoria mae;

    @Deprecated
    public Categoria(){}

    public Categoria(String nome) {

        this.nome = nome;
    }

    public void setCategoriaMae(Categoria mae){
        this.mae = mae;
    }
}
