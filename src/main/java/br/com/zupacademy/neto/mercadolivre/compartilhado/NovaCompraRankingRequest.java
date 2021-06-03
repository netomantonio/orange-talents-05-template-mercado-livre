package br.com.zupacademy.neto.mercadolivre.compartilhado;

import javax.validation.constraints.NotNull;

public class NovaCompraRankingRequest {
    @NotNull
    private Long idCompra;
    @NotNull
    private Long idVendedor;

    public NovaCompraRankingRequest(Long idCompra, Long idVendedor) {
        this.idCompra = idCompra;
        this.idVendedor = idVendedor;
    }

    @Override
    public String toString() {
        return "NovaCompraRankingRequest{" +
                "idCompra=" + idCompra +
                ", idVendedor=" + idVendedor +
                '}';
    }
}
