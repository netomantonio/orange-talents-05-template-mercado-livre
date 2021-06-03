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
        String assunto = "[Nova compra, produto: ]" + novaCompra.getNomeProduto();
        String corpo = "Detalhes da Compra: \n" +
                "Método de pagamento: " + novaCompra.getMetodoPagamento() +
                "Produto: " + novaCompra.getNomeProduto() +
                "Quantidade: " + novaCompra.getQuantidade() +
                "Valor: " + novaCompra.getValor() +
                "Status: " + novaCompra.getStatus();
        enviaEmail.dispararEmail(email, assunto, corpo);
    }

    public void enviaEmailCompraSucesso(Compra compraSucesso) {
        String email = compraSucesso.getEmailComprador();
        String assunto = "[Compra efetivada com sucesso, produto: ]" + compraSucesso.getNomeProduto();
        String corpo = "Detalhes da Compra: \n" +
                "Método de pagamento: " + compraSucesso.getMetodoPagamento() +
                "Produto: " + compraSucesso.getNomeProduto() +
                "Quantidade: " + compraSucesso.getQuantidade() +
                "Valor: " + compraSucesso.getValor() +
                "Status: " + compraSucesso.getStatus();
        enviaEmail.dispararEmail(email, assunto, corpo);
    }

    public void enviaEmailCompraErro(Compra compraErro) {
        String email = compraErro.getEmailComprador();
        String assunto = "[Transação de pagamento falhou, produto: ]" + compraErro.getNomeProduto();
        String corpo = "Detalhes da Compra: \n" +
                "Método de pagamento: " + compraErro.getMetodoPagamento() +
                "Produto: " + compraErro.getNomeProduto() +
                "Quantidade: " + compraErro.getQuantidade() +
                "Valor: " + compraErro.getValor() +
                "Status: " + compraErro.getStatus();
        enviaEmail.dispararEmail(email, assunto, corpo);
    }
}
