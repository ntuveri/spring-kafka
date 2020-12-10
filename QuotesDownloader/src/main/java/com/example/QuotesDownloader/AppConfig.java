package com.example.QuotesDownloader;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
@Data
public class AppConfig {

	private IexConfig iex = new IexConfig();

	@Data
	public class IexConfig {
		private String apiToken;
		private String apiBaseUrl;
	}
}
