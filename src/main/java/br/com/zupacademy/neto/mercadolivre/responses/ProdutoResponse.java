package br.com.zupacademy.neto.mercadolivre.responses;

import br.com.zupacademy.neto.mercadolivre.compartilhado.ManipulacaoDeNotas;
import br.com.zupacademy.neto.mercadolivre.dominios.Produto;

import java.math.BigDecimal;
import java.util.Set;

public class ProdutoResponse {

    /*
        - Links para imagens
        - Nome do produto
        - Preço do produto
        - Características do produto
        - Descrição do produto
        - Média de notas do produto
        - Número total de notas do produto
        - Opiniões sobre o produto
        - Perguntas para aquele produto

    */
    private final Long id;
    private final String nome;
    private final String Descricao;
    private final BigDecimal valor;
    private final Double mediaNotas;
    private final Integer totalNotas;
    private final Set<String> linksImagens;
    private final Set<DetalheProdutoOpiniao> opinioesProduto;
    private final Set<DetalheProdutoPergunta> perguntas;
    private final Set<DetalheProdutoCaracteristica> caracteristicas;


    public ProdutoResponse(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.Descricao = produto.getDescricao();
        this.valor = produto.getValor();
        this.opinioesProduto = produto.mapOpinioes(DetalheProdutoOpiniao::new);
        this.caracteristicas = produto.mapCaracteristicas(DetalheProdutoCaracteristica::new);
        this.linksImagens = produto.mapImagens(imagem -> imagem.getLink());
        this.perguntas = produto.mapPerguntas(DetalheProdutoPergunta::new);
        this.totalNotas = produto.getOpinioes().size();
        this.mediaNotas = ManipulacaoDeNotas.calcularMedia(produto.getOpinioes());
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return Descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Double getMediaNotas() {
        return mediaNotas;
    }

    public Integer getTotalNotas() {
        return totalNotas;
    }

    public Set<String> getLinksImagens() {
        return linksImagens;
    }

    public Set<DetalheProdutoCaracteristica> getCaracteristicas() {
        return caracteristicas;
    }


    public Set<DetalheProdutoOpiniao> getOpinioesProduto() {
        return opinioesProduto;
    }

    public Set<DetalheProdutoPergunta> getPerguntas() {
        return perguntas;
    }
}
