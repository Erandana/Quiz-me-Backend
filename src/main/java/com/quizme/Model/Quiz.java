package com.quizme.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.HashMap;

@Document(collection = "Quiz")
public class Quiz {

    @Id
    private String id;

    @NotNull(message = "Author cannot be Null")
    @NotBlank(message = "Author cannot be Blank")
    private String authorId;

    @NotNull(message = "Quiz name cannot be Null")
    @NotBlank(message = "Quiz name cannot be Blank")
    private String name;

    @NotNull(message = "Quid link cannot be Null")
    @NotBlank(message = "Quiz link cannot be Blank")
    private String sharableLinkOrId;

    @NotNull(message = "Start date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @Pattern(regexp = "\\b\\d\\d\\d\\d-\\d\\d-\\d\\d\\b",message = "startDate is not in correct Format")
    private String startDate;

    @NotNull(message = "Start Time cannot be Null")
    @JsonFormat(pattern = "HH:mm",shape = JsonFormat.Shape.STRING)
    @Pattern(regexp = "\\b\\d\\d:\\d\\d\\b",message = "startTime is not in Correct Format")
    private String startTime;

    private String duration;

    @NotNull(message = "Problems cannot be Null")
    private ArrayList<@Valid Problem> problems = new ArrayList<>();

    @NotNull(message = "No of Problems cannot be Null")
    @Min(value = 1,message = "Minimum value of Problems is 1")
    private int noOfProblems;

    private HashMap<String,Integer> studentMarksList = new HashMap<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSharableLinkOrId() {
        return sharableLinkOrId;
    }

    public void setSharableLinkOrId(String sharableLinkOrId) {
        this.sharableLinkOrId = sharableLinkOrId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public ArrayList<Problem> getProblems() {
        return problems;
    }

    public void setProblems(ArrayList<Problem> problems) {
        this.problems = problems;
    }

    public int getNoOfProblems() {
        return noOfProblems;
    }

    public void setNoOfProblems(int noOfProblems) {
        this.noOfProblems = noOfProblems;
    }

    public void addStudentToQuiz(String studentId){
        this.studentMarksList.put(studentId,0);
    }

    public void updateFinalMarks(String studentId,int marks){
        this.studentMarksList.put(studentId,marks);
    }
}
