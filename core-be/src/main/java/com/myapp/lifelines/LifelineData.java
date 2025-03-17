package com.myapp.lifelines;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.myapp.data.Question;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Component
public class LifelineData {
	private List<Lifeline> lifelines;
	
	public LifelineData() {
		lifelines = new ArrayList<>();
		
		//populating 
		lifelines.add(new Lifeline("50-50"));
		lifelines.add(new Lifeline("Telephone"));
		lifelines.add(new Lifeline("Double Dip"));
		lifelines.add(new Lifeline("Ask Audience"));
	}
	
	public void reset() {
		for(int i = 0; i < lifelines.size(); i++) {
			lifelines.get(i).setActive(true);
		}
	}
	

	// ========= METHODS FOR THE LIFELINES ======== 
	
	/**
	 * Return 2 randomly chosen questions out of the 4 that are not correct.
	 * @param question
	 * @return
	 */
	public int[] fiftyFifty(Question question) {
		int[] res = new int[2];
		Random random = new Random();
		
		for(int i = 0; i < 2; i++) {
			int randomChoice = -1;
			while(true) {
				randomChoice = random.nextInt(4);
				if(!question.getAnswers().get(randomChoice).isCorrect()) {
					if(i == 1 && randomChoice == res[0]) { //not two options equal
						continue;
					}
					break;
				}
			}
			res[i] = randomChoice;
		}
		
		lifelines.get(0).setActive(false); //set the 50-50 lifeline to false after being used
		
		return res;
	}
	
	/**
	 * Set the telephone's lifeline to no more active
	 */
	public void telephone() {
		//for the telephone all the logic is handled by js
		lifelines.get(1).setActive(false);
	}
	
	/**
	 * Add one additional life for answering this question
	 * @return
	 */
	public int doubleDip() {
		lifelines.get(2).setActive(false);
		return 1;
	}
	
	/**
	 * Generate 4 random values for the possible answers by the Audience
	 * @param question
	 * @param currentQuestion
	 * @return
	 */
	public double[] askAudience(Question question, int currentQuestion) {
		double[] res = new double[4];
		Random random = new Random();
		
		//algorithm to create 4 percentages, which sum is equal to 1, based on the currentQuestion and the correct answer
		//the more the currentQuestion is low (first questions) the higher is the consensus on the correct answer		
		int correctIndex = 0;
		for(int i = 0; i < 4; i++) {
			if(question.getAnswers().get(i).isCorrect()) {
				correctIndex = i;
				break;
			}
		}
		
		//Probability of the correct answer based on difficulty
		double easiness = (currentQuestion < 5) ? 1.0 : 1.0 / (currentQuestion / 5.0);
		log.info("Easyness: " + easiness);
		
        res[correctIndex] = easiness * (0.6 + random.nextDouble(0.4));
		 
        double remaining = 1.0 - res[correctIndex];
		
		//let's generate 2 values out of remaining
        List<Integer> wrongIndexes = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if (i != correctIndex) {
                wrongIndexes.add(i);
            }
        }
		
        double r1 = random.nextDouble(remaining);
        double r2 = random.nextDouble((remaining - r1));
        double r3 = remaining - r1 - r2;
        
        res[wrongIndexes.get(0)] = r1;
        res[wrongIndexes.get(1)] = r2;
        res[wrongIndexes.get(2)] = r3;
		
		//final print
		for(int i = 0; i < 4; i++) {
		    log.info("Value "+ i + ": " + res[i]);
		}
		
		lifelines.get(3).setActive(false);
		
		return res;
	}
}
