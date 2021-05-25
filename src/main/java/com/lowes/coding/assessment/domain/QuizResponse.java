package com.lowes.coding.assessment.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizResponse {
    @JsonProperty("quiz")
    private List<CodingExerciseQuizResults> results;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CodingExerciseQuizResults{
        private String category;
        private List<CodingExerciseQuizResult> results;

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class CodingExerciseQuizResult {
            private String type;
            private String difficulty;
            private String question;
            @JsonProperty("correct_answer")
            private String correctAnswer;
            @JsonProperty("all_answers")
            private List<String> allAnswers;
        }
    }
}
