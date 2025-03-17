package com.myapp.moneyPrices;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Component
public class PriceData {
	private List<Price> moneyPrices;

	
	public PriceData() {
		moneyPrices = new ArrayList<>();
	}
	
	@PostConstruct
	public void init() {
		log.info("--- START LOADING MONEY PRICES ---");
		ObjectMapper mapper = new ObjectMapper();
		
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("prices/db.json")) {
	        if (inputStream == null) {
	            throw new FileNotFoundException("File prices/db.json not found in classpath");
	        }
	        
	        int[] prices = mapper.readValue(inputStream, int[].class);
	             
	        for(int price: prices) {
	        	moneyPrices.add(new Price(price, false));
	        }
	        log.info("Loaded " + prices.length + " prices from the JSON file");
	        
	    } catch (Exception e) {
	        log.error("Error loading questions", e);
	    }

	    log.info("--- FINISH LOADING MONEY PRICES ---");
	}
	
	/**
	 * Set the new price as reached
	 * @param index
	 */
	public void reachedPrice(int index) {
		moneyPrices.get(index).setReached(true);
	}
	
	public void reset() {
		for(Price p: moneyPrices) {
			p.setReached(false);
		}
	}
}
