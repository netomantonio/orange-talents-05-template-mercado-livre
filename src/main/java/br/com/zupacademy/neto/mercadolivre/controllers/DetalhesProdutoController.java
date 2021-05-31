package br.com.zupacademy.neto.mercadolivre.controllers;

import br.com.zupacademy.neto.mercadolivre.config.erros.ResourceNotFoundException;
import br.com.zupacademy.neto.mercadolivre.dominios.Produto;
import br.com.zupacademy.neto.mercadolivre.repositories.ProdutoRepository;
import br.com.zupacademy.neto.mercadolivre.responses.ProdutoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class DetalhesProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping("/api/produtos/{id}")
    public ResponseEntity<?> detalhar(@PathVariable("id") Long id) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        ProdutoResponse produtoDetalhes = new ProdutoResponse(produto);
        return new ResponseEntity<ProdutoResponse>(produtoDetalhes, HttpStatus.OK);

    }
}
