package br.com.zupacademy.neto.mercadolivre.requests;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank(message = "Email e/ou senha não podem estar vazios")
    private String email;
    @NotBlank(message = "Email e/ou senha não podem estar vazios")
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }

}
