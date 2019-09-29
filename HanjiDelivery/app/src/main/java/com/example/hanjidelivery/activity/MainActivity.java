package com.example.hanjidelivery.activity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hanjidelivery.R;
import com.example.hanjidelivery.activity.data.AnswerListAsyncResponse;
import com.example.hanjidelivery.activity.data.QuestionBank;
import com.example.hanjidelivery.activity.model.Question;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String SP_SCORE = "SCORE";

    private TextView questionTextView;
    private TextView questionCounterTextView;
    private Button trueButton;
    private Button falseButton;
    private ImageButton prevButton;
    private ImageButton nextButton;
    private TextView tvScore;
    private int score = 0;

    SharedPreferences scoreSaver;
    SharedPreferences.Editor scoreEditor;

    private int currentQuestionIndex = 0;
    private List<Question> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = findViewById(R.id.question_textview);
        questionCounterTextView = findViewById(R.id.counter_text);
        trueButton = findViewById(R.id.trueButton);
        falseButton = findViewById(R.id.falseButton);
        prevButton = findViewById(R.id.prevButton);
        nextButton = findViewById(R.id.nextButton);
        tvScore = findViewById(R.id.tvScore);

        trueButton.setOnClickListener(this);
        falseButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);

        questionList = new QuestionBank().getQuestions(new AnswerListAsyncResponse() {
            @Override
            public void processFinished(ArrayList<Question> questionArrayList) {

                questionTextView.setText(questionArrayList.get(currentQuestionIndex).getAnswer());
                questionCounterTextView.setText((currentQuestionIndex + 1) + " / " + questionArrayList.size());

            }
        });

        //sharedPreference initialization
        scoreSaver = getSharedPreferences(SP_SCORE, MODE_PRIVATE);
        scoreEditor = scoreSaver.edit();

        //맨처음 score
        //Score - 답을 맞출 때마다 score가 10씩 증가.
        SharedPreferences sharedPreferences = getSharedPreferences(SP_SCORE, MODE_PRIVATE);
        int savedScore = sharedPreferences.getInt("score", 0);
        tvScore.setText("Score: " + String.valueOf(savedScore));

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.trueButton:
                if(currentQuestionIndex != 0) {
                    currentQuestionIndex = (currentQuestionIndex + 1) % questionList.size();
                }
                checkAnswer(true);
                updateQuestion();
                break;
            case R.id.falseButton:
                if(currentQuestionIndex != 0) {
                    currentQuestionIndex = (currentQuestionIndex + 1) % questionList.size();
                }
                checkAnswer(false);
                updateQuestion();
                break;
            case R.id.prevButton:
                if(currentQuestionIndex > 0) {
                    currentQuestionIndex = (currentQuestionIndex - 1) % questionList.size();
                }
                updateQuestion();
                break;
            case R.id.nextButton:
                currentQuestionIndex = (currentQuestionIndex + 1) % questionList.size();
                updateQuestion();
                break;
        }

    }

    private void checkAnswer(boolean userChooseCorrect) {
        boolean answerIsTrue = questionList.get(currentQuestionIndex).isAnswerTrue();
        int toastMessageId = 0;
        if(userChooseCorrect == answerIsTrue){
            toastMessageId = R.string.correct_answer;
            score += 10;
            tvScore.setText("Score: " + String.valueOf(score));

            fadeView();
        } else {
            toastMessageId = R.string.wrong_answer;

            if(score > 0) {
                score -= 10;
                tvScore.setText("Score: " + String.valueOf(score));
            }

            shakeAnimation();
        }
        Toast.makeText(MainActivity.this, toastMessageId, Toast.LENGTH_SHORT).show();
    }

    private void updateQuestion() {
        String question = questionList.get(currentQuestionIndex).getAnswer();
        questionTextView.setText(question);
        questionCounterTextView.setText((currentQuestionIndex + 1) + " / " + questionList.size());
    }

    private void fadeView(){
        final CardView cardView = findViewById(R.id.cardView);

        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(350);
        alphaAnimation.setRepeatCount(1);
        alphaAnimation.setRepeatMode(Animation.REVERSE);      //Animation.REVERSE => animation back and forth

        cardView.setAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                cardView.setBackgroundColor(Color.YELLOW);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                cardView.setBackgroundColor(Color.WHITE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                cardView.setBackgroundColor(Color.YELLOW);
            }
        });
    }

    private void shakeAnimation(){
        //Load Animation
        Animation shake = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake_animation);
        //animation을 적용할 widget
        final CardView cardView = findViewById(R.id.cardView);
        //animation 적용
        cardView.setAnimation(shake);

        shake.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                cardView.setCardBackgroundColor(Color.RED);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                cardView.setCardBackgroundColor(Color.WHITE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                cardView.setBackgroundColor(Color.RED);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences sharedPreferences = getSharedPreferences(SP_SCORE, MODE_PRIVATE);
        int savedScore = sharedPreferences.getInt("score", 0);
        if(score > savedScore){
            scoreEditor.putInt("score", score);
            scoreEditor.apply();
        }

    }
}

