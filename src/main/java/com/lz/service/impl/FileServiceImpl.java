package com.lz.service.impl;

import com.lz.service.FileService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    private Logger logger= LoggerFactory.getLogger(FileServiceImpl.class);

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public String uploadFile(String originalFilename, String contentType, InputStream inputStream) {
        String newFileName= UUID.randomUUID().toString()
                +originalFilename.substring(originalFilename.lastIndexOf('.'));
        File file=new File(new File(uploadPath),newFileName);

        try {
            OutputStream outputStream=new FileOutputStream(file);
            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();
            outputStream.close();
            inputStream.close();
            return newFileName;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
