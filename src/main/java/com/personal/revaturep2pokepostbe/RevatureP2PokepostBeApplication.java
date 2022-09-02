package com.personal.revaturep2pokepostbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class RevatureP2PokepostBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(RevatureP2PokepostBeApplication.class, args);
		System.out.println("-------------------------------------------------------------------\n" +
						   "  _____   _____   _   _   _____   _____   _____   _____   _______  \n" +
						   " |  _  | |  _  | | | / / |  ___| |  _  | |  _  | |  ___| |__   __| \n" +
						   " | |_| | | | | | | |/ /  | |__   | |_| | | | | | | |___     | |    \n" +
						   " |  ___| | | | | |   {   |  __|  |  ___| | | | | |___  |    | |    \n" +
						   " | |     | |_| | | |\\ \\  | |___  | |     | |_| |  ___| |    | |    \n" +
						   " |_|     |_____| |_| \\_\\ |_____| |_|     |_____| |_____|    |_|    \n\n" +
						   "-------------------------------------------------------------------\n");
	}
	
	@Bean
	public WebClient webClient() {
	    final int size = 16 * 1024 * 1024;
	    final ExchangeStrategies strategies = ExchangeStrategies.builder()
	        .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
	        .build();
	    return WebClient.builder()
	        .exchangeStrategies(strategies)
	        .build();
	}
}
