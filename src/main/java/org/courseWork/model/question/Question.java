package org.courseWork.model.question;

import java.util.Objects;

public class Question {
    private String question;
    private String correctAnswer;
    private String userAnswer;

    public Question() {

    }

    public Question(String question, String correctAnswer) {
        this.question = question;
        this.correctAnswer = correctAnswer;
    }

    //добавляем вопрос
    public void addQuestion(String question, String answer) {
        this.question = question;
        this.correctAnswer = answer;
    }

    //меняем вопрос
    public void setQuestion(String question) {
        this.question = question;
    }

    //меняем ответ
    public void setAnswer(String answer) {
        this.correctAnswer = correctAnswer;
    }
    public void serUserAnswer(String userAnswer){
        this.userAnswer = userAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
    public String getUserAnswer(){
        return userAnswer;
    }

    //получить рандомный вопрос
    public String getRandomQuestion() {
        return "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;
        Question someQuestion = (Question) o;
        return Objects.equals(question, someQuestion.question) && Objects.equals(correctAnswer, someQuestion.correctAnswer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, correctAnswer);
    }

    public void setUserAnswer(String answer) {
        this.userAnswer = answer;
    }
    @Override
    public String toString() {
        return "Question{question='" + question + "', correctAnswer='" + correctAnswer + "'}";
    }
}
