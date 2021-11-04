package com.wd.myservice.userservice.swaggerconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

@Configuration
@EnableSwagger2//开启Swagger
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot 使用Swagger 构建RestFul API")
                .contact(new Contact("cmf", "https://blog.csdn.net/chai_cmf/", ":"))
                .version("1.0")
                .description("API 描述")
                .build();
    }

    private static Integer temp = 0;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ArrayList<Future<Integer>> list = new ArrayList<>();
        ExecutorService pool = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            list.add(pool.submit(new Abb()));
        }
        int aa = 0;
        for (Future<Integer> future : list) {
            System.out.println(future.get());
            aa += future.get();
        }
        System.out.println(aa);
    }

}

class Abb implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {

        Integer integer = (int) (Math.random() * 100);
        Thread.sleep(integer*100);
        System.out.println("休息了 " + integer*100 + "秒");
        System.out.println("--" + Thread.currentThread().getName());
        return integer;
    }
}