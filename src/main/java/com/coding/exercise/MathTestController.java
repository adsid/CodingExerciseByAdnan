package com.coding.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathTestController {

	@RequestMapping(value = "/mathaddquestions", method=RequestMethod.GET)
	public String getMathAddQuestion() {
		Random rand = new Random();
		int numberOfOperands = rand.nextInt(3) + 1;
		
		List<Integer> operantList = new ArrayList<Integer>();
		
		Integer newNumber = null; 
		StringBuffer questionText = new StringBuffer("Please sum the numbers ");
		for (int i=0; i<numberOfOperands; i++) {
			if (i>0) questionText.append(",");
			newNumber = rand.nextInt(10) + 1;
			questionText.append(newNumber);
		}
		
		return questionText.toString();
	}
	
	@RequestMapping(value="/mathaddquestions", method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<?> checkMathSum(@RequestBody SumTestRequest request) {
		String bufferStr = request.getQuestionText();
		
		System.out.println(bufferStr);
		bufferStr = bufferStr.replaceFirst("Please sum the numbers ", "");
		bufferStr = bufferStr.trim();
		String[] numberArray = bufferStr.split(",");
		int finalSum = 0;
		for (int i=0; i<numberArray.length;i++) {
			finalSum += Integer.valueOf(numberArray[i]).intValue();
		}
		
		int resultInRequest = request.getSuggestedResult().intValue();
		
		ResponseEntity responseEntity;
		if (finalSum != resultInRequest) 
			responseEntity = ResponseEntity.badRequest().build();
		else
			responseEntity = ResponseEntity.ok().build();
		return responseEntity;
	}
}
