package com.myapp.lifelines;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Lifeline {
	private String name;
	private boolean isActive;
	
	public Lifeline(String name) {
		this.name = name;
		isActive = true;
	}
}
