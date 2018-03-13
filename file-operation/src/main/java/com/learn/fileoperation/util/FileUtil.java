package com.learn.core.util;


import java.io.File;
import java.io.FileOutputStream;

/**
 * @author Created by 一伞烟雨 on 2017/8/9.
 */
public class FileUtil {
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            System.out.println("tar"+targetFile);
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        System.out.println("保存路径"+filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
