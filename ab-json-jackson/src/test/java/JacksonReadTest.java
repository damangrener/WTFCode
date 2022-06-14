import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/*
 *@Description TODO
 *@Author WTF
 *@Date 2022/6/14 15:44
 */
public class JacksonReadTest {

    MyJacksonReadTest jacksonTest = new MyJacksonReadTest();

    @Test
    public void json2Car() throws JsonProcessingException {
        jacksonTest.jsonStr2Car();
    }

    @Test
    public void jsonStream2Car() throws IOException {
        jacksonTest.jsonStream2Car();
    }

    @Test
    public void jsonFile2Car() throws IOException {
        jacksonTest.jsonFile2Car();
    }

    @Test
    public void jsonInputStream2Car() throws IOException {
        jacksonTest.jsonFile2Car();
    }

    @Test
    public void jsonArray2Cars() throws IOException {
        jacksonTest.jsonArray2Cars();
    }

    @Test
    public void jsonList2Cars() throws IOException {
        jacksonTest.jsonList2Cars();
    }

    @Test
    public void jsonStr2Map() throws IOException {
        jacksonTest.jsonStr2Map();
    }

}
