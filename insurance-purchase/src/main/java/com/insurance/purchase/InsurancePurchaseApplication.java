package com.insurance.purchase;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class InsurancePurchaseApplication implements CommandLineRunner {
	@Value("${policy.document.path}")
    private String policyDocumentPath;
	public static void main(String[] args) {
		SpringApplication.run(InsurancePurchaseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		 Path path = Paths.get(policyDocumentPath);
	        if (!Files.exists(path)) {
	            Files.createDirectories(path);
	        }
		
	}

}
