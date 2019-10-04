package com.example.barchart_practice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //BarChart Object 생성
    private BarChart chart;
    //집어 넣을 데이터를 담을 어레이리스트
    private ArrayList<BarEntry> barEntry;
    //라벨
    private ArrayList<String> barEntryLabels;
    //실제 데이터 셋
    private BarDataSet barDataSet;
    //데이터 가장 작은 단위
    private BarData barData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //🔷initialize field value

        //🔵 chart, bind data
        chart = (BarChart) findViewById(R.id.chart);

        //🔵 barEntry, add values
        barEntry = new ArrayList<>();
        barEntry.add(new BarEntry(1, 0));
        barEntry.add(new BarEntry(2, 1));
        barEntry.add(new BarEntry(3, 2));
        barEntry.add(new BarEntry(1, 3));
        barEntry.add(new BarEntry(2, 4));

        //🔵 Label, add labels
        barEntryLabels = new ArrayList<>();
        barEntryLabels.add("월");
        barEntryLabels.add("화");
        barEntryLabels.add("수");
        barEntryLabels.add("목");
        barEntryLabels.add("금");

        /*
        🔵 BarDataSet, 여기다가
        arg1은 DataEntry를 포함한 어레이리스트 들을 집어넣는다.
        arg2에는 DataSet의 이름을 지정해준다.
        각각의 데이터를 표현해주는 bar마다 속성 값을 지정할 수 있음. example) 색깔.
         */
        barDataSet = new BarDataSet(barEntry, "Project");

        /*
        🔵 BarData, 여기다가
        arg1에는 label을 포함란 어레이리스트를 넣어준다.
        arg2에는 dataSet을 넣어준다.
         */
        barData = new BarData(barEntryLabels, barDataSet);

        //🔵 마지막으로 chart에 데이터와 애니메이션을 추가해준다.
        chart.setData(barData);
        chart.animateY(3000);


    }
}
