package dobby.dobbyqs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "dobby.dobbyqs.mybatis.mapper")
@SpringBootApplication
public class DobbyqsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DobbyqsApplication.class, args);
    }

}
