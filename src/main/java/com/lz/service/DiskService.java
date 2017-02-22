package com.lz.service;


import com.lz.pojo.Disk;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public interface DiskService {
    List<Disk> findFileByFid(Integer path);

    void save(Disk disk);

    void saveNewFile(Integer fid, MultipartFile file);

    InputStream downloadFile(Integer id) throws FileNotFoundException;

    Disk findFileById(Integer id);

    void del(Integer id);
}
