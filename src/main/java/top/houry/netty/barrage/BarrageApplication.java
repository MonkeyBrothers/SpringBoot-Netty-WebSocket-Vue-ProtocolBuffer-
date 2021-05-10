package top.houry.netty.barrage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BarrageApplication {

    public static void main(String[] args) {
        SpringApplication.run(BarrageApplication.class, args);
    }

}
