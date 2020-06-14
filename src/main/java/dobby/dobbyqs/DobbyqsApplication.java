package dobby.dobbyqs;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@MapperScans({
        @MapperScan("dobby.dobbyqs.web.openAPI.mapper"),
        @MapperScan("dobby.dobbyqs.mybatis.mapper")
})
@SpringBootApplication
public class DobbyqsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DobbyqsApplication.class, args);
    }

}
