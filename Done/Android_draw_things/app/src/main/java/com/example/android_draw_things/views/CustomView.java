package com.example.android_draw_things.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CustomView extends View {

    public static final int SQUARE_SIZE = 1000;

    private Rect mRectSquare;
    private Paint mPaintSquare;

    public CustomView(Context context) {
        super(context);

        init(null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
    }

    //자동으로 attributes 값을 넣어서 생성자를 만들 때마다 값이 들어감.
    private void init(@Nullable AttributeSet set){

        mRectSquare = new Rect();
        mPaintSquare = new Paint(Paint.ANTI_ALIAS_FLAG); //안티앨리어싱 설정. Paint 생성자에 이런 식으로 값을 넣을 수 있음.

    }

    public void swapColor(){
        mPaintSquare.setColor(mPaintSquare.getColor() == Color.GREEN ? Color.RED : Color.GREEN);
        //밑에 있는 method들은 기본적으로 onDraw를 호출한다.
        //invalidate(); //Synchronous Process, Block UI before the execution
        postInvalidate(); //Asynchronous Process, Don't block UI before the execution
    }

    //실제로 그림을 그려줄 method
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //🔷배경화면 색깔
        //canvas.drawColor(Color.RED);
        //🔷직사각형
        mRectSquare.left = 100;
        mRectSquare.top = 100;
        mRectSquare.right = mRectSquare.left + SQUARE_SIZE;
        mRectSquare.bottom = mRectSquare.top + SQUARE_SIZE;
        //🔵Paint - Object의 색깔을 지정해줌
        //mPaintSquare.setColor(Color.GREEN);

        canvas.drawRect(mRectSquare, mPaintSquare); //arg1 : 그려줄 모양, arg2 : 속성값. (배경이라던지..)
    }
}
