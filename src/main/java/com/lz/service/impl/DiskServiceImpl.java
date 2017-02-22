package com.lz.service.impl;

import com.google.common.collect.Lists;
import com.lz.mapper.DiskMapper;
import com.lz.pojo.Disk;
import com.lz.service.DiskService;
import com.lz.util.ShiroUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.UUID;

@Service
public class DiskServiceImpl implements DiskService {
    @Autowired
    private DiskMapper diskMapper;
    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public List<Disk> findFileByFid(Integer fid) {
        return diskMapper.findFileByFid(fid);
    }

    @Override
    public void save(Disk disk) {
        disk.setCreateuser(ShiroUtil.getCurrentUserName());
        disk.setType(Disk.DIRECTORY_TYPE);
        diskMapper.save(disk);
    }

    @Override
    @Transactional
    public void saveNewFile(Integer fid, MultipartFile file) {
//        先将文件写入磁盘
        String sourceName = file.getOriginalFilename();
        String newName = UUID.randomUUID().toString();
        if (sourceName.lastIndexOf('.') != -1) {
            newName += sourceName.substring(sourceName.lastIndexOf('.'));
        }
        try {
            File saveFile = new File(uploadPath, newName);
            FileOutputStream outputStream = new FileOutputStream(saveFile);
            InputStream inputStream=file.getInputStream();
            IOUtils.copy(inputStream,outputStream);

            outputStream.flush();
            outputStream.close();
            inputStream.close();
        }catch (IOException e){
            throw new RuntimeException("写入磁盘错误!",e);
        }

//      保存进数据库中的记录
        Disk disk=new Disk();
        disk.setType(Disk.FILE_TYPE);
        disk.setFid(fid);
        disk.setCreateuser(ShiroUtil.getCurrentUserName());
        disk.setSize(FileUtils.byteCountToDisplaySize(file.getSize()));
        disk.setSourcename(sourceName);
        disk.setName(newName);

        diskMapper.save(disk);

    }

    @Override
    public InputStream downloadFile(Integer id) throws FileNotFoundException {
        Disk disk=findFileById(id);
        if(disk==null || Disk.DIRECTORY_TYPE.equals(disk.getType())){
            return null;
        }else{
            FileInputStream inputStream=new FileInputStream(new File(uploadPath,disk.getName()));
            return inputStream;
        }
    }

    @Override
    public Disk findFileById(Integer id) {
        return diskMapper.findFileById(id);
    }

    @Override
    @Transactional
    public void del(Integer id) {
        Disk disk=findFileById(id);
        if(Disk.FILE_TYPE.equals(disk.getType())){//是文件,直接删除
//            删除本地文件
            File file=new File(uploadPath,disk.getName());
            file.delete();
//            删除数据库记录
            diskMapper.del(id);
        }else{//递归删除文件夹的资源
            List<Disk> diskList=diskMapper.findAll();
            List<Integer> delDiskIds= Lists.newArrayList();
            findDelIds(diskList,delDiskIds,id);
            delDiskIds.add(id);

            diskMapper.batchDel(delDiskIds);
        }
    }

    private void findDelIds(List<Disk> diskList, List<Integer> delDiskIds, Integer id) {
        for(Disk disk:diskList){
            if(disk.getFid().equals(id)){
                delDiskIds.add(disk.getId());
                if(disk.getType().equals(Disk.DIRECTORY_TYPE)){
                    findDelIds(diskList,delDiskIds,disk.getId());
                }else{
                    File file=new File(uploadPath,disk.getName());
                    file.delete();
                }
            }
        }
    }


}
