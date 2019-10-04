package com.example.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button trueButton;
    private Button falseButton;
    private ImageButton nextButton;
    private TextView questionTextView;

    //loop through questions
    private int currentQuestionIndex = 0;

    private Question[] questionBank = new Question[]{
         new Question(R.string.programming_text_question, true),
         new Question(R.string.exercise_text_question, true),
         new Question(R.string.dropshipping_text_question, true)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        questionTextView = findViewById(R.id.answer_text_view);

        trueButton.setOnClickListener(this);
        falseButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.true_button:
                checkAnswer(true);
                break;
            case R.id.false_button:
                checkAnswer(false);
                break;
            case R.id.next_button:
                //go to next question
                if(currentQuestionIndex < questionBank.length) {
                    updateQuestion();
                }
                break;
        }
    }

    private void updateQuestion(){
        questionTextView.setText(questionBank[currentQuestionIndex].getAnswerResId());
        currentQuestionIndex++;
    }

    private void checkAnswer(boolean userChooseCorrect){
        boolean answerIsTrue = questionBank[currentQuestionIndex].isAnswerTrue();
        if(answerIsTrue == userChooseCorrect){
            Toast.makeText(getApplicationContext(), R.string.correct_answer, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), R.string.wrong_answer, Toast.LENGTH_LONG).show();
        }
    }

}
