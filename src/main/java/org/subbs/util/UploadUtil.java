package org.subbs.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @ClassName UploadImg
 * @Date 2019/12/23 21:03
 */
public class UploadUtil {
    //读取文件到byte[]
    public static byte[] getFileBytes(String file) {
        try {
            String dir = Thread.currentThread().getContextClassLoader().getResource("").getPath();
            File f = new File(dir+file);;
            int length = (int) f.length();
            byte[] data = new byte[length];
            new FileInputStream(f).read(data);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
