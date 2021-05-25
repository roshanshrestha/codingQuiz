package com.lowes.coding.assessment.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataSourceResponse {
    @JsonProperty("response_code")
    private String responseCode;
    private List<ResourceCategoryResult> results;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResourceCategoryResult{
        private String category;
        private String type;
        private String difficulty;
        private String question;
        @JsonProperty("correct_answer")
        private String correctAnswer;
        @JsonProperty("incorrect_answers")
        private List<String> incorrectAnswers;
    }
}


