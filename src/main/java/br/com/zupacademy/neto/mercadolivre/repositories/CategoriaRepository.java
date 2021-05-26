package br.com.zupacademy.neto.mercadolivre.repositories;

import br.com.zupacademy.neto.mercadolivre.dominios.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
