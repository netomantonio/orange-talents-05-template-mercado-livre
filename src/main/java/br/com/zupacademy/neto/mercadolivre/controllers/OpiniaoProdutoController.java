package br.com.zupacademy.neto.mercadolivre.controllers;

import br.com.zupacademy.neto.mercadolivre.compartilhado.VerificaPropriedade;
import br.com.zupacademy.neto.mercadolivre.config.erros.ResourceNotFoundException;
import br.com.zupacademy.neto.mercadolivre.dominios.OpiniaoProduto;
import br.com.zupacademy.neto.mercadolivre.dominios.Produto;
import br.com.zupacademy.neto.mercadolivre.dominios.Usuario;
import br.com.zupacademy.neto.mercadolivre.repositories.ProdutoRepository;
import br.com.zupacademy.neto.mercadolivre.requests.OpiniaoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping
public class OpiniaoProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @PostMapping("/api/produtos/{id}/opinioes")
    public void opinar(@PathVariable("id") Long id, @RequestBody @Valid OpiniaoRequest opiniaoRequest, @AuthenticationPrincipal Usuario user) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        OpiniaoProduto novaOpiniao = opiniaoRequest.toModel(produto, user);
        produto.addOpiniao(novaOpiniao);
        produtoRepository.save(produto);

    }
}
