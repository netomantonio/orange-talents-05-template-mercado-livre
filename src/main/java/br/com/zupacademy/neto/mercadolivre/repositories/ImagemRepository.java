package br.com.zupacademy.neto.mercadolivre.repositories;

import br.com.zupacademy.neto.mercadolivre.dominios.ImagemProduto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagemRepository extends JpaRepository<ImagemProduto, Long> {

}
