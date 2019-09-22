package com.example.quizapp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView question;
    private Button btnTrue, btnFalse;
    private ImageButton btnNext, btnPrevious;

    private Question[] questions = new Question[]{
      new Question(R.string.first_question, true),
      new Question(R.string.second_question, true),
      new Question(R.string.third_question, true)
    };
    private int currentPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        question = findViewById(R.id.question_delaration_textview);
        btnTrue = findViewById(R.id.true_text);
        btnFalse = findViewById(R.id.false_text);
        btnNext = findViewById(R.id.next_button);
        btnPrevious = findViewById(R.id.previous_button);

        btnTrue.setOnClickListener(this);
        btnFalse.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnPrevious.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.true_text:
                try {
                    userChooseAnswer(true);
                    break;
                } catch (Exception e){
                    Toast.makeText(this, "Click on to \'next button\'", Toast.LENGTH_LONG).show();
                }
            case R.id.false_text:
                try {
                    userChooseAnswer(false);
                } catch (Exception e){
                    Toast.makeText(this, "Click on to \'next button\'", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.next_button:
                getQuestion();
                break;
            case R.id.previous_button:
                getPreviousQuestion();
                break;
        }

    }

    private void getQuestion() {
        if(currentPosition < questions.length - 1){
            currentPosition++;
            question.setText(questions[currentPosition].getRes_question());
        }
    }

    private void getPreviousQuestion() {
        if(currentPosition > 0){
            currentPosition--;
            question.setText(questions[currentPosition].getRes_question());
        }
    }

    private void userChooseAnswer(boolean answer){
        String newString = "";
        if(questions[currentPosition].isAnswer() == answer){
            newString="Correct!";
        } else {
            newString="Wrong!";
        }
        Toast.makeText(this, newString, Toast.LENGTH_LONG).show();
    }
}
