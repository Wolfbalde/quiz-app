package com.wolfiee.quiz_service.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quizdto {


    @JsonProperty("category")
    private String category;

    @JsonProperty("numq")
    private Integer numq;

    @JsonProperty("title")
    private String title;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getNumq() {
        return numq;
    }

    public void setNumq(Integer numq) {
        this.numq = numq;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
