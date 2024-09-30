package com.github.gabguedes.ms_pagamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MsPagamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsPagamentoApplication.class, args);
	}

}
