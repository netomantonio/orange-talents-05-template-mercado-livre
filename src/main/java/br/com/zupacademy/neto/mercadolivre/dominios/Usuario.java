package br.com.zupacademy.neto.mercadolivre.dominios;

import br.com.zupacademy.neto.mercadolivre.config.validacoes.SenhaValidacoes;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Usuario implements UserDetails {

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

    public Long getId() {
        return this.id;
    }
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Perfil> perfis = new ArrayList<>();

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfis;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
