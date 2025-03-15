package com.wolfiee.quiz_service.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelTest {
    @Test
    public void testQuizDto() {
        Quizdto quizdto = new Quizdto();
        quizdto.setCategory("Java");
        quizdto.setNumq(10);
        quizdto.setTitle("Java Basics");

        assertEquals("Java", quizdto.getCategory());
        assertEquals(10, quizdto.getNumq());
        assertEquals("Java Basics", quizdto.getTitle());
    }

    @Test
    public void testQuestionWrapper() {
        QuestionWrapper question = new QuestionWrapper(1, "What is Java?", "A", "B", "C", "D");

        assertEquals(1, question.getId());
        assertEquals("What is Java?", question.getQuestionTitle());
        assertEquals("A", question.getOption1());
    }

    @Test
    public void testResponse() {
        Response response = new Response(1, "A");

        assertEquals(1, response.getQuestionid());
        assertEquals("A", response.getResponse());
    }
}
