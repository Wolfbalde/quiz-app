package com.wolfiee.quiz_service.dao;

import com.wolfiee.quiz_service.model.Quiz;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)

public class quizdaotest {

    @Autowired
    private quizdao quizRepository;

    @Test
    public void testSaveQuiz() {
        Quiz quiz = new Quiz();
        quiz.setTitle("Java Basics");
        quiz.setQuestionids(List.of(101, 102, 103));
        Quiz savedQuiz = quizRepository.save(quiz);
        assertNotNull(savedQuiz);
        assertEquals("Java Basics", savedQuiz.getTitle());
    }


    @Test
    public void testFindById() {
        Quiz quiz = new Quiz();
        quiz.setTitle("Python Basics");
        quiz.setQuestionids(List.of(201, 202));
        Quiz savedQuiz = quizRepository.saveAndFlush(quiz);
        Optional<Quiz> foundQuiz = quizRepository.findById(savedQuiz.getId());

        assertTrue(foundQuiz.isPresent());
        assertEquals("Python Basics", foundQuiz.get().getTitle());
    }

    @Test
    public void testFindAllQuizzes() {
        quizRepository.save(new Quiz(3, "C++ Basics", List.of(301, 302)));
        List<Quiz> quizzes = quizRepository.findAll();
        assertFalse(quizzes.isEmpty());
    }
}