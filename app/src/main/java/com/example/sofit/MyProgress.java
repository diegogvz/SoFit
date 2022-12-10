package com.example.sofit;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.example.sofit.data.ProgressDataSource;
import com.example.sofit.data.UserDataSource;
import com.example.sofit.model.ModelProgress;
import com.google.android.material.tabs.TabItem;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.Series;

import java.util.ArrayList;
import java.util.List;

public class MyProgress extends BaseActivity {

    // creating an attribute
    // for our graph view.
    GraphView graphView;
    List<ModelProgress> listOfData=new ArrayList<>();
    List<DataPoint> dataPoints=new ArrayList<>();
    private TabItem tabWeight;
    private TabItem tabFat;
    private TabItem tabWater;
    private TabItem tabMuscle;

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_progress);
        setTitle("My Progress");
        createDrawer(this);

        //Get data from database and store it to array attribute
        getProgressData();


        //Get views
        Button b = findViewById(R.id.my_progress_add_data);
        graphView = findViewById(R.id.idGraphView);
        //Tab views
        tabWeight=findViewById(R.id.TabItem_progress_weight);
        tabFat=findViewById(R.id.TabItem_progress_fat);
        tabMuscle=findViewById(R.id.TabItem_progress_muscle);
        tabWater=findViewById(R.id.TabItem_progress_water);

        //Initialize the graph view
        prepareGraph();

        //Set listeners
        b.setOnClickListener(
                view -> startActivity(new Intent(MyProgress.this, AddDataForToday.class))
        );
        //Tab listeners
        tabWeight.setOnClickListener(view ->{
            selectWeightTab();
        });
        tabFat.setOnClickListener(view ->{
            selectFatTab();
        });
        tabMuscle.setOnClickListener(view ->{
            selectMuscleTab();
        });
        tabWater.setOnClickListener(view ->{
            selectWaterTab();
        });




    }
    private void prepareGraph(){
        // after adding data to our line graph series.
        // on below line we are setting
        // title for our graph view.
        graphView.setTitle("My Graph View");

        // on below line we are setting
        // text color to our graph view.
        graphView.setTitleColor(R.color.purple_200);

        // on below line we are setting
        // our title text size.
        graphView.setTitleTextSize(18);
    }
    private void selectWeightTab(){
        graphView.removeAllSeries();
        dataPoints=new ArrayList<>();

        for (int i=0;i<listOfData.size();i++) {
            dataPoints.add(new DataPoint(listOfData.get(i).getWeight(),i));
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>((DataPoint[]) dataPoints.toArray());
        // on below line we are adding
        // data series to our graph view.
        graphView.addSeries(series);
    }
    private void selectFatTab(){
        graphView.removeAllSeries();
        dataPoints=new ArrayList<>();
        for (int i=0;i<listOfData.size();i++) {
            dataPoints.add(new DataPoint(listOfData.get(i).getFat(),i));
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>((DataPoint[]) dataPoints.toArray());
        // on below line we are adding
        // data series to our graph view.
        graphView.addSeries(series);
    }
    private void selectWaterTab(){
        graphView.removeAllSeries();
        dataPoints=new ArrayList<>();
        for (int i=0;i<listOfData.size();i++) {
            dataPoints.add(new DataPoint(listOfData.get(i).getWater(),i));
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>((DataPoint[]) dataPoints.toArray());
        // on below line we are adding
        // data series to our graph view.
        graphView.addSeries(series);
    }
    private void selectMuscleTab(){
        graphView.removeAllSeries();
        dataPoints=new ArrayList<>();
        for (int i=0;i<listOfData.size();i++) {
            dataPoints.add(new DataPoint(listOfData.get(i).getMuscle(),i));
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>((DataPoint[]) dataPoints.toArray());
        // on below line we are adding
        // data series to our graph view.
        graphView.addSeries(series);
    }
    private void getProgressData(){
        ProgressDataSource progressDataSource = new ProgressDataSource(getApplicationContext());
        progressDataSource.open();
        listOfData=progressDataSource.getProgressData();
        progressDataSource.close();
    }
}