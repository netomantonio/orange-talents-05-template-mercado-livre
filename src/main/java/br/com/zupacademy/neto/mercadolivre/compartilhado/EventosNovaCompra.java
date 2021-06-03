package br.com.zupacademy.neto.mercadolivre.compartilhado;

import br.com.zupacademy.neto.mercadolivre.dominios.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class EventosNovaCompra {

    @Autowired
    Set<EventoCompraSucesso> eventosCompraSucesso;

    @Autowired
    CentralEmails centralEmails;


    public void executa(Compra compra) {
        if (compra.processadaComSucesso()) {
            eventosCompraSucesso.forEach(evento -> evento.executa(compra));
            centralEmails.enviaEmailCompraSucesso(compra);
        } else {

        }
    }
}
