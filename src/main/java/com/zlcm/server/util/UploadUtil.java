package com.zlcm.server.util;

import com.zlcm.server.constant.Constant;
import com.zlcm.server.util.id.UUIDTools;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;

public class UploadUtil {

    public static String uploadImg(MultipartFile file,String path,String sqlPath) throws IOException {
        String newFileName = DateUtil.getStringDate() + "_" + UUIDTools.getImgName() + ".jpg";
        // 设定文件保存的目录
        File f = new File(path,newFileName);
//        String serverPath = path + newFileName;
        if (!f.exists()) {
//            f.mkdirs();
//            if (!file.isEmpty()) {
//                FileOutputStream fos = new FileOutputStream(serverPath);
//                InputStream in = file.getInputStream();
//                int b = 0;
//                while ((b = in.read()) != -1) {
//                    fos.write(b);
//                }
//                fos.close();
//                in.close();
//            }
            if (!file.isEmpty()) {
                file.transferTo(f);
            }
        }

        return Constant.ADDRESS +sqlPath+newFileName;
    }
}
