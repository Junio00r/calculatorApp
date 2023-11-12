package com.devmobile.android.calculadora;

import android.app.LocaleConfig;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.apache.logging.log4j.spi.ObjectThreadContextMap;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private ImageButton aiButton;
    private ImageButton lengthConversion;
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
        lengthConversion = findViewById(R.id.lengthConversion);
        imcButton = findViewById(R.id.imcConversion);
        weightButton = findViewById(R.id.weightConversion);
        potencyButton = findViewById(R.id.potencyConversion);
        pressureButton = findViewById(R.id.pressureConversion);
        temperatureButton = findViewById(R.id.temperatureConversion);
        distanceTimeButton = findViewById(R.id.distanceTime);

        setOnClickButtonListener();
    }

    private void setOnClickButtonListener() {
        View[] buttons = {aiButton, lengthConversion, weightButton, potencyButton, pressureButton, temperatureButton, distanceTimeButton};

        for (View e : buttons) e.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == aiButton) {

        } else if (v == lengthConversion) {

            initConverterClass(ConversorComprimento.class);
        } else if (v == imcButton) {


        } else if (v == weightButton) {


        } else if (v == potencyButton) {


        } else if (v == pressureButton) {


        } else if (v == temperatureButton) {


        } else {

        }
    }

    private void initConverterClass(Class<?> fromClass) {
        Bundle bundle = new Bundle();

        bundle.putString("class_name", fromClass.getName());
        initConverterActivity(bundle);
    }

    private void initConverterActivity(Bundle bundleWithParcelable) {

        Intent intent = new Intent(this, ActivityConverters.class);
        intent.putExtras(bundleWithParcelable);

        startActivity(intent);
    }
}
