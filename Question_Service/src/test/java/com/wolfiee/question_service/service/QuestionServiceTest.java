package com.wolfiee.question_service.service;

import com.wolfiee.question_service.model.Question;
import com.wolfiee.question_service.model.QuestionWrapper;
import com.wolfiee.question_service.model.Response;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import com.wolfiee.question_service.dao.questiondao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceTest {

    @Mock
    private questiondao questionrep;
    @InjectMocks
    private QuestionService questionService;

    private Question question;


    @BeforeEach
    void kostin(){
        question = new Question();
        question.setQuestionTitle("What is Java?");
        question.setOption1("Programming Language");
        question.setOption2("Animal");
        question.setOption3("Food");
        question.setOption4("City");
        question.setRightAnswer("Programming Language");
        question.setCategory("Programming");
        question.setDifficultylevel("Easy");
    }

    @Test
    public void testgetallkostin(){
        when(questionrep.findAll()).thenReturn(List.of(question));
        ResponseEntity<List<Question>> response = questionService.getallquestions();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
        assertEquals(1, response.getBody().size());
        assertEquals("What is Java?", response.getBody().get(0).getQuestionTitle());

    }

    @Test
    public void testgetbycategory(){
        when(questionrep.findByCategory("Programming")).thenReturn(List.of(question));
        ResponseEntity<List<Question>> response = questionService.getbycategory("Programming");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
        assertEquals(1, response.getBody().size());
    }

    @Test
    public void testaddorupdate(){
        when(questionrep.save(question)).thenReturn(question);
        ResponseEntity<String> response = questionService.addorupdate(question);
        assertEquals(201, response.getStatusCodeValue());

    }

    @Test
    public void testdeletebyid(){
        doNothing().when(questionrep).deleteById(1);
        ResponseEntity<String> response = questionService.deletebyid(1);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testgetquestionsforquiz(){
        when(questionrep.findRandomQbyCategory("Programming",1)).thenReturn(List.of(1));
        ResponseEntity<List<Integer>> response = questionService.getQuestionsforquiz("Programming",1);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals(1, response.getBody().get(0));
    }

    @Test
    public void testgetquestionsforuser(){
        when(questionrep.findById(1)).thenReturn(Optional.of(question));

        ResponseEntity<List<QuestionWrapper>> response = questionService.getquestionsforuser(List.of(1));

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals("What is Java?", response.getBody().get(0).getQuestionTitle());
    }

    @Test
    void testResult() {
        Response userResponse = new Response();
        userResponse.setQuestionid(1);
        userResponse.setResponse("Programming Language");

        when(questionrep.findById(1)).thenReturn(Optional.of(question));

        ResponseEntity<Integer> response = questionService.result(List.of(userResponse));

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody());
    }

}
