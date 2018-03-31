package com.example.demo;

import com.example.demo.service.impl.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.Resource;

@SpringBootApplication
@EnableAsync
public class DemoApplication implements CommandLineRunner {
	@Resource
	StorageService storageService;
	public static void main(String[] args)  {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		storageService.deleteAll();
		storageService.init();
	}
}
