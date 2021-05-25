package com.lowes.coding.assessment.utils;

import com.lowes.coding.assessment.domain.QuizResponse;
import com.lowes.coding.assessment.domain.DataSourceResponse;

import java.util.ArrayList;
import java.util.List;

public class Converter {
    public static QuizResponse convertToQuizResponse(List<DataSourceResponse> dataSourceResponses){
        QuizResponse response = new QuizResponse();
        if(dataSourceResponses!=null) {
            response.setResults(new ArrayList<>());
            for (DataSourceResponse dataSourceResponse : dataSourceResponses) {
                QuizResponse.CodingExerciseQuizResults codingExerciseQuizResults = new QuizResponse.CodingExerciseQuizResults();
                codingExerciseQuizResults.setResults(new ArrayList<>());
                for (DataSourceResponse.ResourceCategoryResult r : dataSourceResponse.getResults()) {
                    QuizResponse.CodingExerciseQuizResults.CodingExerciseQuizResult result = new QuizResponse.CodingExerciseQuizResults.CodingExerciseQuizResult();
                    codingExerciseQuizResults.setCategory(r.getCategory());
                    result.setType(r.getType());
                    result.setQuestion(r.getQuestion());
                    result.setDifficulty(r.getDifficulty());
                    result.setCorrectAnswer(r.getCorrectAnswer());
                    List<String> allAnswer = new ArrayList<>();
                    allAnswer.add(r.getCorrectAnswer());
                    allAnswer.addAll(r.getIncorrectAnswers());
                    result.setAllAnswers(allAnswer);
                    codingExerciseQuizResults.getResults().add(result);
                }
                response.getResults().add(codingExerciseQuizResults);
            }
        }
        return response;
    }
}
