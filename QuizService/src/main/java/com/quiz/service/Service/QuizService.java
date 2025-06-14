package com.quiz.service.Service;



import com.quiz.service.Entity.Questions;
import com.quiz.service.Entity.QuestionsWrapper;

import com.quiz.service.Entity.Quiz;
import com.quiz.service.Entity.Response;
import com.quiz.service.Feign.QuizInterface;
import com.quiz.service.Respository.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    QuizInterface quizInterface;
    public  ResponseEntity<String> createQuiz(String title,String category, Integer numQuestions) {
    List<Integer> questions = quizInterface.getQuestionsForQuiz(category,numQuestions).getBody();
    Quiz quiz = new Quiz();
    quiz.setTitle(title);
    quiz.setQuestionsList(questions);
    quizRepo.save(quiz);

        return new ResponseEntity<>("success",HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionsWrapper>> getQuiz(Integer id) {
       Quiz quiz = quizRepo.findById(id).get();
       List<Integer>num = quiz.getQuestionsList();
       List<QuestionsWrapper>ques =  quizInterface.generateQuiz(num).getBody();
       return new ResponseEntity<>(ques,HttpStatus.OK);
    }


    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        //Quiz quiz = quizRepo.findById(id).get();
        int res = quizInterface.getScore(responses).getBody();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
