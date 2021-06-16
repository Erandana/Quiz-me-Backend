package com.quizme.Service;

import com.quizme.Model.Quiz;
import com.quizme.Model.QuizUpdatePayload;
import com.quizme.Repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class QuizService {

    private QuizRepository quizRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public Collection<Quiz> findAll() {
        return quizRepository.findAll();
    }

    public String addQuiz(Quiz quiz) {
        quizRepository.save(quiz);
        return "Quiz Created Successfully";
    }

    public Optional<Quiz> findById(String id) {
        return quizRepository.findById(id);
    }

    public String updateQuiz(String id, QuizUpdatePayload quizUpdatePayload) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        quiz.ifPresent(q -> {
            q.setAuthorId(quizUpdatePayload.getAuthorId());
            q.setName(quizUpdatePayload.getName());
            q.setSharableLinkOrId(quizUpdatePayload.getSharableLinkOrId());
            q.setStartDate(quizUpdatePayload.getStartDate());
            q.setStartTime(quizUpdatePayload.getStartTime());
            q.setDuration(quizUpdatePayload.getDuration());
            q.setProblems(quizUpdatePayload.getProblems());
            q.setNoOfProblems(quizUpdatePayload.getNoOfProblems());
            quizRepository.save(q);
        });
        return "Quiz successfully updated";
    }

    public String deleteQuizById(String id) {
        quizRepository.deleteById(id);
        return "Successfully Deleted";
    }

    public String onAttendQuiz(String quizId, String studentid) {
        Optional<Quiz> quiz = quizRepository.findById(quizId);
        quiz.ifPresent(q -> {
            //TODO : Check whether student
            //TODO : Check whether studentid or username or name is more suitable
            q.addStudentToQuiz(studentid);
            quizRepository.save(q);
        });
        return "Attended Quiz Successfully";
    }

    public String onSubmitQuiz(String quizId, String studentid, int marks) {
        Optional<Quiz> quiz = quizRepository.findById(quizId);
        quiz.ifPresent(q -> {
            //TODO : Check whether student
            //TODO : Check whether studentId or username or name is more suitable
            q.updateFinalMarks(studentid,marks);
            quizRepository.save(q);
        });
        return "Submitted Quiz Successfully";
    }
}
