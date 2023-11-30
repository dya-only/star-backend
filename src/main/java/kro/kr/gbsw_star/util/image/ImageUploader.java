package kro.kr.gbsw_star.util.image;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class ImageUploader {
    private static final String STORE_PATH = System.getProperty("user.dir") + "/images";

    public Image upload(MultipartFile multipartFile, String dirName) throws Exception {
        String originalName = multipartFile.getOriginalFilename();
        String storeName = createImageName(originalName);
        String storePath = STORE_PATH + "/" + dirName + "/" + storeName;
        Path path = Paths.get(storePath).toAbsolutePath();
        File file = path.toFile();

        file.getParentFile().mkdirs();
        multipartFile.transferTo(file);
        return new Image(originalName, storeName, storePath);
    }

    private String createImageName(String fileName) {
        int index = fileName.lastIndexOf(".");
        String extension = fileName.substring(index + 1);
        String uuid = UUID.randomUUID().toString();

        return uuid + "." + extension;
    }
}
