package it.aulab.theaulabchronicle.services;

import it.aulab.theaulabchronicle.models.Article;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public interface ImageService {
    void saveImageOnDB(String url, Article article);

    CompletableFuture<String> saveImageOnCloud(MultipartFile file) throws Exception;

    void deleteImage(String imagePath) throws IOException;
}