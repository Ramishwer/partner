package com.goev.partner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.goev.lib", "com.goev.partner"})
@Slf4j
public class PartnerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PartnerApplication.class, args);
	}

}
