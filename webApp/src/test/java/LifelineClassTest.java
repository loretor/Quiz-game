import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.myapp.WebMain;
import com.myapp.lifelines.LifelineData;
import com.myapp.logic.QuizEngineImpl;

@SpringBootTest(classes = WebMain.class)
public class LifelineClassTest {
	
	@Autowired
	public QuizEngineImpl quizEngine;
	
	@Autowired
	public LifelineData lifelines;
	
	@Test
	public void testAskAudience(){
		 assertNotNull(quizEngine);
		 
		 //lifelines.askAudience(quizEngine.getQuestions()[3], 3);
		 lifelines.askAudience(quizEngine.getQuestions()[11], 11);
	}

}
