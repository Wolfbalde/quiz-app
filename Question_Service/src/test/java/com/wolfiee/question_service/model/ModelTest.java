package com.wolfiee.question_service.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
public class ModelTest {
    @Test
    public void testQuestionModel() {
        Question question = new Question();
        question.setId(1);
        question.setQuestionTitle("What is Java?");
        question.setOption1("Programming Language");
        question.setOption2("Animal");
        question.setOption3("Food");
        question.setOption4("City");
        question.setRightAnswer("Programming Language");
        question.setDifficultylevel("Easy");
        question.setCategory("Programming");

        assertThat(question.getId()).isEqualTo(1);
        assertThat(question.getQuestionTitle()).isEqualTo("What is Java?");
        assertThat(question.getOption1()).isEqualTo("Programming Language");
        assertThat(question.getRightAnswer()).isEqualTo("Programming Language");
        assertThat(question.getDifficultylevel()).isEqualTo("Easy");
        assertThat(question.getCategory()).isEqualTo("Programming");
    }

    @Test
    public void testResponseModel() {
        Response response = new Response();
        response.setQuestionid(1);
        response.setResponse("Programming Language");

        assertThat(response.getQuestionid()).isEqualTo(1);
        assertThat(response.getResponse()).isEqualTo("Programming Language");
    }

    @Test
    public void testQuestionWrapperModel() {
        QuestionWrapper wrapper = new QuestionWrapper(1, "What is Java?", "Programming Language", "Animal", "Food", "City");

        assertThat(wrapper.getId()).isEqualTo(1);
        assertThat(wrapper.getQuestionTitle()).isEqualTo("What is Java?");
        assertThat(wrapper.getOption1()).isEqualTo("Programming Language");
        assertThat(wrapper.getOption2()).isEqualTo("Animal");
        assertThat(wrapper.getOption3()).isEqualTo("Food");
        assertThat(wrapper.getOption4()).isEqualTo("City");
    }
}
