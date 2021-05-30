package br.com.zupacademy.neto.mercadolivre.controllers;

import br.com.zupacademy.neto.mercadolivre.dominios.Produto;
import br.com.zupacademy.neto.mercadolivre.dominios.Usuario;
import br.com.zupacademy.neto.mercadolivre.repositories.CategoriaRepository;
import br.com.zupacademy.neto.mercadolivre.repositories.ProdutoRepository;
import br.com.zupacademy.neto.mercadolivre.requests.CadastroProdutoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping
public class ProdutoCadastroController {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @PostMapping("/api/produtos")
    public void cadastrar(@RequestBody @Valid CadastroProdutoRequest cadastroProdutoRequest, @AuthenticationPrincipal Usuario usuario) {
        Produto novoProduto = cadastroProdutoRequest.toModel(usuario, categoriaRepository);
        produtoRepository.save(novoProduto);
    }

}
