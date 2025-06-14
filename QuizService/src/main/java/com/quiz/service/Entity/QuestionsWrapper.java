package com.quiz.service.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestionsWrapper {
    private Integer id;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
}
