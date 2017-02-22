package com.lz.mapper;

import com.lz.pojo.Disk;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DiskMapper {
    List<Disk> findFileByFid(Integer fid);

    void save(Disk disk);

    Disk findFileById(Integer id);

    void del(Integer id);

    List<Disk> findAll();

    void batchDel(List<Integer> delDiskIds);
}
