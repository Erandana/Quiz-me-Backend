package com.quizme.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;

@Getter
@Setter
public class QuizUpdatePayload {

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

    public String getAuthorId() {
        return authorId;
    }

    public String getName() {
        return name;
    }

    public String getSharableLinkOrId() {
        return sharableLinkOrId;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getDuration() {
        return duration;
    }

    public ArrayList<Problem> getProblems() {
        return problems;
    }

    public int getNoOfProblems() {
        return noOfProblems;
    }
}
