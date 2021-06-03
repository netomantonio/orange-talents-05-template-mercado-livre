package br.com.zupacademy.neto.mercadolivre.compartilhado;

import br.com.zupacademy.neto.mercadolivre.dominios.Compra;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class Ranking implements EventoCompraSucesso {
    @Override
    public void executa(Compra compra) {
        Assert.isTrue(compra.processadaComSucesso(), "Está compra não pode ser incluída no ranking, não está concluída com sucesso " + compra);
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra", compra.getId(), "idVendedor", compra.getVendedor().getId());
        try {
            restTemplate.postForEntity("http://localhost:8080/api/ranking", request, String.class);
        } catch (RestClientException e) {
            throw e;
        }
    }
}
