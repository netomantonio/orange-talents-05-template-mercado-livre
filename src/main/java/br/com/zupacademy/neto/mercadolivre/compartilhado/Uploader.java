package br.com.zupacademy.neto.mercadolivre.compartilhado;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface Uploader {
    public List<String> upload(List<MultipartFile> listaDeArquivos, Long id);
}
