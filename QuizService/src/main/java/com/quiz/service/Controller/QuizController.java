package com.quiz.service.Controller;


import com.quiz.service.Entity.Questions;
import com.quiz.service.Entity.QuestionsWrapper;
import com.quiz.service.Entity.QuizDto;
import com.quiz.service.Entity.Response;
import com.quiz.service.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quizmicro")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("createQuiz")
    private ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){

        return quizService.createQuiz(quizDto.getTitle(),quizDto.getCategory(),quizDto.getNumQuestion());
    }

    @GetMapping("getQuiz/{id}")
    private ResponseEntity<List<QuestionsWrapper>> getQuiz(@PathVariable  Integer id){
        return quizService.getQuiz(id);
    }
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id, responses);
    }


}
