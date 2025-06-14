package com.quiz.service.Feign;

import com.quiz.service.Entity.QuestionsWrapper;
import com.quiz.service.Entity.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
    @GetMapping("question/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam Integer num);

    //this will give us the questions based on given list of question id
    @PostMapping("question/getQuestions")
    public ResponseEntity<List<QuestionsWrapper>> generateQuiz(@RequestBody List<Integer>ls);
    @PostMapping("question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response>responses);
}
