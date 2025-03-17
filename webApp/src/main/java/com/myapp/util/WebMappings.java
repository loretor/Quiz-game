package com.myapp.util;

/**
 * These are the possible URLs
 */
public class WebMappings {
	
	public static final String QUESTION = "question";
	public static final String BACK_TO_MENU = "backToMenu";
	public static final String NEXT_QUESTION = "nextQuestion";
	public static final String GAME_OVER = "gameOver";
	
	public static final String REDIRECT_HOME = "redirect:/";
	public static final String REDIRECT_QUESTION = "redirect:/" + QUESTION;
	
	private WebMappings() {}
}
