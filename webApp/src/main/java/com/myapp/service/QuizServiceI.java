package com.myapp.service;

import java.util.List;

import com.myapp.data.Question;
import com.myapp.lifelines.Lifeline;
import com.myapp.moneyPrices.Price;

public interface QuizServiceI {
	public Question getQuestion();
	
	public boolean isCorrect(int answerId);
	
	public void nextQuestion();
	
	public void reset();
	
	public boolean isQuizWon();
	
	public List<Lifeline> getLifelines();
	
	public boolean hasAdditionalLife();
	
	public void removeAdditionalLife();
	
	public List<Price> getMoneyPrices();
}
