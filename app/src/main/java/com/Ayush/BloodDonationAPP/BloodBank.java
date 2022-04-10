package com.Ayush.BloodDonationAPP;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class BloodBank extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView A_positive, A_negative, B_positive, B_negative,O_positive, O_negative, AB_positive, AB_negative;
    private PieChart pieChart;
    private Button Read;
    private DatabaseReference userDatabaseRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_bank);

        pieChart = findViewById(R.id.activity_main_piechart);
        setupPieChart();
        loadPieChartData();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Blood Stock Updated");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        A_positive = findViewById(R.id.A_positive);
        A_negative = findViewById(R.id.A_negative);
        B_positive = findViewById(R.id.B_positive);
        B_negative = findViewById(R.id.B_negative);
        O_positive = findViewById(R.id.O_positive);
        O_negative = findViewById(R.id.O_negative);
        AB_positive = findViewById(R.id.AB_positive);
        AB_negative = findViewById(R.id.AB_negative);



        Read = findViewById(R.id.Read);


        userDatabaseRef = FirebaseDatabase.getInstance().getReference()
                .child("BloodBank");

        Read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userDatabaseRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            Map map= (Map) snapshot.getValue();
                            Object A_Positive=map.get("A+");
                            Object A_Negative=map.get("A-");
                            Object B_Positive=map.get("B+");
                            Object B_Negative=map.get("B-");
                            Object O_Positive=map.get("O+");
                            Object O_Negative=map.get("O-");
                            Object AB_Positive=map.get("AB+");
                            Object AB_Negative=map.get("AB-");

                            A_positive.setText(""+A_Positive);
                            A_negative.setText(""+A_Negative);
                            B_positive.setText(""+B_Positive);
                            B_negative.setText(""+B_Negative);
                            O_positive.setText(""+O_Positive);
                            O_negative.setText(""+O_Negative);
                            AB_positive.setText(""+AB_Positive);
                            AB_negative.setText(""+AB_Negative);



                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }

    private void setupPieChart() {
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("Blood Stock in %");
        pieChart.setCenterTextSize(24);
        pieChart.getDescription().setEnabled(false);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(true);
    }

    private void loadPieChartData() {


        ArrayList<PieEntry> entries = new ArrayList<>();

        userDatabaseRef = FirebaseDatabase.getInstance().getReference()
                .child("BloodBank");

        userDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Map map = (Map) snapshot.getValue();
                    Object A_Positive = map.get("A+");
                    Object A_Negative = map.get("A-");
                    Object B_Positive = map.get("B+");
                    Object B_Negative = map.get("B-");
                    Object O_Positive = map.get("O+");
                    Object O_Negative = map.get("O-");
                    Object AB_Positive = map.get("AB+");
                    Object AB_Negative = map.get("AB-");

                    A_positive.setText("" + A_Positive);
                    A_negative.setText("" + A_Negative);
                    B_positive.setText("" + B_Positive);
                    B_negative.setText("" + B_Negative);
                    O_positive.setText("" + O_Positive);
                    O_negative.setText("" + O_Negative);
                    AB_positive.setText("" + AB_Positive);
                    AB_negative.setText("" + AB_Negative);

                    Float a_p=Float.parseFloat((String) A_Positive);
                    Float a_n=Float.parseFloat((String) A_Negative);
                    Float b_p=Float.parseFloat((String) B_Positive);
                    Float b_n=Float.parseFloat((String) B_Negative);
                    Float o_p=Float.parseFloat((String) O_Positive);
                    Float o_n=Float.parseFloat((String) O_Negative);
                    Float ab_p=Float.parseFloat((String) AB_Positive);
                    Float ab_n=Float.parseFloat((String) AB_Negative);


                    entries.add(new PieEntry((Float) a_p, "A+"));
                    entries.add(new PieEntry((Float) a_n, "A-"));
                    entries.add(new PieEntry((Float) b_p, "B+"));
                    entries.add(new PieEntry((Float) b_n, "B-"));
                    entries.add(new PieEntry((Float) o_p, "O+"));
                    entries.add(new PieEntry((Float) o_n, "O-"));
                    entries.add(new PieEntry((Float) ab_p, "AB+"));
                    entries.add(new PieEntry((Float) ab_n, "AB-"));

                    ArrayList<Integer> colors = new ArrayList<>();
                    for (int color : ColorTemplate.MATERIAL_COLORS) {
                        colors.add(color);
                    }

                    for (int color : ColorTemplate.VORDIPLOM_COLORS) {
                        colors.add(color);
                    }

                    PieDataSet dataSet = new PieDataSet(entries, "Blood Stock");
                    dataSet.setColors(colors);

                    PieData data = new PieData(dataSet);
                    data.setDrawValues(true);
                    data.setValueFormatter(new PercentFormatter(pieChart));
                    data.setValueTextSize(12f);
                    data.setValueTextColor(Color.BLACK);

                    pieChart.setData(data);
                    pieChart.invalidate();

                    pieChart.animateY(1400, Easing.EaseInOutQuad);

                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
        
    

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }
}

