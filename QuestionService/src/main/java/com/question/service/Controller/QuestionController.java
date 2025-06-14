package com.question.service.Controller;


import com.question.service.Entity.Questions;
import com.question.service.Entity.QuestionsWrapper;
import com.question.service.Entity.Response;
import com.question.service.Service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionsService questionsService;

    @PostMapping("create")
    public Questions createQuestion(@RequestBody Questions questions){
        return questionsService.createQuestion(questions);
    }

    @GetMapping("getAllQuestion")
    public List<Questions> getQuestion(){
        return questionsService.getQuestions();
    }
    //Generate the list of question id for us based on the category and number
    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category,@RequestParam Integer num){
        return questionsService.getQuestionsForQuize(category,num);
    }

    //this will give us the questions based on given list of question id
    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionsWrapper>> generateQuiz(@RequestBody List<Integer>ls){
        return questionsService.getQuestionsFromId(ls);
    }
    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response>responses){
        return questionsService.getScore(responses);
    }
}
