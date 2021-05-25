package br.com.zupacademy.neto.mercadolivre.config.validacoes;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.assertTrue;

public class SenhasValidacoes {


    public static String encripta(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        return encoder.encode(password);
    }

    public boolean matche(String passwordRequest, String passwordBD){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        String result = encoder.encode(passwordRequest);
        assertTrue(encoder.matches(passwordBD, result));
        return true;
    }
}
