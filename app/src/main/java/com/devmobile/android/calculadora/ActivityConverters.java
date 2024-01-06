package com.devmobile.android.calculadora;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.devmobile.android.calculadora.model.CustomEditTextConverter;
import com.devmobile.android.calculadora.model.conversores.ConversorComprimento;
import com.devmobile.android.calculadora.model.conversores.Converter;
import com.devmobile.android.calculadora.model.interfaces.DataInsertEditTextConverter;
import com.devmobile.android.calculadora.model.interfaces.OnItemSpinnerListener;
import com.devmobile.android.calculadora.model.spinner.CustomSpinnerAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

public class ActivityConverters extends Activity
        implements View.OnClickListener
        , OnItemSpinnerListener
        , DataInsertEditTextConverter
        , Serializable {

    private static CustomEditTextConverter mCustomEditTextConverter;
    private static TextView mTextView;
    private static Spinner mSpinner1;
    private static Spinner mSpinner2;
    private Button buttonZero;
    private Button buttonOne;
    private Button buttonTwo;
    private Button buttonThree;
    private Button buttonFour;
    private Button buttonFive;
    private Button buttonSix;
    private Button buttonSeven;
    private Button buttonEight;
    private Button buttonNine;
    private Button buttonClearAll;
    private Button buttonSeparator;
    private ImageButton buttonBackspace;
    private ImageButton buttonMenu;
    private String[] from = {"abbreviation", "name"};
    private int[] to = {R.id.icon_item_text_view, R.id.description_item_text_view};
    private ArrayList<HashMap<String, String>> spinnerItems = new ArrayList<>();
    private int cursorPosition = 0;
    private static Converter converter;
    private static int layoutConverterId = R.layout.base_converter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            layoutConverterId = savedInstanceState.getInt("layoutConverterId");
            setContentView(layoutConverterId);
            initReferences();
            converter = (Converter) savedInstanceState.getSerializable("converter");
            mCustomEditTextConverter.setText(savedInstanceState.getString("custom_editText_text"));
        } else {

            setContentView(layoutConverterId);
            initReferences();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        converter = null;
        mTextView = null;
        mCustomEditTextConverter = null;
        mSpinner1 = null;
        mSpinner2 = null;
        from = null;

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("layoutConverterId", layoutConverterId);
        outState.putSerializable("converter", converter);
        outState.putString("custom_editText_text", mCustomEditTextConverter.getText().toString());
    }

    private void initReferences() {

        Bundle bundle = getIntent().getExtras();
        String className = (String) bundle.get("class_name");
        setConverterType(className);

        mTextView = findViewById(R.id.textViewConverter);
        mSpinner1 = findViewById(R.id.firstSpinner);
        mSpinner2 = findViewById(R.id.secondSpinner);
        mCustomEditTextConverter = findViewById(R.id.editTextViewConverter);
        mCustomEditTextConverter.setTextView(mTextView);

        buttonZero = findViewById(R.id.buttonZero);
        buttonOne = findViewById(R.id.buttonOne);
        buttonTwo = findViewById(R.id.buttonTwo);
        buttonThree = findViewById(R.id.buttonThree);
        buttonFour = findViewById(R.id.buttonFour);
        buttonFive = findViewById(R.id.buttonFive);
        buttonSix = findViewById(R.id.buttonSix);
        buttonSeven = findViewById(R.id.buttonSeven);
        buttonEight = findViewById(R.id.buttonEight);
        buttonNine = findViewById(R.id.buttonNine);
        buttonSeparator = findViewById(R.id.buttonSeparator);
        buttonBackspace = findViewById(R.id.buttonBackSpace);
        buttonClearAll = findViewById(R.id.buttonClearAll);
        buttonMenu = findViewById(R.id.buttonMenu);

        setAdapter();
        setOnClickListener();
    }

    private void setAdapter() {

        CustomSpinnerAdapter spinnerAdapter = new CustomSpinnerAdapter(this, spinnerItems, R.layout.spinner_item_single, from, to);
        mSpinner1.setAdapter(spinnerAdapter);
        mSpinner2.setAdapter(spinnerAdapter);

        // When the Spinner drop Down is show a new layout to each child view
        mSpinner1.setOnItemSelectedListener(spinnerAdapter);
        mSpinner2.setOnItemSelectedListener(spinnerAdapter);
        spinnerAdapter.addOnItemSpinnerSelected(this);
        mCustomEditTextConverter.addDataInsertEditTextConverter(this);
    }

    private void setOnClickListener() {

        View[] buttons = {
                buttonZero, buttonOne, buttonTwo, buttonThree, buttonFour
                , buttonFive, buttonSix, buttonSeven, buttonEight, buttonNine
                , buttonSeparator, buttonBackspace, buttonClearAll, buttonMenu
        };

        Stream.of(buttons).forEach(button -> button.setOnClickListener(this));
    }

    @Override
    public void onClick(View viewButton) {

        if (buttonBackspace == viewButton) {
            excludeCharacter();
        } else if (buttonClearAll == viewButton) {
            expressionClear();
        } else if (buttonMenu == viewButton) {
            acessMenu();
        } else {
            insertTextInEditText(viewButton.getContentDescription().toString());
        }
    }

    private void excludeCharacter() {
//        int cursorPosition = getCursorEnd();

        if (cursorPosition > 0) {

            if (getCursorStart() == getCursorEnd()) {

                mCustomEditTextConverter.getText().delete(cursorPosition - 1, cursorPosition);
//                    mCustomEditTextConverter.setSelection(cursorPosition - 1);
                attCursorPosition();

//                } else {
//                    mCustomEditTextConverter.getText().delete(cursorPosition - 1, cursorPosition);
//                }
            } else {
                mCustomEditTextConverter.getText().delete(getCursorStart(), getCursorEnd());
                attCursorPosition();
            }
        }
    }

    private void insertTextInEditText(String textInput) {
        int attCursorPositionBefore;
        int attCursorPositionNow;

        if (getCustomEditTextSize() > 0 && getTextSize() == 1) {

            mCustomEditTextConverter.setText(mCustomEditTextConverter.getText().toString() + textInput);
//            attCursorPositionNow = mCustomEditTextConverter.length();

            mCustomEditTextConverter.setSelection(mCustomEditTextConverter.getText().length());
            attCursorPosition();
        } else {

            if (mCustomEditTextConverter.getSelectionEnd() == getCustomEditTextSize()) {

                mCustomEditTextConverter.setText(mCustomEditTextConverter.getText().toString() + textInput);
                mCustomEditTextConverter.setSelection(mCustomEditTextConverter.getText().toString().length());
                attCursorPosition();
            } else {

                String textLeftCursor = (String) mCustomEditTextConverter.getText().toString().subSequence(0, getCursorStart());
                String textRightCursor = (String) mCustomEditTextConverter.getText().toString().subSequence(getCursorEnd(), mCustomEditTextConverter.getText().toString().length());
                String allExpressionInput = textLeftCursor + textInput + textRightCursor;

                attCursorPositionBefore = getCursorEnd();
                attCursorPositionNow = attCursorPositionBefore + 1;

                mCustomEditTextConverter.setText(allExpressionInput);
                mCustomEditTextConverter.setSelection(attCursorPositionNow);
                attCursorPosition();
            }
        }
    }

    private void acessMenu() {

        Intent intent = new Intent(this, MenuActivity.class);
        this.startActivity(intent);
    }

    private void expressionClear() {

        mCustomEditTextConverter.setText("");
        mTextView.setText("");
    }

    private void attCursorPosition() {

        cursorPosition = getCustomEditTextSize();
    }

    private int getCursorEnd() {

        return mCustomEditTextConverter.getSelectionEnd();
    }

    private int getCursorStart() {

        return mCustomEditTextConverter.getSelectionStart();
    }

    private int getCustomEditTextSize() {

        return mCustomEditTextConverter.getText().toString().trim().length();
    }

    private int getTextSize() {

        return mTextView.getText().toString().trim().length();
    }

    @Override
    public void spinnerItemSelected(String firstSpinnerItemSelected, Integer idItemFirstSpinner
            , String secondSpinnerItemSelected, Integer idItemSecondSpinner) {

        converter.addSpinnerSelected(
                firstSpinnerItemSelected
                , idItemFirstSpinner
                , secondSpinnerItemSelected
                , idItemSecondSpinner
        );

        addResultInTextView(mCustomEditTextConverter.getText().toString());
    }

    @Override
    public void dataInsertInEditTextToConverter(String dataToConverter) {

        addResultInTextView(mCustomEditTextConverter.getText().toString());
    }

    private void setConverterType(@NonNull String converterClassName) {

        if (converterClassName.equals(ConversorComprimento.class.getName())) {

            // Polimorphism
            converter = ConversorComprimento.getInstance();
            spinnerItems = converter.getTypesToConverter();
        }
    }

    private void addResultInTextView(@NonNull String valueToConvert) {

        if (!valueToConvert.trim().equals("")) {

            try {

                String valueConverted = converter.getValueConverted(valueToConvert);
                mTextView.setText(valueConverted);

            } catch (NumberFormatException e) {

                String exception = "Invalid Expression!";
                mTextView.setText(exception);

                e.printStackTrace();
            }

        }
    }
}
