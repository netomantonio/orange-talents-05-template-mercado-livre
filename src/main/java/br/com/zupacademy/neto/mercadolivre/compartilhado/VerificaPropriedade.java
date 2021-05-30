package br.com.zupacademy.neto.mercadolivre.compartilhado;

import br.com.zupacademy.neto.mercadolivre.config.erros.ForbiddenException;
import br.com.zupacademy.neto.mercadolivre.dominios.Produto;
import br.com.zupacademy.neto.mercadolivre.dominios.Usuario;

public class VerificaPropriedade {

    public static void produto(Usuario user, Produto produto) {
        if (user.getId() != produto.getAnunciante().getId()) {
            throw new ForbiddenException("");
        }
    }
}
