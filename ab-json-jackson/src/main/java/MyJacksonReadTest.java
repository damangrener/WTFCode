/*
 *@Description TODO
 *@Author WTF
 *@Date 2022/6/14 14:39
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Car;

import java.io.*;
import java.util.List;
import java.util.Map;

public class MyJacksonReadTest {

    /**
     * JSON字符串-->Java对象
     *
     * @throws JsonProcessingException
     */
    public void jsonStr2Car() throws JsonProcessingException {
        String carJson = "{\"brand\":\"wuLing\",\"doors\":\"2\"}";

        ObjectMapper objectMapper = new ObjectMapper();

        Car car = objectMapper.readValue(carJson, Car.class);

        System.out.println(car.toString());
    }

    /**
     * JSON字符输入流-->Java对象
     *
     * @throws IOException
     */
    public void jsonStream2Car() throws IOException {
        String carJson = "{\"brand\":\"wuLing\",\"doors\":\"2\"}";

        ObjectMapper objectMapper = new ObjectMapper();

        Reader reader = new StringReader(carJson);

        Car car = objectMapper.readValue(reader, Car.class);

        System.out.println(car.toString());
    }

    /**
     * JSON文件-->Java对象
     *
     * @throws IOException
     */
    public void jsonFile2Car() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        File file = new File("src/main/resources/car.json");

        Car car = objectMapper.readValue(file, Car.class);

        System.out.println(car.toString());
    }

    /**
     * JSON字节输入流-->Java对象
     *
     * @throws IOException
     */
    public void jsonInputStream2Car() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        InputStream input = new FileInputStream("src/main/resources/car.json");

        Car car = objectMapper.readValue(input, Car.class);

        System.out.println(car.toString());
    }

    /**
     * JSON数组-->Java对象数组
     *
     * @throws IOException
     */
    public void jsonArray2Cars() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        InputStream input = new FileInputStream("src/main/resources/cars.json");

        Car[] cars = objectMapper.readValue(input, Car[].class);

        for (Car car : cars) {
            System.out.println(car);
        }

    }

    /**
     * JSON数组-->Java对象集合
     *
     * @throws IOException
     */
    public void jsonList2Cars() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        InputStream input = new FileInputStream("src/main/resources/cars.json");

        List<Car> cars = objectMapper.readValue(input, new TypeReference<List<Car>>() {
        });

        for (Car car : cars) {
            System.out.println(car);
        }

    }

    /**
     * JSON数组-->Java对象集合
     *
     * @throws IOException
     */
    public void jsonStr2Map() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        InputStream input = new FileInputStream("src/main/resources/car.json");

        Map<String,Object> map = objectMapper.readValue(input, new TypeReference<Map<String,Object>>() {
        });

        map.forEach((k,v)->{
            System.out.println(k+" : "+v);
        });

    }


}
