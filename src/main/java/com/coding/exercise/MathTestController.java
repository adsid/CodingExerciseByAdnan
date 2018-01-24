package com.coding.exercise;

import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coding.exercise.SumTestRequest;

@RestController
public class MathTestController {

	private static final int MIN_ALLOWED_OPERAND_COUNT = 2;
	private static final int MAX_ALLOWED_OPERAND_COUNT = 4;
	private static final int MAX_OPERAND_VALUE = 10;
	private static final String QUESTION_TEXT = "Please sum the numbers ";
	
	@RequestMapping(method=RequestMethod.GET, produces="text/html")
	public String getMathSumQuestion() {

		return this.generateSummationQuestion();
	}

	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<?> checkMathSum(@RequestBody SumTestRequest request) {

		int questionOperandSum = getRequestSum(request.getQuestionText());
		
		int submittedSum = request.getSuggestedResult().intValue();
		
		return this.generateResponse(questionOperandSum, submittedSum);
	}

	private ResponseEntity<String> generateResponse(int requestSum, int submittedSum) {
		ResponseEntity<String> responseEntity;
		
		if (requestSum != submittedSum) 
			responseEntity = new ResponseEntity<String>("Incorrect Answer", HttpStatus.BAD_REQUEST);
		else
			responseEntity = new ResponseEntity<String>("Correct Answer", HttpStatus.OK);

		return responseEntity;
	}

	private int getRequestSum(String questionText) {
		
		questionText = questionText.replaceFirst(QUESTION_TEXT, "");
		questionText = questionText.trim();
		
		String[] operandArray = questionText.split(",");
		
		int actualSum = 0;
		for (int i=0; i < operandArray.length; i++) {
			actualSum += Integer.valueOf(operandArray[i]).intValue();
		}

		return actualSum;
	}
	
	private String generateSummationQuestion() {

		StringBuffer questionText = new StringBuffer(QUESTION_TEXT);
		
		for (int i=0; i < generateOperandCount(); i++) {
			if (i>0) questionText.append(",");
			questionText.append(generateOperand());
		}

		return questionText.toString();
	}

	private int generateOperandCount() {
		Random rand = new Random();
		
		int operandCount = 0;
		while (operandCount < MIN_ALLOWED_OPERAND_COUNT) {
			operandCount = rand.nextInt(MAX_ALLOWED_OPERAND_COUNT) + 1;
		}
		return operandCount;
	}

	private int generateOperand() {
		Random rand = new Random();
		return rand.nextInt(MAX_OPERAND_VALUE) + 1;
	}

}
