package br.com.zupacademy.neto.mercadolivre.dominios;

import br.com.zupacademy.neto.mercadolivre.requests.CaracteristicaRequest;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    private @Positive BigDecimal valor;
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

    private final LocalDate instante = LocalDate.now();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<ImagemProduto> imagens = new ArrayList<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<Pergunta> perguntas = new ArrayList<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<OpiniaoProduto> opinioes = new ArrayList<>();

    @Deprecated
    public Produto() {
    }

    public Produto(@NotBlank(message = "Nome do produto é obrigatório") String nome,
                   @Positive BigDecimal valor,
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

    public Usuario getAnunciante() {
        return this.anunciante;
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return this.id;
    }

    public void addImagens(List<ImagemProduto> novasImagens) {
        this.imagens = imagens;
    }

    public void addOpiniao(OpiniaoProduto opiniao) {
        this.opinioes.add(opiniao);
    }


    public String getDescricao() {
        return this.descricao;
    }

    public BigDecimal getValor() {
        return this.valor;
    }

    public List<OpiniaoProduto> getOpinioes() {
        return this.opinioes;
    }

    public List<ImagemProduto> getImagens() {
        return this.imagens;
    }

    public Set<CaracteristicaProduto> getCaracteristicas() {
        return this.caracteristicaProdutos;
    }

    public List<Pergunta> getPerguntas() {
        return this.perguntas;
    }

    public <T> Set<T> mapCaracteristicas(Function<CaracteristicaProduto, T> funcaoMapeadora) {
        return this.caracteristicaProdutos.stream().map(funcaoMapeadora).collect(Collectors.toSet());
    }

    public <T> Set<T> mapImagens(Function<ImagemProduto, T> funcaoMapeadora) {
        return this.imagens.stream().map(funcaoMapeadora).collect(Collectors.toSet());
    }

    public <T> Set<T> mapOpinioes(Function<OpiniaoProduto, T> funcaoMapeadora) {
        return this.opinioes.stream().map(funcaoMapeadora).collect(Collectors.toSet());
    }

    public <T> Set<T> mapPerguntas(Function<Pergunta, T> funcaoMapeadora) {
        return this.perguntas.stream().map(funcaoMapeadora).collect(Collectors.toSet());
    }
}
