package br.com.zupacademy.neto.mercadolivre.controllers;

import br.com.zupacademy.neto.mercadolivre.dominios.Usuario;
import br.com.zupacademy.neto.mercadolivre.repositories.UsuarioRepository;
import br.com.zupacademy.neto.mercadolivre.requests.UsuarioCadastroRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping
public class UsuariosCadastroController {

    private final UsuarioRepository usuarioRepository;

    public UsuariosCadastroController(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/api/usuarios")
    public void cadastrar(@RequestBody @Valid UsuarioCadastroRequest usuarioCadastroRequest){
        Usuario novoUsuario = usuarioCadastroRequest.toModel();
        usuarioRepository.save(novoUsuario);
    }
}
