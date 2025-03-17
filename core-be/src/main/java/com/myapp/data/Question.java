package com.myapp.data;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Question {
	private static final Logger log = LoggerFactory.getLogger(Question.class);
	
	private int id;
	private String text;
	private List<Answer> answers;
	
	
	public void displayQuestion() {
		log.info("==== Question number " + id + " ====");
		log.info(text);
		
		for(int i = 0; i < answers.size(); i++) {
			log.info("  " + (i+1) + ". " + answers.get(i).getText());
		}
		
		log.info("Answer to question number " + id +":");
	}
}
