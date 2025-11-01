package lab.crud.api.controller;
 
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
public class ImageController {
 
    @GetMapping("/image")
    public ResponseEntity<byte[]> getImagem() throws IOException {
        // Caminho completo da imagem no teu computador
        Path caminho = Paths.get("C:\\Users\\rm100778\\Downloads\\calopsita.png");
 
        // Lê todos os bytes do arquivo
        byte[] imagemBytes = Files.readAllBytes(caminho);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG); // muda pra IMAGE_JPEG se for .jpg
 
        // Retorna a imagem pro navegador
        return new ResponseEntity<>(imagemBytes, headers, HttpStatus.OK);
    }
}
 