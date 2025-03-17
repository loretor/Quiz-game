package com.myapp.data;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;

import java.io.FileNotFoundException;
import java.io.InputStream;

@Getter
@Component
public class QuizData {
	private static final Logger log = LoggerFactory.getLogger(QuizData.class);
	Question[] questions;
	
	@PostConstruct
	public void init() {
		log.info("--- START LOADING QUESTIONS ---");
		ObjectMapper mapper = new ObjectMapper();
		
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("questions/questions.json")) {
	        if (inputStream == null) {
	            throw new FileNotFoundException("File questions/db.json not found in classpath");
	        }
	        questions = mapper.readValue(inputStream, Question[].class);
	        log.info("Loaded " + questions.length + " questions from the JSON file");
	    } catch (Exception e) {
	        log.error("Error loading questions", e);
	    }

	    log.info("--- FINISH LOADING QUESTIONS ---");
	}
}
