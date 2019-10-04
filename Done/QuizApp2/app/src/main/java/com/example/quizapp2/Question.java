package com.example.quizapp2;

public class Question {

    private int res_question;
    private boolean answer;

    public Question(int res_question, boolean answer) {
        this.res_question = res_question;
        this.answer = answer;
    }

    public int getRes_question() {
        return res_question;
    }

    public void setRes_question(int res_question) {
        this.res_question = res_question;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

}
