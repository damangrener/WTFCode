import org.junit.jupiter.api.Test;
import util.CommonUtils;

import java.util.Map;

/**
 * @Author WTF
 * @Date 2022/6/28 22:35
 */
public class CommonUtilsTest {


    @Test
    public void readProperties(){

        Map<String, Object> map = CommonUtils.readProperties("elasticsearch.properties");

        map.forEach((k,v)->{
            System.out.println(k+" : "+v);
        });

    }

}
