package Net;

/**
 * Created by flyex on 2018/10/30.
 */
import java.net.URL;
import java.net.URLConnection;

public class Main {
    public static void main(String[] args) throws Exception {
        int size;
        URL url = new URL("https://dldir1.qq.com/music/clntupate/QQMusicSetup.exe");
        URLConnection conn = url.openConnection();
        size = conn.getContentLength();
        if (size < 0)
            System.out.println("无法获取文件大小。");
        else
            System.out.println("文件大小为：" + size + " bytes");
        conn.getInputStream().close();
    }
}
