package com.example.QuotesDownloader;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quote {

	private String symbol;
	private double latestPrice;
}
