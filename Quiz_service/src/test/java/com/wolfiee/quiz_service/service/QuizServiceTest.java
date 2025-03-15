package com.wolfiee.quiz_service.service;

import com.wolfiee.quiz_service.dao.quizdao;
import com.wolfiee.quiz_service.feign.QuizInterface;
import com.wolfiee.quiz_service.model.QuestionWrapper;
import com.wolfiee.quiz_service.model.Quiz;
import com.wolfiee.quiz_service.model.Response;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QuizServiceTest {
    @InjectMocks
    private QuizService quizService;

    @Mock
    private quizdao quizrep;

    @Mock
    private QuizInterface qi;

    @Test
    public void testCreateQuiz() {
        String category = "Java";
        Integer numq = 5;
        String title = "Java Quiz";
        List<Integer> questionIds = List.of(1, 2, 3, 4, 5);

        when(qi.createquiz(category, numq)).thenReturn(ResponseEntity.ok(questionIds));

        ResponseEntity<String> response = quizService.createquiz(category, numq, title);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Success", response.getBody());
        verify(quizrep, times(1)).save(any(Quiz.class));
    }

    @Test
    public void testGetQuiz() {
        Integer quizId = 1;
        Quiz quiz = new Quiz(quizId, "Java Quiz", List.of(10, 20));
        when(quizrep.findById(quizId)).thenReturn(Optional.of(quiz));

        List<QuestionWrapper> questions = List.of(
                new QuestionWrapper(10, "What is Java?", "A", "B", "C", "D"),
                new QuestionWrapper(20, "What is OOP?", "X", "Y", "Z", "W")
        );

        when(qi.getQuestions(List.of(10, 20))).thenReturn(ResponseEntity.ok(questions));

        ResponseEntity<List<QuestionWrapper>> response = quizService.getquiz(quizId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testSubmitQuiz() {
        List<Response> responses = List.of(
                new Response(10, "A"),
                new Response(20, "Y")
        );

        when(qi.result(responses)).thenReturn(ResponseEntity.ok(80));

        ResponseEntity<Integer> result = quizService.submitquiz(1, responses);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(80, result.getBody());
    }
}
