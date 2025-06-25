package com.app.service.impl;

import com.app.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImages(String path, MultipartFile file) throws IOException {
        // Original file name
        String filename = file.getOriginalFilename();
        // random name generated file to avoid same name collusion
        String randomID = UUID.randomUUID().toString();

        String fileName1 = randomID.concat(filename.substring(filename.lastIndexOf(".")));
        // fullpath
        String filePath = path + File.separator + fileName1;
        // create folder if not created
        File f = new File(path);
        if(!f.exists()){
            f.mkdir();
        }
        // file copy
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return filename;
    }
}