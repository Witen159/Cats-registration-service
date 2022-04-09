package ru.itmo.kotikijava.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.Enumerated;

@SpringBootApplication
@EntityScan(basePackages = "ru.itmo.kotiki.models")
@EnableJpaRepositories(basePackages = "ru.itmo.kotiki.Interfaces")
@ComponentScan(basePackages = {"ru.itmo.kotiki.Interfaces", "ru.itmo.kotikijava.web.controllers", "ru.itmo.kotiki.services"})
public class WebApplication {
	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
}

