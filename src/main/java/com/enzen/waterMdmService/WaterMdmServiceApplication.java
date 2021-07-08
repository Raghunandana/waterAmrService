package com.enzen.waterMdmService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class WaterMdmServiceApplication extends SpringBootServletInitializer {

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WaterMdmServiceApplication.class);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(WaterMdmServiceApplication.class, args);
	}

}
