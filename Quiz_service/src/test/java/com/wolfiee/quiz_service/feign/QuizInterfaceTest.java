package com.wolfiee.quiz_service.feign;

import com.wolfiee.quiz_service.model.QuestionWrapper;
import com.wolfiee.quiz_service.model.Response;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QuizInterfaceTest {
    @Mock
    private QuizInterface quizInterface;

    @Test
    public void testCreateQuizFeign() {
        List<Integer> questionIds = List.of(10, 20, 30);
        when(quizInterface.createquiz("Java", 3)).thenReturn(ResponseEntity.ok(questionIds));

        ResponseEntity<List<Integer>> response = quizInterface.createquiz("Java", 3);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(3, response.getBody().size());
    }

    @Test
    public void testGetQuestionsFeign() {
        List<QuestionWrapper> questions = List.of(new QuestionWrapper(1, "Q1", "A", "B", "C", "D"));

        when(quizInterface.getQuestions(List.of(1))).thenReturn(ResponseEntity.ok(questions));

        ResponseEntity<List<QuestionWrapper>> response = quizInterface.getQuestions(List.of(1));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    public void testSubmitResultsFeign() {
        List<Response> responses = List.of(new Response(1, "A"));
        when(quizInterface.result(responses)).thenReturn(ResponseEntity.ok(90));

        ResponseEntity<Integer> response = quizInterface.result(responses);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Optional.of(90), response.getBody());
    }
}
