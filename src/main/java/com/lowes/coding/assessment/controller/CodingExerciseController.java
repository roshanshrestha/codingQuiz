package com.lowes.coding.assessment.controller;

import com.lowes.coding.assessment.domain.QuizResponse;
import com.lowes.coding.assessment.domain.DataSourceResponse;
import com.lowes.coding.assessment.services.CodingResourceClient;
import com.lowes.coding.assessment.utils.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/coding/exercise")
public class CodingExerciseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CodingResourceClient resourceClient;

    @Value("${resource1.url}")
    private String resource1;

    @Value("${resource2.url}")
    private String resource2;

    @RequestMapping("/quiz")
    public ResponseEntity<QuizResponse> getQuiz(){
        try{
            CompletableFuture<DataSourceResponse>  response1 = resourceClient.getQuizAsync(resource1);
            CompletableFuture<DataSourceResponse>  response2 = resourceClient.getQuizAsync(resource2);
            List<DataSourceResponse> responses = Arrays.asList(response1.get(),response2.get());
            QuizResponse quizResponse = Converter.convertToQuizResponse(responses);
            return new ResponseEntity<QuizResponse>(quizResponse,HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<QuizResponse>(new QuizResponse(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
