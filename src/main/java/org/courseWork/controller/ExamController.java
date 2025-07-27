package org.courseWork.controller;

import org.courseWork.model.question.Question;
import org.courseWork.service.ExaminerService;
import org.courseWork.service.ExaminerServiceImpl;
import org.courseWork.service.QuestionService;
import org.courseWork.service.QuestionStorage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ExamController {
    private final ExaminerService examinerService;
    private final ExaminerServiceImpl examineserviceImpl;
    private final QuestionService questionService;
    private final QuestionStorage questionStorage;

    public ExamController(ExaminerService examinerService, ExaminerServiceImpl examineserviceImpl, QuestionService questionService, QuestionStorage questionStorage) {
        this.examinerService = examinerService;
        this.examineserviceImpl = examineserviceImpl;
        this.questionService = questionService;
        this.questionStorage = questionStorage;
    }
    @GetMapping("/")
    public String home() {
        return "Welcome to SkyExaminer!";
    }

    @GetMapping("exam/get/{amount}")
    public Collection<Question> getRandomQuestions(){
        return questionStorage.getCollectionsOfQuestions();
    }
}
