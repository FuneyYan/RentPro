package com.lz.service;

import com.lz.dto.DeviceRentDto;
import com.lz.pojo.Device;
import com.lz.pojo.DeviceRent;
import com.lz.pojo.DeviceRentDetail;
import com.lz.pojo.DeviceRentDoc;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

public interface DeviceService {
    List<Device> findAll();
    void addNewDevice(Device device);

    List<Device> findDeviceByParam(Map<String, Object> searchParam);

    Long count();

    Long filterCount(Map<String, Object> searchParam);

    Device findDeviceById(Integer id);

    String saveRent(DeviceRentDto deviceRentDto);

    DeviceRent findDeviceRentBySerialNumber(String serialNumber);

    List<DeviceRentDetail> findDeviceDetailListByRentId(Integer id);

    List<DeviceRentDoc> findDeviceDocListByRentId(Integer id);

    InputStream downloadFile(Integer docId) throws IOException;

    DeviceRentDoc findDeviceDocById(Integer id);

    DeviceRent findDeviceRentById(Integer rentId);

    void downloadZipFile(DeviceRent rent, ZipOutputStream zipOutputStream) throws IOException;

    List<DeviceRent> findDeviceRentByParam(Map<String, Object> map);

    Long deviceRentCount();

    void rentChangeState(Integer id);
}
