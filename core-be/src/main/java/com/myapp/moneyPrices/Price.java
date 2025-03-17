package com.myapp.moneyPrices;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Price {
	private int value;
	private boolean isReached;
	
	public Price(int value, boolean isReached) {
		this.value = value;
		this.isReached = isReached;
	}
}
