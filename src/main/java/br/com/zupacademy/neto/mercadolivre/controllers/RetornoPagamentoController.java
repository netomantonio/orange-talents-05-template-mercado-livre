package br.com.zupacademy.neto.mercadolivre.controllers;

import br.com.zupacademy.neto.mercadolivre.compartilhado.EventosNovaCompra;
import br.com.zupacademy.neto.mercadolivre.compartilhado.RetornoPagamento;
import br.com.zupacademy.neto.mercadolivre.config.erros.ResourceNotFoundException;
import br.com.zupacademy.neto.mercadolivre.dominios.Compra;
import br.com.zupacademy.neto.mercadolivre.repositories.CompraRepository;
import br.com.zupacademy.neto.mercadolivre.requests.TransacaoPagseguroRequest;
import br.com.zupacademy.neto.mercadolivre.requests.TransacaoPaypalRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping
public class RetornoPagamentoController {

    @Autowired
    CompraRepository compraRepository;
    @Autowired
    private EventosNovaCompra eventosNovaCompra;

    @PostMapping("/api/retorno-pagseguro/{id}")
    public void pagseguro(@PathVariable("id") Long id, @Valid TransacaoPagseguroRequest transacaoPagseguroRequest) {
        processaPagamento(id, transacaoPagseguroRequest);
    }

    @PostMapping("/api/retorno-paypal/{id}")
    public void paypal(@PathVariable("id") Long id, @Valid TransacaoPaypalRequest transacaoPaypalRequest) {
        processaPagamento(id, transacaoPaypalRequest);
    }

    private void processaPagamento(Long id, RetornoPagamento tranacaoRequest) {
        Compra compra = compraRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("produto não existente"));
        compra.addTransacao(tranacaoRequest);
        compraRepository.saveAndFlush(compra);
        eventosNovaCompra.executa(compra);
    }
}
