package com.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.data.Question;
import com.myapp.lifelines.Lifeline;
import com.myapp.logic.QuizEngineI;
import com.myapp.logic.QuizEngineImpl;
import com.myapp.moneyPrices.Price;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class QuizServiceImpl implements QuizServiceI, QuizServiceAPII{
	
	private QuizEngineImpl quizEngine;
	
	@Autowired
	public QuizServiceImpl(QuizEngineImpl quizEngine) {
		this.quizEngine = quizEngine;
	}
	

	@Override
	public Question getQuestion() {
		return quizEngine.getQuestion();
	}


	@Override
	public boolean isCorrect(int answerId) {
		return quizEngine.isCorrect(answerId);
	}


	@Override
	public void nextQuestion() {
		quizEngine.nextQuestion();
	}


	@Override
	public void reset() {
		quizEngine.reset();	
	}


	@Override
	public boolean isQuizWon() {
		return quizEngine.isQuizWon();
	}
	
	@Override
	public List<Lifeline> getLifelines() {
		return quizEngine.getLifelines();
	}
	
	@Override
	public boolean hasAdditionalLife() {
		return quizEngine.hasAdditionalLife();
	}
	
	@Override
	public void removeAdditionalLife() {
		quizEngine.removeAdditionalLife();
	}

	@Override
	public List<Price> getMoneyPrices() {
		return quizEngine.getMoneyPrices();
	}
	
	//============== API METHODS ===============

	@Override
	public int[] fiftyFifty() {
		return quizEngine.fiftyFifty();
	}


	@Override
	public void telephone() {
		quizEngine.telephone();	
	}


	@Override
	public void doubleDip() {
		quizEngine.doubleDip();
		
	}

	@Override
	public double[] askAudience() {
		return quizEngine.askAudience();
	}
}
