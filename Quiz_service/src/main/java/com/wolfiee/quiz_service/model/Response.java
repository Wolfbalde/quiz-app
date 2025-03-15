package com.wolfiee.quiz_service.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    private Integer questionid;
    private String response;


    public Integer getQuestionid() {
        return questionid;
    }

    public Response(Integer questionid, String response) {
        this.questionid = questionid;
        this.response = response;
    }

    public void setQuestionid(Integer questionid) {
        this.questionid = questionid;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
