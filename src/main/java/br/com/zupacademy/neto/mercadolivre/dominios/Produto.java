package br.com.zupacademy.neto.mercadolivre.dominios;

import br.com.zupacademy.neto.mercadolivre.requests.CaracteristicaRequest;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @Positive
    private String valor;
    @PositiveOrZero
    private String quantidade;
    @Size(min = 3)
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private Set<CaracteristicaProduto> caracteristicaProdutos;
    @NotBlank
    private String descricao;
    @ManyToOne
    private Categoria categoria;
    @ManyToOne
    private Usuario anunciante;

    private LocalDate instante = LocalDate.now();

    @Deprecated
    public Produto() {
    }

    public Produto(@NotBlank(message = "Nome do produto é obrigatório") String nome,
                   @Positive(message = "Valor do produto deve ser maior que zero") String valor,
                   @PositiveOrZero(message = "Quantidade deve ser igual ou maior a zero") String quantidade,
                   @NotBlank(message = "Todo produto deve ter minimo de 3 caracteristicaProdutos")
                   @Size(min = 3, message = "minimo de 3 caracteristicaProdutos") Set<CaracteristicaRequest> caracteristicas,
                   @NotBlank(message = "É obrigatório preencher a descrição do produto") String descricao,
                   Categoria categoria,
                   Usuario anunciante) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        Set<CaracteristicaProduto> nCaracteristicaProdutos = new HashSet<>();

        caracteristicas.forEach(
                caracteristica -> nCaracteristicaProdutos.add(
                        new CaracteristicaProduto(
                                caracteristica.getNome(),
                                caracteristica.getDescricao(),
                                this)));


        this.caracteristicaProdutos = nCaracteristicaProdutos;
        this.descricao = descricao;
        this.categoria = categoria;
        this.anunciante = anunciante;
    }
}