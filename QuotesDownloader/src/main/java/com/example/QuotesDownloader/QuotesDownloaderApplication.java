package com.example.QuotesDownloader;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class QuotesDownloaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuotesDownloaderApplication.class, args);
	}
}
