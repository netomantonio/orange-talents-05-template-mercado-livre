package br.com.zupacademy.neto.mercadolivre.repositories;

import br.com.zupacademy.neto.mercadolivre.dominios.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {
}
