package br.com.zupacademy.neto.mercadolivre.repositories;

import br.com.zupacademy.neto.mercadolivre.dominios.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompraRepository extends JpaRepository<Compra, UUID> {
}
