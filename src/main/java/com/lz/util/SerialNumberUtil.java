package com.lz.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;

public class SerialNumberUtil {

    public static String getSerialNumber(){
        DateTime dateTime=new DateTime();
        String result=dateTime.toString("YYYYMMDDHHmmss");
        result+= RandomStringUtils.randomNumeric(4);//生成4位随机数
        return result;
    }

}
