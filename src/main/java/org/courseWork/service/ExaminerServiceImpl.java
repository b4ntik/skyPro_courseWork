package org.courseWork.service;

import org.courseWork.model.error.ThereIsNotQuestionError;
import org.courseWork.model.error.ThisQuestionAlreadyExist;
import org.courseWork.model.question.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService{
    private Set<Question> questions = new HashSet<>();
    private Random random = new Random();

    public ExaminerServiceImpl(){
        this.questions = createTestQuestions();
    }

    public void addQuestion(Question question) {
        if (questions.contains(question)) {
            throw new ThisQuestionAlreadyExist("Такой вопрос уже существует в коллекции");
        }
        questions.add(question);
    }

    public Question getRandomQuestion() {
        if (questions == null || questions.isEmpty()) {
            throw new ThereIsNotQuestionError();
        }

        int index = random.nextInt(questions.size());
        int current = 0;
        for (Question question : questions) {
            if (current == index) {
                return question;
            }
            current++;
        }
        // Теоретически сюда не дойдём, но чтобы компилятор не ругался:
        throw new ThereIsNotQuestionError();
    }

    public void removeQuestion(Question question) {

        if (!questions.contains(question)) {
            throw new ThisQuestionAlreadyExist("Такой вопрос отсутствует в коллекции");
        }
        questions.remove(question);
    }

    public boolean findQuestion(Question question){
        return questions.contains(question);
    }

    @Override
    public boolean checkCorrectAnswer() {
        return false;
    }

    @Override
    public boolean equals(Question question) {
        return false;
    }

    public Collection<Question> getCollectionsOfQuestions() {

        return questions;
    }

    public Set<Question> createTestQuestions() {
        Set<Question> questions = new HashSet<>();
        Question q1 = new Question();
        q1.addQuestion("Столица Великобритании?", "Лондон");
        questions.add(q1);
        Question q2 = new Question();
        q2.addQuestion("Сколько будет 2+2?", "4");
        questions.add(q2);
        Question q3 = new Question();
        q3.addQuestion("Кто написал 'Идиот'? (Фамилия автора)", "Достоевский");
        questions.add(q3);

        return questions;
    }
}
