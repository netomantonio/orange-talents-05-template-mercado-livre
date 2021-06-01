package br.com.zupacademy.neto.mercadolivre.controllers;

import br.com.zupacademy.neto.mercadolivre.compartilhado.CentralEmails;
import br.com.zupacademy.neto.mercadolivre.config.erros.ResourceNotFoundException;
import br.com.zupacademy.neto.mercadolivre.dominios.Compra;
import br.com.zupacademy.neto.mercadolivre.dominios.Produto;
import br.com.zupacademy.neto.mercadolivre.dominios.Usuario;
import br.com.zupacademy.neto.mercadolivre.repositories.CompraRepository;
import br.com.zupacademy.neto.mercadolivre.repositories.ProdutoRepository;
import br.com.zupacademy.neto.mercadolivre.requests.CompraRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping
public class ProcessamentoCompraController {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    CompraRepository compraRepository;

    @Autowired
    CentralEmails centralEmails;

    @PostMapping("/api/compras")
    public ResponseEntity<?> processarCompra(@Valid @RequestBody CompraRequest compraRequest, @AuthenticationPrincipal Usuario usuarioLogado) {

        Produto produtoCompra = produtoRepository.findById(compraRequest.getIdProduto()).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        if (!produtoCompra.abaterEstoque(compraRequest.getQuantidade()))
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);

        Compra novaCompra = compraRequest.toModel(produtoCompra, usuarioLogado);

        compraRepository.save(novaCompra);

        centralEmails.enviaEmailNovaCompra(novaCompra);
        String urlRetornoPosCompra = compraRequest.getMetodoPagamento().url(novaCompra.getId());
        return new ResponseEntity<>(urlRetornoPosCompra, HttpStatus.FOUND);
    }
}
