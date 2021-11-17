package me.glinde.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("me.glinde.www.dao")
@SpringBootApplication
@EnableTransactionManagement
public class BlogApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BlogApplication.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

}
