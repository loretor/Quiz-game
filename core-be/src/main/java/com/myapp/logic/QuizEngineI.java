package com.myapp.logic;

import java.util.List;

import com.myapp.data.Question;
import com.myapp.moneyPrices.Price;

/**
 * Interface with all the methods that can be called by the webApp
 */
public interface QuizEngineI {
	public Question getQuestion();
	
	public boolean isCorrect(int answerId);
	
	public void nextQuestion();
	
	public void reset();
	
	public boolean isQuizWon();
	
	public void removeAdditionalLife();
	
	public List<Price> getMoneyPrices();
}
