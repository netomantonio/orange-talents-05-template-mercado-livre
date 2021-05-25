package br.com.zupacademy.neto.mercadolivre.repositories;

import br.com.zupacademy.neto.mercadolivre.dominios.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String username);
}
