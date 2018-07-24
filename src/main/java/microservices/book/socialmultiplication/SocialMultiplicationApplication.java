package microservices.book.socialmultiplication;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("microservices.book.socialmultiplication.mapper")
@SpringBootApplication
public class SocialMultiplicationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialMultiplicationApplication.class, args);
    }
}
