package br.com.zupacademy.neto.mercadolivre.controllers;

import br.com.zupacademy.neto.mercadolivre.dominios.Categoria;
import br.com.zupacademy.neto.mercadolivre.repositories.CategoriaRepository;
import br.com.zupacademy.neto.mercadolivre.requests.CategoriaCadastroRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping
public class CategoriaCadastroController {

    @Autowired
    CategoriaRepository categoriaRepository;

    @PostMapping("/api/categorias")
    public void cadastrar(@RequestBody @Valid CategoriaCadastroRequest categoriaCadastroRequest){
        Categoria novaCategoria = categoriaCadastroRequest.toModel(categoriaRepository);
        categoriaRepository.save(novaCategoria);
    }
}
