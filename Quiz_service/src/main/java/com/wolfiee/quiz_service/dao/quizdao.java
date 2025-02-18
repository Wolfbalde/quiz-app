package com.wolfiee.quiz_service.dao;

import com.wolfiee.quiz_service.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface quizdao extends JpaRepository<Quiz,Integer> {


}
