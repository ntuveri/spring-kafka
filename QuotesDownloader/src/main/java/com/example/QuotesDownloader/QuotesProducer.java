package com.example.QuotesDownloader;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.context.PollableBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Configuration
@Slf4j
public class QuotesProducer {

	private AppConfig config;
	private WebClient webClient;
	private Map<String, Quote> stockQuotes = new HashMap<>();

	@Autowired
	public QuotesProducer(AppConfig config) {
		this.config = config;
		log.info("Configured base url is {}", this.config.getIex().getApiBaseUrl());

		this.webClient = WebClient.builder()
			.baseUrl(config.getIex().getApiBaseUrl())
			.build();
	}


	@PollableBean
	public Supplier<Flux<Quote>> updateStockQuote() {

		return () -> {
			Flux<Quote> quotes = Flux.fromIterable(StocksConsumer.stocks).flatMap(
				stock -> {
					log.info("Getting quote for stock symbol {}", stock);

					Mono<Quote> updatedQuotes = this.webClient.get()
						.uri("/stock/{symbol}/quote?token={token}", stock, this.config.getIex().getApiToken())
						.retrieve()
						.bodyToMono(Quote.class)
						.filter(quote -> {
								Quote previousQuote = this.stockQuotes.get(stock);
								if(previousQuote == null || previousQuote.getLatestPrice() != quote.getLatestPrice()) {
									log.info("Quote value updated to {} for stock symbol {}", quote.getLatestPrice(), stock);
									this.stockQuotes.put(stock, quote);
									return true;
								}
								log.info("Quote value {} already updated for stock symbol {}", quote.getLatestPrice(), stock);
								return false;
							}
						);

					return updatedQuotes;
				}
			);

			return quotes;
		};
	}
}
