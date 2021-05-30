package br.com.zupacademy.neto.mercadolivre.compartilhado;

import org.springframework.stereotype.Component;

@Component
public class EnviaEmail {

    public void dispararEmail(String email, String assunto, String corpo) {
        System.out.println("Email para: " + email + "\n" +
                "Assunto: " + assunto + "\n" +
                "Menssagem: " + corpo);
    }
}
