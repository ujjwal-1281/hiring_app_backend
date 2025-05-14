package com.project.utility;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@Slf4j
public class FileStorage {

    private static final String UPLOAD_DIRECTORY="uploads/";


    public String storeFiles(MultipartFile files){
        try{
            File dir = new File(UPLOAD_DIRECTORY);

            log.info("Checking directory: " + dir.getAbsolutePath());

            if(!dir.exists()){

                log.info("Creating directory: " + dir.getAbsolutePath());

                dir.mkdirs();
            }

            String originalFilename = files.getOriginalFilename();

            if (originalFilename == null) {

                throw new IllegalArgumentException("File name cannot be null");
            }
           String fileName = System.currentTimeMillis() + "_"+ files.getOriginalFilename();

            Path filePath = Paths.get(UPLOAD_DIRECTORY + fileName);

            log.info("Saving file to: " + filePath.toAbsolutePath());

            Files.write(filePath, files.getBytes());

            log.info("File saved successfully: " + filePath.toString());

            return filePath.toString();

        }
        catch(Exception e){
            throw new RuntimeException("Failed to store file",e);
        }

    }

}
