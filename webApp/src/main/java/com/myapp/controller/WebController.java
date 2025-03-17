package com.myapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myapp.data.Question;
import com.myapp.lifelines.Lifeline;
import com.myapp.moneyPrices.Price;
import com.myapp.service.QuizServiceI;
import com.myapp.util.ViewNames;
import com.myapp.util.WebMappings;

import lombok.extern.slf4j.Slf4j;

/**
 * Controller for the Views. It returns the HTML pages with Thymeleaf
 */
@Slf4j
@Controller
public class WebController {

	public QuizServiceI quizService;
	
	@Autowired
	public WebController(QuizServiceI quizService) {
		this.quizService = quizService;
	}
	
	@GetMapping(WebMappings.QUESTION)
	public String question(Model model) {
		//check if the game is finished
		if(quizService.isQuizWon()) {
			return ViewNames.GAME_WIN;
		}
		
		List<Lifeline> lifelines = quizService.getLifelines();
		Question currQuestion = quizService.getQuestion();
		List<Price> moneyPrices = quizService.getMoneyPrices();
		
		model.addAttribute("lifelines", lifelines);
		model.addAttribute("currentQuestion", currQuestion);
		model.addAttribute("moneyPrices", moneyPrices);
		
		return ViewNames.QUESTION;		
	}
	
	@GetMapping(WebMappings.BACK_TO_MENU)
	public String restart() {
		quizService.reset();
		return WebMappings.REDIRECT_HOME;
	}
	
	@GetMapping(WebMappings.NEXT_QUESTION)
	public String nextQuestion() {
		quizService.nextQuestion();
		return WebMappings.REDIRECT_QUESTION;
	}
	
	@GetMapping(WebMappings.GAME_OVER)
	public String gameOver() {
		return ViewNames.GAME_OVER;
	}
}
