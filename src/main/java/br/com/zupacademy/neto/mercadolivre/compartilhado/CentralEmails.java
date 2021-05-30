package br.com.zupacademy.neto.mercadolivre.compartilhado;

import br.com.zupacademy.neto.mercadolivre.dominios.Pergunta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CentralEmails {

    @Autowired
    EnviaEmail enviaEmail;

    public void enviaEmailNovaPergunta(Pergunta pergunta) {
        String email = pergunta.getProduto().getAnunciante().getUsername();
        String assunto = "Pergunta para o produto: " + pergunta.getProduto().getNome();
        String corpo = pergunta.toString();

        enviaEmail.dispararEmail(email, assunto, corpo);
    }
}
