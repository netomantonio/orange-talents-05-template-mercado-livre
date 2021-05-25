package br.com.zupacademy.neto.mercadolivre.requests;

import br.com.zupacademy.neto.mercadolivre.config.validacoes.SenhaValidacoes;
import br.com.zupacademy.neto.mercadolivre.config.validacoes.Unique;
import br.com.zupacademy.neto.mercadolivre.dominios.Usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioCadastroRequest {

    /*

      O instante não pode ser nulo e não pode ser no futuro
      O login não pode ser em branco ou nula
      O login precisa ter o formato do email
      A senha não pode ser branca ou nula
      A senha precisa ter no mínimo 6 caracteres
      A senha deve ser guardada usando algum algoritmo de hash da sua escolha.

     */
    @Email(message = "deve ter formato de email válido")
    @NotBlank(message = "Email é obrigatório")
    @Unique(domainClass = Usuario.class, fieldName = "email", message = "Email inválido")
    private String email;
    @NotBlank(message = "Senha é obrigatória")

    @Size(min = 6, message = "minimo de 6 caractéres")
    private String password;

    public UsuarioCadastroRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public Usuario toModel() {
        return new Usuario(this.email, new SenhaValidacoes(this.password));
    }
}
