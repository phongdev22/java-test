package com.QuanLyChungCu_v2.QuanLyChungCu.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class Utils {
    public static void saveFile(MultipartFile file, String folder, String fileName) throws IOException{
        String uploadPath = "./src/main/resources/static/images";
        try {
            Path uploadDir = Paths.get(uploadPath, folder);

            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            Path filePath = uploadDir.resolve(fileName);

            if (Files.exists(filePath)) {
                Files.delete(filePath);
            }

            Files.copy(file.getInputStream(), filePath);
        } catch (IOException ex) {
            throw new IOException(ex.getMessage());
        }
    }

    public static String generateCode(int length){
        Random rnd = new Random();
        String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
