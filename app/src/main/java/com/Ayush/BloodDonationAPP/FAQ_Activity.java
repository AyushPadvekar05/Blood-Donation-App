package com.Ayush.BloodDonationAPP;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.animation.LayoutTransition;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FAQ_Activity extends AppCompatActivity {

    private Toolbar toolbar;

    private TextView Ans1,Ans2,Ans3,Ans4,Ans5,Ans6,Ans7;
    private LinearLayout Q1,Q2,Q3,Q4,Q5,Q6,Q7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);


        Ans1 = findViewById(R.id.Ans1);
        Q1 = findViewById(R.id.Q1);
        Q1.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);

        Ans2 = findViewById(R.id.Ans2);
        Q2 = findViewById(R.id.Q2);
        Q2.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);

        Ans3 = findViewById(R.id.Ans3);
        Q3 = findViewById(R.id.Q3);
        Q3.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);

        Ans4 = findViewById(R.id.Ans4);
        Q4 = findViewById(R.id.Q4);
        Q4.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);

        Ans5 = findViewById(R.id.Ans5);
        Q5 = findViewById(R.id.Q5);
        Q5.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);

        Ans6 = findViewById(R.id.Ans6);
        Q6 = findViewById(R.id.Q6);
        Q6.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);

        Ans7 = findViewById(R.id.Ans7);
        Q7 = findViewById(R.id.Q7);
        Q7.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);






        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("FAQ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);




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
    @RequiresApi(api = Build. VERSION_CODES.KITKAT)

    public void Q1(View view) {
        int one = (Ans1.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE;
        TransitionManager.beginDelayedTransition(Q1, new AutoTransition());
        Ans1.setVisibility(one);
    }
    public void Q2(View view) {
        int two = (Ans2.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE;
        TransitionManager.beginDelayedTransition(Q2, new AutoTransition());
        Ans2.setVisibility(two);
    }
    public void Q3(View view) {
        int three = (Ans3.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE;
        TransitionManager.beginDelayedTransition(Q3, new AutoTransition());
        Ans3.setVisibility(three);
    }
    public void Q4(View view) {
        int four = (Ans4.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
        TransitionManager.beginDelayedTransition(Q4, new AutoTransition());
        Ans4.setVisibility(four);
    }
    public void Q5(View view) {
        int five = (Ans5.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
        TransitionManager.beginDelayedTransition(Q5, new AutoTransition());
        Ans5.setVisibility(five);
    }
    public void Q6(View view) {
        int six = (Ans6.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
        TransitionManager.beginDelayedTransition(Q6, new AutoTransition());
        Ans6.setVisibility(six);
    }
    public void Q7(View view) {
        int seven = (Ans7.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
        TransitionManager.beginDelayedTransition(Q7, new AutoTransition());
        Ans7.setVisibility(seven);
    }

}