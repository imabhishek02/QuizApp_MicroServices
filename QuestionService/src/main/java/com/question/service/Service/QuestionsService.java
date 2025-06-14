package com.question.service.Service;


import com.question.service.Entity.Questions;
import com.question.service.Entity.QuestionsWrapper;
import com.question.service.Entity.Response;
import com.question.service.Respository.QuestionsRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionsService {
    @Autowired
    private QuestionsRepo questionsRepo;

    @Autowired
    private ModelMapper modelMapper;

    public Questions createQuestion(Questions questions) {
        return questionsRepo.save(questions);
    }

    public List<Questions> getQuestions() {
        return questionsRepo.findAll();
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuize(String category, Integer num) {
        List<Integer> listOfQuestions = questionsRepo.findQuestionsByCategory(category,num);
        return new ResponseEntity<>(listOfQuestions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionsWrapper>> getQuestionsFromId(List<Integer> ls) {
        List<Questions> questions = questionsRepo.findAllById(ls);
        List<QuestionsWrapper> AllQuestion = questions.stream()
                .map(q ->modelMapper.map(q,QuestionsWrapper.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(AllQuestion,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int score = 0;
        for(Response res : responses){
            Questions question = questionsRepo.findById(res.getId()).get();
            if(question.getRightAnswer().equals(res.getResponse())){
                score++;
            }
        }
        return new ResponseEntity<>(score,HttpStatus.OK);
    }
}
