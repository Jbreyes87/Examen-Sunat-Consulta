package com.sunat_consulta.sunat_consulta;

import com.sunat_consulta.sunat_consulta.config.DecolectaApiProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(DecolectaApiProperties.class)
public class SunatConsultaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SunatConsultaApplication.class, args);
	}
}
