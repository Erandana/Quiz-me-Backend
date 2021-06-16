package com.quizme.Controller;

import com.quizme.Model.Quiz;
import com.quizme.Model.QuizUpdatePayload;
import com.quizme.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    private QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/all")
    public Collection<Quiz> findAll(){
        return quizService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Quiz> findById(@PathVariable("id") String id){
        return quizService.findById(id);
    }

    @PostMapping("/add")
    public String addQuiz(@Valid @RequestBody Quiz quiz){
        return quizService.addQuiz(quiz);
    }

    @PutMapping("/update/{id}")
    public String updateQuiz(@PathVariable("id")String id, @RequestBody QuizUpdatePayload quizUpdatePayload){
        return quizService.updateQuiz(id,quizUpdatePayload);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteQuizById(@PathVariable("id")String id){
        return quizService.deleteQuizById(id);
    }

    @PutMapping("/attend")
    public String onAttendQuiz(@RequestParam(name = "quizid")String quizId,@RequestParam(name = "studentid")String studentid){
        return quizService.onAttendQuiz(quizId,studentid);
    }

    @PutMapping("/submit")
    public String onSubmitQuiz(@RequestParam(name = "quizid")String quizId,
                               @RequestParam(name = "studentid")String studentid,
                               @RequestParam(name = "marks")int marks){
        return quizService.onSubmitQuiz(quizId,studentid,marks);
    }
}
