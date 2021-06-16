package com.quizme.Model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@Getter
@Setter
public class Problem {

    @NotNull(message = "Question cannot be Null")
    @NotBlank(message = "Question cannot be Blank")
    private String question;

    @NotNull(message = "Answers cannot be Null")
    private ArrayList<String> answers;

    private LinkedHashMap<String,Boolean> answersMap;

    @NotNull(message = "Correct Answer Number cannot be Null")
    @Min(value = 1,message = "Minimum index of correct answer is 1")
    private int correctAnswer;//1 based

    @NotNull(message = "Question cannot be Null")
    @NotBlank(message = "Question cannot be Blank")
    private String image_url;

    private String description;

    public Problem(String question,ArrayList<String> answers, int correctAnswer, String image_url, String description) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
        this.image_url = image_url;
        this.description = description;
        init();
    }

    private void init() {
        int index = 1;
        this.answersMap = new LinkedHashMap<>();
        for(String s:this.answers){
            if(this.correctAnswer == index){
                this.answersMap.put(s,true);
            }
            else {
                this.answersMap.put(s,false);
            }
            index++;
        }
    }
}
