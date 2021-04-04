package life.majiang.community.utils;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Properties;

public class FileUploadUtils {

    /**
     * 上传文件
     * @param file
     * @param flag 是否使用原文件名
     * @return 上传后的文件名
     * @throws IOException
     */
    public static String uploadFile(MultipartFile file, boolean flag) throws IOException {
        String name = file.getOriginalFilename();  //文件名
        if (!StringUtils.isEmpty(name)) {
            InputStream is = file.getInputStream();
            /*Properties prop = new Properties();
            InputStream in = FileUploadUtils.class.getClassLoader().getResourceAsStream("db.properties");
            prop.load(in);
            String path = prop.get("saveFilePath").toString();*/
            String path = FileUploadUtils.class.getClassLoader().getResource("").getPath();
            path += "static/images/upload";
            File file2 = new File(path);
            if (!file2.exists()) {
                file2.mkdirs();
            }
            String filename = System.currentTimeMillis() + name.substring(name.lastIndexOf("."));
            if(flag){
                filename = file.getOriginalFilename();
            }
            OutputStream os = new FileOutputStream(file2.getAbsolutePath() + "/" + filename);
            byte[] buf = new byte[1024];
            int len;
            while ((len = is.read(buf)) != -1) {
                os.write(buf, 0, len);
                os.flush();
            }
            os.close();
            return "/images/upload/"+filename;
        } else {
            return "";
        }
    }
}
