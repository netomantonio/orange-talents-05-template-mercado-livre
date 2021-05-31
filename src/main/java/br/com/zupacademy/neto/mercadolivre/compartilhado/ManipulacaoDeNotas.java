package br.com.zupacademy.neto.mercadolivre.compartilhado;

import br.com.zupacademy.neto.mercadolivre.dominios.OpiniaoProduto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManipulacaoDeNotas {
    public static Double calcularMedia(List<OpiniaoProduto> opinioes) {
        if (opinioes.size() == 0) return 0.0;
        Double media = 0.0;
        Double total = 0.0;

        for (OpiniaoProduto opiniao : opinioes) total += opiniao.getNota();
        media = total / opinioes.size();
        return (media);
    }
}
