package com.question.service.Respository;



import com.question.service.Entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionsRepo extends JpaRepository<Questions,Integer> {
    @Query(nativeQuery = true,value = "SELECT q.id FROM questions q WHERE q.category=?1 order by Random() limit ?2")
    List<Integer> findQuestionsByCategory(String category,Integer num);
}
