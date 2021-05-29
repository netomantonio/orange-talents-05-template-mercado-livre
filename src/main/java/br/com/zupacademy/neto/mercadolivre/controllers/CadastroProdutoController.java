package br.com.zupacademy.neto.mercadolivre.controllers;

import br.com.zupacademy.neto.mercadolivre.compartilhado.ImagensUploader;
import br.com.zupacademy.neto.mercadolivre.config.erros.ResourceNotFoundException;
import br.com.zupacademy.neto.mercadolivre.dominios.ImagemProduto;
import br.com.zupacademy.neto.mercadolivre.dominios.Produto;
import br.com.zupacademy.neto.mercadolivre.dominios.Usuario;
import br.com.zupacademy.neto.mercadolivre.repositories.CategoriaRepository;
import br.com.zupacademy.neto.mercadolivre.repositories.ImagemRepository;
import br.com.zupacademy.neto.mercadolivre.repositories.ProdutoRepository;
import br.com.zupacademy.neto.mercadolivre.requests.CadastroProdutoRequest;
import br.com.zupacademy.neto.mercadolivre.requests.ImagensRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
public class CadastroProdutoController {

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
