package br.com.zupacademy.neto.mercadolivre.compartilhado;

public enum MetodoPagamento implements RedirectGateway {


    PAYPAL {
        @Override
        public String url(Long idGeradoDaCompra) {
            return ("paypal.com?buyerId=" + idGeradoDaCompra + "&redirectUrl=localhost:8080/retorno-paypal/" + idGeradoDaCompra);
        }
    },
    PAGSEGURO {
        @Override
        public String url(Long idGeradoDaCompra) {
            return ("pagseguro.com?returnId=" + idGeradoDaCompra + "&redirectUrl=localhost:8080/retorno-pagseguro/" + idGeradoDaCompra);
        }
    };

}
