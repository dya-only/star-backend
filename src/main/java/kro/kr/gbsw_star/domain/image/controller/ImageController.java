package kro.kr.gbsw_star.domain.image.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequestMapping("api/image")
@RestController
public class ImageController {

    @GetMapping("user/{fileName}")
    public ResponseEntity<Resource> getUserImageByName(@PathVariable("fileName") String filename) throws IOException {
        String STORE_PATH = "./images/user";

        String str = URLEncoder.encode(filename, "UTF-8");

        Path path = Paths.get(STORE_PATH + "/" + filename);
        Resource resource = new InputStreamResource(Files.newInputStream(path));
        System.out.println("resource : " + resource.getFilename());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/octect-stream")
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + str + ";")
                .body(resource);
    }
}
