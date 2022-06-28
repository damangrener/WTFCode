package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Author WTF
 * @Date 2022/6/28 22:26
 */
public class CommonUtils {

    /**
     * 手动读取properties文件
     *
     * @param path properties路径
     * @return
     */
    public static Map<String, Object> readProperties(String path) {
        Map<String, Object> map = new HashMap<>();

        try {
            InputStream inputStream =
                    ClassLoader.getSystemResourceAsStream(path);
            if (inputStream == null) {
                inputStream = new FileInputStream(path);
            }
            Properties p = new Properties();
            p.load(inputStream);

            p.forEach((k, v) -> {
                map.put(k.toString(), v);
            });

            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(String.format("read [%s] error", path));
        }
        return map;

    }

}
