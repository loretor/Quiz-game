package com.myapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import com.myapp.moneyPrices.Price;
import com.myapp.service.QuizServiceAPII;
import com.myapp.service.QuizServiceI;
import com.myapp.service.QuizServiceImpl;
import com.myapp.util.WebAPIMappings;

/**
 * This is the controller for the AJAX calls. It returns only JSON files to render dinamically the page
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class WebAPIController {
	
	public QuizServiceImpl quizServiceImpl;
	
	@Autowired
	public WebAPIController(QuizServiceImpl quizServiceImpl) {
		this.quizServiceImpl = quizServiceImpl;
	}
	
	@GetMapping(WebAPIMappings.IS_CORRECT)
    public ResponseEntity<String> isCorrect(@RequestParam("answerId") int answerId) {
        return ResponseEntity.ok(String.valueOf(quizServiceImpl.isCorrect(answerId)));
    }
	
	@GetMapping(WebAPIMappings.HAS_ADDITIONAL_LIFE)
    public ResponseEntity<String> hasAdditionalLife() {
        return ResponseEntity.ok(String.valueOf(quizServiceImpl.hasAdditionalLife()));
    }
	
	@GetMapping(WebAPIMappings.REMOVE_ADDITIONAL_LIFE)
    public ResponseEntity<String> removeAdditionalLife() {
		quizServiceImpl.removeAdditionalLife();
        return ResponseEntity.ok("Removed correclty");
    }
	
	//----- API FOR LIFELINES ----
	
	@GetMapping(WebAPIMappings.LIFELINE_FIFTY_FIFITY)
    public ResponseEntity<int[]> useFiftyFifty() {
		log.info("API 50-50 called");
		int[] result = quizServiceImpl.fiftyFifty();
        return ResponseEntity.ok(result);
    }
    
    @GetMapping(WebAPIMappings.LIFELINE_TELEPHONE)
    public ResponseEntity<String> useTelephone(){
    	log.info("API Telephone called");
    	quizServiceImpl.telephone();
    	return ResponseEntity.ok("Called correclty");
    }
    
    @GetMapping(WebAPIMappings.LIFELINE_DOUBLE_DIP)
    public ResponseEntity<String> useDoubleDip(){
    	log.info("API Double Dip called");
    	quizServiceImpl.doubleDip();
    	return ResponseEntity.ok("Called correclty");
    }
    
    @GetMapping(WebAPIMappings.LIFELINE_ASK_AUDIENCE)
    public ResponseEntity<double[]> useAskAudience(){
    	log.info("API Ask Audience called");
    	double[] result = quizServiceImpl.askAudience();
    	return ResponseEntity.ok(result);
    }
}
