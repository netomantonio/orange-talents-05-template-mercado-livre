package br.com.zupacademy.neto.mercadolivre.dominios;

import br.com.zupacademy.neto.mercadolivre.config.validacoes.SenhaValidacoes;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(unique = true)
    private String email;
    @NotBlank
    @Length(min = 6)
    private String password;
    private LocalDateTime criandoEm = LocalDateTime.now();

    @Deprecated
    public Usuario(){}

    public Usuario(@Email(message = "deve ter formato de email válido") @NotBlank(message = "Email é obrigatório") String email,
                   @Valid @NotBlank SenhaValidacoes senhaLimpa){

        Assert.isTrue(StringUtils.hasLength(email),"email não pode estar em branco");
        Assert.notNull(senhaLimpa,"email não pode estar em branco");


        this.email = email;
        this.password = senhaLimpa.hash();
    }
}
