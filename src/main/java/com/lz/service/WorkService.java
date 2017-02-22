package com.lz.service;

import com.lz.dto.WorkRentDto;
import com.lz.pojo.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipOutputStream;

public interface WorkService {

    List<WorkType> findAll();

    WorkType findWorkById(Integer id);

    String saveRent(WorkRentDto workRentDto);

    WorkRent findDeviceRentBySerialNumber(String serialNumber);

    List<WorkRentDetail> findDeviceRentDetailListByRentId(Integer id);

    List<WorkRentDoc> findDeviceRentDocListByRentId(Integer id);

    InputStream downloadFile(Integer id) throws FileNotFoundException;

    WorkRentDoc findDeviceRentDocById(Integer id);

    WorkRent findDeviceRentById(Integer id);

    void downloadZipFile(WorkRent rent, ZipOutputStream zipOutputStream) throws IOException;
}
