package com.wolfiee.question_service.dao;


import com.wolfiee.question_service.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface questiondao extends JpaRepository<Question,Integer>{

    List<Question> findByCategory(String category);


    @Query(value = "select q.id from Question q where q.category=:category ORDER BY RANDOM() LIMIT :numQ",nativeQuery = true)
    List<Integer> findRandomQbyCategory(String category, int numQ);
}
