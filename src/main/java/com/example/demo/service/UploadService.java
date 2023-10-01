package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Service
public class UploadService {


    @Async
    public void saveMP3(String filename, MultipartFile multipartFile)  {
        Path upDir = Paths.get("songs");

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filepath = upDir.resolve(filename);
            Files.copy(inputStream, filepath, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    @Async
    public void saveImageUrl(String filename, MultipartFile multipartFile)  {
        Path upDir = Paths.get("images");

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filepath = upDir.resolve(filename);
            Files.copy(inputStream, filepath, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
           e.printStackTrace();
        }


    }
}
