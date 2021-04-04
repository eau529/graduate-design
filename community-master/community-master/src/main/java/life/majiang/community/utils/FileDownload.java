package life.majiang.community.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FileDownload {

    public static void download(String filename, HttpServletResponse res) throws IOException {
        // 发送给客户端的数据
        OutputStream outputStream = res.getOutputStream();
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        // 读取filename
        String path = FileUploadUtils.class.getClassLoader().getResource("").getPath();
        path += "static/images/upload/";
        bis = new BufferedInputStream(new FileInputStream(new File(
                path + filename)));
        int i = bis.read(buff);
        while (i != -1) {
            outputStream.write(buff, 0, buff.length);
            outputStream.flush();
            i = bis.read(buff);
        }
    }
}
