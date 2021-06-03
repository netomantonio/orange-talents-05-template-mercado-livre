package br.com.zupacademy.neto.mercadolivre.compartilhado;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class OutrosSistemas {

    @PostMapping("/api/notas-ficais")
    public void criaNota(@Valid @RequestBody NovaCompraNFRequest novaCompraNFRequest) throws InterruptedException {
        System.out.println("Criando nota para " + novaCompraNFRequest.toString());
        Thread.sleep(150);
    }

    @PostMapping("/api/ranking")
    public void ranking(@Valid @RequestBody NovaCompraRankingRequest novaCompraRankingRequest) throws InterruptedException {
        System.out.println("atualizando ranking com " + novaCompraRankingRequest.toString());
        Thread.sleep(150);
    }
}
