package br.com.zupacademy.neto.mercadolivre.requests;

import br.com.zupacademy.neto.mercadolivre.config.anotacoes.Unique;
import br.com.zupacademy.neto.mercadolivre.config.erros.ResourceNotFoundException;
import br.com.zupacademy.neto.mercadolivre.dominios.Categoria;
import br.com.zupacademy.neto.mercadolivre.repositories.CategoriaRepository;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class CategoriaCadastroRequest {

    @Unique(domainClass = Categoria.class, fieldName = "nome", message = "Categoria já cadastrada")
    @NotBlank
    private String nome;
    @Nullable
    private Long idCategoriaMae;

    public CategoriaCadastroRequest(String nome, @Nullable Long idCategoriaMae) {
        this.nome = nome;
        this.idCategoriaMae = idCategoriaMae;
    }

    public Categoria toModel(CategoriaRepository repo) {
        Categoria nCategoria = new Categoria(this.nome);
        if (this.idCategoriaMae == null) return nCategoria;
        Categoria categoriaMae = repo.findById(this.idCategoriaMae).orElseThrow(() -> new ResourceNotFoundException("categoria mãe não existe"));

        nCategoria.setCategoriaMae(categoriaMae);

        return nCategoria;
    }
}
