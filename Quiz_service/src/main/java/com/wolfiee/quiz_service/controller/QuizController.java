package com.wolfiee.quiz_service.controller;


import com.wolfiee.quiz_service.model.QuestionWrapper;
import com.wolfiee.quiz_service.model.Quizdto;
import com.wolfiee.quiz_service.model.Response;
import com.wolfiee.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("qui")
public class QuizController {

    @Autowired
    QuizService qs;



    @PostMapping("create")
    public ResponseEntity<String> createquiz(@RequestBody Quizdto quizdto){
        System.out.println("createquiz");
        return qs.createquiz(quizdto.getCategory(),quizdto.getNumq(),quizdto.getTitle());
    }


    @GetMapping("getquiz/{id}")
    public ResponseEntity<List<QuestionWrapper>> getquiz(@PathVariable int id){


        return qs.getquiz(id);

    }


    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submit(@PathVariable Integer id, @RequestBody List<Response> responses){
        return qs.submitquiz(id,responses);
    }
}
