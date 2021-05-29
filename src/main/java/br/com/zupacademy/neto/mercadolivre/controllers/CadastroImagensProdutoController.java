package br.com.zupacademy.neto.mercadolivre.controllers;

import br.com.zupacademy.neto.mercadolivre.compartilhado.ImagensUploader;
import br.com.zupacademy.neto.mercadolivre.config.erros.ForbiddenException;
import br.com.zupacademy.neto.mercadolivre.config.erros.ResourceNotFoundException;
import br.com.zupacademy.neto.mercadolivre.dominios.ImagemProduto;
import br.com.zupacademy.neto.mercadolivre.dominios.Produto;
import br.com.zupacademy.neto.mercadolivre.dominios.Usuario;
import br.com.zupacademy.neto.mercadolivre.repositories.ImagemRepository;
import br.com.zupacademy.neto.mercadolivre.repositories.ProdutoRepository;
import br.com.zupacademy.neto.mercadolivre.requests.ImagensRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
public class CadastroImagensProdutoController {

    @Autowired
    ImagensUploader imagensUploader;

    @Autowired
    ImagemRepository imagemRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @PostMapping("/api/produtos/{id}/fotos")
    public void adicionarFotos(@PathVariable("id") Long id, @Valid ImagensRequest imagensRequest, @AuthenticationPrincipal Usuario user) {

        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        if (user.getId() != produto.getAnunciante().getId()) {
            throw new ForbiddenException("");
        }

        List<ImagemProduto> novasImagens = imagensRequest.toModel(imagensUploader, produto);

        imagemRepository.saveAll(novasImagens);
    }
}
