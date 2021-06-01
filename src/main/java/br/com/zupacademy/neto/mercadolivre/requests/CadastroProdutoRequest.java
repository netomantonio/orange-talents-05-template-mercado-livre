package br.com.zupacademy.neto.mercadolivre.requests;

import br.com.zupacademy.neto.mercadolivre.config.anotacoes.ExisteID;
import br.com.zupacademy.neto.mercadolivre.dominios.Categoria;
import br.com.zupacademy.neto.mercadolivre.dominios.Produto;
import br.com.zupacademy.neto.mercadolivre.dominios.Usuario;
import br.com.zupacademy.neto.mercadolivre.repositories.CategoriaRepository;
import io.jsonwebtoken.lang.Assert;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

public class CadastroProdutoRequest {
    @NotBlank(message = "Nome do produto é obrigatório")
    private final String nome;
    private final @Positive BigDecimal valor;
    @PositiveOrZero(message = "Quantidade deve ser igual ou maior a zero")
    private final Integer quantidade;
    @Size(min = 3)
    private final Set<CaracteristicaRequest> caracteristicas;
    @NotBlank(message = "É obrigatório preencher a descrição do produto")
    @Length(max = 1000)
    private final String descricao;
    @ExisteID(domainClass = Categoria.class)
    private final Long idCategoria;

    public CadastroProdutoRequest(String nome, @Positive BigDecimal valor, Integer quantidade, Set<CaracteristicaRequest> caracteristicas, String descricao, Long idCategoria) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
    }


    public Produto toModel(Usuario usuario, CategoriaRepository categoriaRepository) {
        Optional<Categoria> categoria = categoriaRepository.findById(this.idCategoria);
        Assert.isTrue(categoria.isPresent());
        return new Produto(this.nome, this.valor, this.quantidade, this.caracteristicas, this.descricao, categoria.get(), usuario);
    }
}
