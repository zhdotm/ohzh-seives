package io.github.zhdotm.ohzh.sieve.starter.web;

import io.github.zhdotm.ohzh.sieve.starter.web.annotation.EnableSpringSieve;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhihao.mao
 */

@EnableSpringSieve
@SpringBootApplication
@MapperScan(basePackages = "io.github.zhdotm.ohzh.sieve.starter.web.mapper")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
