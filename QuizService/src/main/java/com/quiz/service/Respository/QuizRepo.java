package com.quiz.service.Respository;


import com.quiz.service.Entity.QuestionsWrapper;
import com.quiz.service.Entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepo extends JpaRepository<Quiz,Integer> {
    @Query(nativeQuery = true,value = "select q.* from quiz_questions_list where q.id =?1")
    List<QuestionsWrapper> findQuestionsById(Integer id);
}
