[参考](https://juejin.cn/post/6844904166809157639#heading-1)
# 1. 依赖导入
`jackson-databind`依赖`jackson-core`和`jackson-annotations`，当添加`jackson-databind`之后，`jackson-core`和`jackson-annotations`也随之添加到 Java 项目工程中。在添加相关依赖包之后，就可以使用 Jackson。
```xml
        <!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.13.3</version>
        </dependency>
```
# 2. 使用
## 2.1. JSON转Java对象
### 2.1.1. JSON字符串-->Java对象
```java
        String carJson = "{\"brand\":\"wuLing\",\"doors\":\"2\"}";

        ObjectMapper objectMapper = new ObjectMapper();

        Car car = objectMapper.readValue(carJson, Car.class);
```
> **ObjectMapper如何匹配JSON对象的字段和Java对象的属性?**  

将JSON字段的名称与Java对象中的getter和setter方法进行映射，如getNameDesc()对应nameDesc。  
如果需要以其他方式将JSON字段与Java对象字段匹配，则需自定义序列化反序列化，或者使用一些Jackson注解。

### 2.1.2. JSON字符输入流-->Java对象

