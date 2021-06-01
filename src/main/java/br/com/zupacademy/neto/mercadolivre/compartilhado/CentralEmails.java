package br.com.zupacademy.neto.mercadolivre.compartilhado;

import br.com.zupacademy.neto.mercadolivre.dominios.Compra;
import br.com.zupacademy.neto.mercadolivre.dominios.Pergunta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CentralEmails {

    @Autowired
    EnviaEmail enviaEmail;

    public void enviaEmailNovaPergunta(Pergunta pergunta) {
        String email = pergunta.getEmailAnunciante();
        String assunto = "Pergunta para o produto: " + pergunta.getNomeProduto();
        String corpo = pergunta.toString();

        enviaEmail.dispararEmail(email, assunto, corpo);
    }

    public void enviaEmailNovaCompra(Compra novaCompra) {
        String email = novaCompra.getEmailComprador();
        String assunto = "[Compra efetivada com sucesso, produto: ]" + novaCompra.getNomeProduto();
        String corpo = "Detalhes da Compra: \n" +
                "Método de pagamento: " + novaCompra.getMetodoPagamento() +
                "Produto: " + novaCompra.getNomeProduto() +
                "Quantidade: " + novaCompra.getQuantidade() +
                "Valor: " + novaCompra.getValor() +
                "Status: " + novaCompra.getStatus();
    }
}
