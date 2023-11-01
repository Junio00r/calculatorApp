package com.devmobile.android.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.devmobile.android.calculadora.MainActivity;
import com.devmobile.android.calculadora.R;

import java.util.Arrays;

public class MenuActivity extends AppCompatActivity
        implements View.OnClickListener {

    private ImageButton aiButton;
    private ImageButton imcButton;
    private ImageButton weightButton;
    private ImageButton potencyButton;
    private ImageButton pressureButton;
    private ImageButton temperatureButton;
    private ImageButton distanceTimeButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        init();
    }

    private void init() {
        aiButton = findViewById(R.id.aiFunction);
        imcButton = findViewById(R.id.imcConversion);
        weightButton = findViewById(R.id.weightConversion);
        potencyButton = findViewById(R.id.potencyConversion);
        pressureButton = findViewById(R.id.pressureConversion);
        temperatureButton = findViewById(R.id.temperatureConversion);
        distanceTimeButton = findViewById(R.id.distanceTime);
    }

    private void setOnClickButtonListener() {
        View[] buttons = {
                aiButton, imcButton, weightButton, potencyButton
                , pressureButton, temperatureButton, distanceTimeButton
        };

        for (View e : buttons) e.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == aiButton) {

        } else if (v == imcButton) {

        } else if (v == weightButton) {

        } else if (v == potencyButton) {

        } else if (v == pressureButton) {

        } else if (v == temperatureButton) {

        } else {

        }
    }
}
