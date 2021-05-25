package br.com.zupacademy.neto.mercadolivre.dominios;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
    private String password;
    private LocalDateTime criandoEm;

    @Deprecated
    public Usuario(){}

    public Usuario(@Email(message = "deve ter formato de email válido") @NotBlank(message = "Email é obrigatório") String email, @NotBlank(message = "Senha é obrigatória") @Size(min = 6, message = "minimo de 6 caractéres") String password, LocalDateTime criandoEm){
        this.criandoEm = criandoEm;
        this.email = email;
        this.password = password;
    }
}
