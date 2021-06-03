package br.com.zupacademy.neto.mercadolivre.compartilhado;

import br.com.zupacademy.neto.mercadolivre.dominios.Compra;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class NotaFiscal implements EventoCompraSucesso {

    @Override
    public void executa(Compra compra) {
        Assert.isTrue(compra.processadaComSucesso(), "Essa compra não foi concluída com sucesso, portanto não pode ser gerada nota fiscal " + compra);
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra", compra.getId(), "idComprador", compra.getComprador().getId());
        try {
            restTemplate.postForEntity("http://localhost:8080/api/notas-fiscais", request, String.class);
        } catch (RestClientException e) {
            throw e;
        }
    }
}
