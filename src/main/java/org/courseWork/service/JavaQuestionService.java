package org.courseWork.service;

import org.courseWork.model.error.ThereIsNotQuestionError;
import org.courseWork.model.error.ThisQuestionAlreadyExist;
import org.courseWork.model.question.Question;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
    public class JavaQuestionService implements QuestionService{
    private Set<Question> questions = new HashSet<>();
    private Question question;
    private Question currentQuestion;

        public JavaQuestionService(){
            this.questions = createTestQuestions();
        }


        @Override
        public boolean equals(Question question) {
            boolean a = false;
            if (question == null){ a = false;}
            if (this.question.getQuestion() != null && this.question.getQuestion().equals(question.getQuestion()) &&
                    (this.question.getCorrectAnswer() != null && this.question.getCorrectAnswer().equals(question.getCorrectAnswer()))){
                a = true;
            }
            return a;
        }
    public void addQuestion(Question question) {
        //System.out.println("Добавляем вопрос: " + question);
        //System.out.println("Текущие вопросы: " + questions);
        //System.out.println("Содержится ли вопрос? " + questions.contains(question));
        if (questions.contains(question)) {
            throw new ThisQuestionAlreadyExist("Такой вопрос уже существует в коллекции");
        }
        questions.add(question);
       //System.out.println("Вопрос добавлен");
    }

    public void removeQuestion(String questionForRemoving) {

        if (questions == null) {
            throw new ThereIsNotQuestionError();
        }
        questions = questions.stream()
                .filter(q -> !q.getQuestion().contains(questionForRemoving))
                .collect(Collectors.toSet());
    }

    public boolean findQuestion(Question question) {
        return questions.contains(question);
    }

    public Collection<Question> getAllQuestions() {
        return questions;
    }
    @Override
    public String toString(){

        return questions.toString();
    }
    //тестовые вопросы
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
    public Question getRandomQuestion() {

        if (questions == null || questions.isEmpty()) {
            throw new ThereIsNotQuestionError();
        }
        List<Question> randomQuestion = (List<Question>) getAllQuestions();
        Random random = new Random();
        int randomIndex = random.nextInt(randomQuestion.size());

        return randomQuestion.get(randomIndex);
    }

    public void clearQuestions() {
        questions.clear();
    }
    public void setQuestion(Question question) {
        this.currentQuestion = question;
    }

}
