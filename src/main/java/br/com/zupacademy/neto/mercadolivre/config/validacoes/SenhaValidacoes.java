package br.com.zupacademy.neto.mercadolivre.config.validacoes;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class SenhaValidacoes {

    private String password;

    public SenhaValidacoes(@Valid @NotBlank @Length(min=6) String password){
        Assert.hasLength(password, "Senha não pode estar em branco");
        Assert.isTrue(password.length() >= 6, "Senha deve ter mínimo de 6 caracteres");

        this.password = password;
    }

    public String hash(){
        return new BCryptPasswordEncoder().encode(this.password);
    }
}
