package com.wolfiee.quiz_service.feign;


import com.wolfiee.quiz_service.model.QuestionWrapper;
import com.wolfiee.quiz_service.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")

public interface QuizInterface {

    @GetMapping("question/createquiz")
    public ResponseEntity<List<Integer>> createquiz(@RequestParam String category, @RequestParam int numQ);


    @PostMapping("question/getquestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestions(@RequestBody List<Integer> questionsforuser);

    @PostMapping("question/result")
    public ResponseEntity<Integer> result(@RequestBody List<Response> responses);
}
