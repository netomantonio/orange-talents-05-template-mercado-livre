package br.com.zupacademy.neto.mercadolivre.compartilhado;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Component
public class ImagensUploader implements Uploader {

    public ImagensUploader(){}

    @Override
    public List<String> upload(List<MultipartFile> imagens, Long idProduto) {

        List<String> links = new ArrayList<>();

        for (MultipartFile imagem: imagens) {
            links.add("/api/produtos/"+idProduto+"/fotos/"+imagem.hashCode());
        }

        return links;
    }

}
