package com.example.QuotesDownloader;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
@Slf4j
public class StocksConsumer {

	public static final List<String> stocks = new ArrayList<>();

	@Bean
	public Consumer<String> acceptStock() {
		return stock -> {
			if(!this.stocks.contains(stock)) {
				log.info("Received stock symbol {}", stock);
				this.stocks.add(stock);
			}
		};
	}
}
