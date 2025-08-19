package org.courseWork.controller;

import org.courseWork.model.error.ThereIsNotQuestionError;
import org.courseWork.model.question.Question;
import org.courseWork.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;

@RestController
public class ExamController {
    private final ExaminerService examinerService;


    public ExamController(ExaminerService examinerService, ExaminerServiceImpl examinerServiceImpl, JavaQuestionService questionService, ExaminerServiceImpl questionStorage) {
        this.examinerService = examinerService;

    }

    @Autowired
    private JavaQuestionService javaQuestionService;

    @GetMapping("/")
    public String home() {
        return "Welcome to SkyExaminer!";
    }

    @GetMapping("exam/get/{amount}")
    public Collection<Question> getQuestions(@PathVariable int amount) {

        Collection<Question> allQuestions = javaQuestionService.getAllQuestions();

        if (allQuestions.size() < amount) {
            throw new ThereIsNotQuestionError();
        }

        return examinerService.getQuestions(amount);
    }
}
