package com.wolfiee.question_service.controller;



import com.wolfiee.question_service.model.Question;
import com.wolfiee.question_service.model.QuestionWrapper;
import com.wolfiee.question_service.model.Response;
import com.wolfiee.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService qs;


    @GetMapping("allquestions")
    public ResponseEntity<List<Question>> getallquestion(){
        System.out.println("Application running");
        return qs.getallquestions();
    }


    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getbycategory(@PathVariable String category){
        return qs.getbycategory(category);

    }


    @PostMapping("addupdate")
    public ResponseEntity<String> Add(@RequestBody Question question){
        return qs.addorupdate(question);
    }


    @PutMapping("addupdate")
    public ResponseEntity<String> update(@RequestBody Question question){

        return qs.addorupdate(question);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){

        return qs.deletebyid(id);
    }


    @GetMapping("createquiz")
    public ResponseEntity<List<Integer>> createquiz(@RequestParam String category, @RequestParam int numQ){
        return qs.getQuestionsforquiz(category,numQ);
    }


    @PostMapping("getquestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestions(@RequestBody List<Integer> questionsforuser){
        return qs.getquestionsforuser(questionsforuser);
    }

    @PostMapping("result")
    public ResponseEntity<Integer> result(@RequestBody List<Response> responses){
            return qs.result(responses);
    }



}
