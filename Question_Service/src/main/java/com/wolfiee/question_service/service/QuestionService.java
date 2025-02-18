package com.wolfiee.question_service.service;



import com.wolfiee.question_service.dao.questiondao;
import com.wolfiee.question_service.model.Question;
import com.wolfiee.question_service.model.QuestionWrapper;
import com.wolfiee.question_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    questiondao questionrep;
    public ResponseEntity<List<Question>> getallquestions() {
        try{
            return new ResponseEntity<>(questionrep.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getbycategory(String category) {
        try{
            return new ResponseEntity<>(questionrep.findByCategory(category),HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<String> addorupdate(Question question) {
        try{
            questionrep.save(question);
            return new ResponseEntity<>("success",HttpStatus.CREATED);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


    }

    public ResponseEntity<String> deletebyid(int id) {
        try{
            questionrep.deleteById(id);
            return new ResponseEntity<>("record deleted",HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


    }

    public ResponseEntity<List<Integer>> getQuestionsforquiz(String category, int numQ) {
        List<Integer> questions = questionrep.findRandomQbyCategory(category, numQ);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getquestionsforuser(List<Integer> questionsforuser) {

        List<QuestionWrapper> kostins= new ArrayList<>();
        List<Question> questions=new ArrayList<>();
        for (Integer id:questionsforuser){
            questions.add(questionrep.findById(id).get());
        }

        for (Question q:questions){
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(q.getId());
            wrapper.setQuestionTitle(q.getQuestionTitle());
            wrapper.setOption1(q.getOption1());
            wrapper.setOption2(q.getOption2());
            wrapper.setOption3(q.getOption3());
            wrapper.setOption4(q.getOption4());
            kostins.add(wrapper);
        }

        return new ResponseEntity<>(kostins,HttpStatus.OK);
    }

    public ResponseEntity<Integer> result(List<Response> responses) {

        int score=0;

        for(Response resp: responses){
            Question kostin=questionrep.findById(resp.getQuestionid()).get();
            if(resp.getResponse().equals(kostin.getRightAnswer())){
                score++;
            }
        }
        return new ResponseEntity<>(score,HttpStatus.OK);
    }
}

