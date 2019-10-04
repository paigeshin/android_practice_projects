package com.example.mpchart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private BarChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mChart = (BarChart) findViewById(R.id.chart1);
        mChart.getDescription().setEnabled(false);

        setData(10);
        mChart.setFitBars(true);

    }

    private void setData(int count){
        ArrayList<BarEntry> yValues = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();
        labels.add("월");
        labels.add("화");
        labels.add("수");
        labels.add("목");
        labels.add("금");
        labels.add("토");
        labels.add("일");

        for(int i = 0; i < count; i++){
            float value = (float) (Math.random() * 100);
            yValues.add(new BarEntry(i, (int) value));
        }
        BarDataSet set = new BarDataSet(yValues, "Data set");

//        set.setColors(ColorTemplate.MATERIAL_COLORS);
//        set.setDrawValues(true);

        BarData data = new BarData(set);

        mChart.setData(data);
        mChart.invalidate();
        mChart.animateY(500);
    }

}
