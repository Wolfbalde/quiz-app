package com.wolfiee.question_service.dao;
import com.wolfiee.question_service.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class questiondaotest {
    @Autowired
    private questiondao questionRepo;

    private Question question1, question2;

    @BeforeEach
    void setUp() {

        question1 = new Question();
        question1.setQuestionTitle("What is Java?");
        question1.setOption1("Programming Language");
        question1.setOption2("Animal");
        question1.setOption3("Food");
        question1.setOption4("City");
        question1.setRightAnswer("Programming Language");
        question1.setDifficultylevel("Easy");
        question1.setCategory("Programming");
        questionRepo.save(question1);


        question2 = new Question();
        question2.setQuestionTitle("What is Python?");
        question2.setOption1("Snake");
        question2.setOption2("Programming Language");
        question2.setOption3("Book");
        question2.setOption4("City");
        question2.setRightAnswer("Programming Language");
        question2.setDifficultylevel("Medium");
        question2.setCategory("Programming");
        questionRepo.save(question2);
    }

    @Test
    void testFindByCategory() {
        List<Question> questions = questionRepo.findByCategory("Programming");
        assertFalse(questions.isEmpty());
        assertEquals(2, questions.size());
    }

    @Test
    void testFindRandomQbyCategory() {
        List<Integer> questionIds = questionRepo.findRandomQbyCategory("Programming", 1);
        assertFalse(questionIds.isEmpty());
        assertEquals(1, questionIds.size());
    }
}
