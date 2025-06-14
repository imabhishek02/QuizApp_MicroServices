package com.quiz.service.Entity;

import lombok.Data;

@Data
public class QuizDto {
    private String title;
    private Integer numQuestion;
    private String category;
}
