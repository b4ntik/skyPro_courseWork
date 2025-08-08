package org.courseWork.controller;

import org.courseWork.model.error.ThereIsNotQuestionError;
import org.courseWork.model.question.Question;
import org.courseWork.service.*;
//import org.courseWork.service.ExaminerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
public class ExamController {
    private final ExaminerService examinerService;
    private final ExaminerServiceImpl examineserviceImpl;
    private final JavaQuestionService questionService;
    private final ExaminerServiceImpl questionStorage;

    public ExamController(ExaminerService examinerService, ExaminerServiceImpl examinerServiceImpl, JavaQuestionService questionService, ExaminerServiceImpl questionStorage) {
        this.examinerService = examinerService;
        this.examineserviceImpl = examinerServiceImpl;
        this.questionService = questionService;
        this.questionStorage = questionStorage;
    }

    @Autowired
    private JavaQuestionService javaQuestionService;

    @GetMapping("/")
    public String home() {
        return "Welcome to SkyExaminer!";
    }

    @GetMapping("exam/get/{amount}")
    public Collection<Question> getRandomQuestions(@PathVariable int amount) {

      Collection<Question> allQuestions = javaQuestionService.getCollectionsOfQuestions();

        if (allQuestions.size() < amount) {
           throw new ThereIsNotQuestionError();
        }

        return javaQuestionService.getRandomQuestion(amount);
    }

    @GetMapping("exam/java/add")
    public void addQuestion(@RequestParam String text, @RequestParam String answer){
        Question question = new Question();
        question.setQuestion(text);
        question.setAnswer(answer);
        questionService.addQuestion(question);

    }
    @GetMapping("exam/java/remove")
    public void removeQuestions(@RequestParam String text){

    }
}
