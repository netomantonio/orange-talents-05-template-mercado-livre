package br.com.zupacademy.neto.mercadolivre.controllers;

import br.com.zupacademy.neto.mercadolivre.compartilhado.CentralEmails;
import br.com.zupacademy.neto.mercadolivre.config.erros.ResourceNotFoundException;
import br.com.zupacademy.neto.mercadolivre.dominios.Pergunta;
import br.com.zupacademy.neto.mercadolivre.dominios.Produto;
import br.com.zupacademy.neto.mercadolivre.dominios.Usuario;
import br.com.zupacademy.neto.mercadolivre.repositories.PerguntaRepository;
import br.com.zupacademy.neto.mercadolivre.repositories.ProdutoRepository;
import br.com.zupacademy.neto.mercadolivre.requests.PerguntaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping
public class PerguntaController {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    PerguntaRepository perguntaRepository;

    @Autowired
    CentralEmails centralEmails;

    @PostMapping("/api/produtos/{id}/perguntas")
    public void perguntar(@PathVariable("id") Long id,
                          @Valid @RequestBody PerguntaRequest perguntaRequest,
                          @AuthenticationPrincipal Usuario user) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        Pergunta novaPergunta = perguntaRequest.toModel(produto, user);
        perguntaRepository.save(novaPergunta);
        centralEmails.enviaEmailNovaPergunta(novaPergunta);
    }
}
