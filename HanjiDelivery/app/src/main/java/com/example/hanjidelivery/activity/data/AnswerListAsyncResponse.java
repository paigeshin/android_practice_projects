package com.example.hanjidelivery.activity.data;

import com.example.hanjidelivery.activity.model.Question;

import java.util.ArrayList;

public interface AnswerListAsyncResponse {

    //Callback Function으로 이용하기 위해서..
    void processFinished(ArrayList<Question> questionArrayList);

}
