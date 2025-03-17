package com.myapp.logic;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myapp.data.Question;
import com.myapp.data.QuizData;
import com.myapp.lifelines.Lifeline;
import com.myapp.lifelines.LifelineData;
import com.myapp.moneyPrices.Price;
import com.myapp.moneyPrices.PriceData;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class QuizEngineImpl implements QuizEngineI, QuizEngineAPII{
	
	private final QuizData quizData;
	private final LifelineData lifelineData;
	private final PriceData priceData;
	
	private int currQuestion = 0;
	
	private int additionalLife = 0;
	
	@Autowired
	public QuizEngineImpl(QuizData quizData, LifelineData lifelineData, PriceData priceData) {
		this.quizData = quizData;
		this.lifelineData = lifelineData;
		this.priceData = priceData;
	}
	
	public List<Lifeline> getLifelines() {
		return lifelineData.getLifelines();
	}
	
	public boolean hasAdditionalLife() {
		log.info("The player has " + additionalLife + " additionalLifes");
		return additionalLife > 0;
	}
	
	public Question[] getQuestions(){
		return quizData.getQuestions();
	}

	
	/* ========== INTERFACE METHODS =========== */

	@Override
	public Question getQuestion() {
		return quizData.getQuestions()[currQuestion];
	}


	@Override
	public boolean isCorrect(int answerId) {
		if(quizData.getQuestions()[currQuestion].getAnswers().get(answerId).isCorrect()) {
			log.info("Correct answer for question number " + (currQuestion+1));
			return true;
		}
		else {
			log.info("Choice number " + answerId + " is not correct. Try Again.");
			return false;
		}
	}


	@Override
	public void nextQuestion() {	
		priceData.reachedPrice(currQuestion);
		currQuestion++;
		additionalLife = 0;
		log.info("Loading question number " + (currQuestion+1));
	}


	@Override
	public void reset() {
		log.info("Resetting the game...");
		currQuestion = 0;
		lifelineData.reset();
		priceData.reset();
		log.info("...game correctly resetted!");
	}


	@Override
	public boolean isQuizWon() {
		if(currQuestion >= quizData.getQuestions().length) {
			log.info("The game is finished, Congratulations!");
			return true;
		}
		else {
			log.info("There are other questions to answer...");
			return false;
		}	
	}
	
	@Override
	public void removeAdditionalLife() {
		log.info("The player has no more additional lifes");
		additionalLife = 0;
	}
	
	@Override
	public List<Price> getMoneyPrices() {
		return priceData.getMoneyPrices();
	}

	
	// ========= INTERFACE API METHODS ============
	
	@Override
	public int[] fiftyFifty() {
		log.info("Using the 50-50 lifeline...");
		Question currQuestion = getQuestion();
		return lifelineData.fiftyFifty(currQuestion);
	}
	
	@Override
	public void telephone() {
		log.info("Using the Telephone lifeline...");
		lifelineData.telephone();
	}
	
	@Override
	public void doubleDip() {
		log.info("Using the Double Dip lifeline...");
		additionalLife += lifelineData.doubleDip();
	}

	@Override
	public double[] askAudience() {
		log.info("Using the askAudience lifeline...");
		return lifelineData.askAudience(getQuestion(), currQuestion+1);
	}
}
