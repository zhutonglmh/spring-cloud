package com.zt.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

/**
 * @author 朱同
 */
@SpringBootApplication
//@EnableEurekaClient  //声明这是一个服务提供者
@ServletComponentScan
@MapperScan({"com.zt.demo.dao"})
@EnableAsync
public class ProductServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServerApplication.class, args);
		System.out.println("启动完毕！");
	}
}
