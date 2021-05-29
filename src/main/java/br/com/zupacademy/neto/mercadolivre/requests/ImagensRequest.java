package br.com.zupacademy.neto.mercadolivre.requests;

import br.com.zupacademy.neto.mercadolivre.compartilhado.ImagensUploader;
import br.com.zupacademy.neto.mercadolivre.dominios.ImagemProduto;
import br.com.zupacademy.neto.mercadolivre.dominios.Produto;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class ImagensRequest {

    @Size(min = 1)
    @NotEmpty(message = "é necessário enviar pelo menos 1 imagem")
    private List<MultipartFile> imagens;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ImagensRequest(List<MultipartFile> imagens){
        this.imagens = imagens;
    }

    public List<MultipartFile> getImagens() {
        return imagens;
    }

    public List<ImagemProduto> toModel(ImagensUploader imagensUploader, Produto produto) {

        List<String> linksimagens = imagensUploader.upload(this.imagens, produto.getId());
        List<ImagemProduto> imagens = new ArrayList<>();
        linksimagens.forEach(link -> imagens.add(new ImagemProduto(link, produto)));
        return imagens;
    }
}
