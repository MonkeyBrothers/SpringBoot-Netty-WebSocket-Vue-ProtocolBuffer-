package top.houry.netty.barrage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("top.houry.netty.barrage.dao")
public class BarrageApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplicationBuilder().sources(BarrageApplication.class).build();
        springApplication.setWebApplicationType(WebApplicationType.NONE);
        springApplication.run(args);
    }

}
