package com.wolfiee.quiz_service.service;


import com.wolfiee.quiz_service.dao.quizdao;
import com.wolfiee.quiz_service.feign.QuizInterface;
import com.wolfiee.quiz_service.model.QuestionWrapper;
import com.wolfiee.quiz_service.model.Quiz;
import com.wolfiee.quiz_service.model.Quizdto;
import com.wolfiee.quiz_service.model.Response;
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
    quizdao quizrep;

    @Autowired
    QuizInterface qi;

    public ResponseEntity<String> createquiz(String category, Integer numq, String title) {
        System.out.println("service quiz");
        System.out.println(category);
        System.out.println(numq);
        List<Integer> qid=qi.createquiz(category,numq).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionids(qid);
        quizrep.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getquiz(Integer id) {

        Optional<Quiz> quiz=quizrep.findById(id);
        List<Integer> qids=quiz.get().getQuestionids();
        ResponseEntity<List<QuestionWrapper>> questionforuser= qi.getQuestions(qids);
        return (questionforuser);
    }

    public ResponseEntity<Integer> submitquiz(Integer id, List<Response> responses) {

        ResponseEntity<Integer> result=qi.result(responses);
        return result;
    }

}
