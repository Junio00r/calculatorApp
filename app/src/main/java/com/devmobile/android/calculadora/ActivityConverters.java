package com.devmobile.android.calculadora;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
import java.util.ArrayList;
import java.util.HashMap;

public class ActivityConverters extends Activity
        implements View.OnClickListener
        , OnItemSpinnerListener
        , DataInsertEditTextConverter {

    private CustomEditTextConverter mCustomEditTextConverter;
    private TextView mTextView;
    private Spinner mSpinner1;
    private Spinner mSpinner2;
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
    private final String[] from = {"abbreviation", "name"};
    private final int[] to = {R.id.icon_item_text_view, R.id.description_item_text_view};
    private ArrayList<HashMap<String, String>> spinnerItems = new ArrayList<>();
    private Converter converter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_converter);

        initReferences();
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

        for (View e : buttons) e.setOnClickListener(this);
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
        int cursorPosition = getCursorEnd();

        if (cursorPosition > 0) {
            if (getCursorStart() == getCursorEnd()) {
                if (getCursorStart() < getCustomEditTextSize()
                        && getCursorStart() > 0) {

                    mCustomEditTextConverter.getText().delete(cursorPosition - 1, cursorPosition);
                    mCustomEditTextConverter.setSelection(cursorPosition - 1);

                } else {
                    mCustomEditTextConverter.getText().delete(cursorPosition - 1, cursorPosition);
                }
            } else {
                mCustomEditTextConverter.getText().delete(getCursorStart(), getCursorEnd());
            }
        }
    }

    private void insertTextInEditText(String textInput) {
        int attCursorPositionBefore;
        int attCursorPositionNow;

        if (getCustomEditTextSize() > 0 && getTextSize() == 1) {

            mCustomEditTextConverter.append(textInput);
            attCursorPositionNow = mCustomEditTextConverter.getText().length();

            mCustomEditTextConverter.setSelection(attCursorPositionNow);
        } else {

            if (mCustomEditTextConverter.getSelectionEnd() == getCustomEditTextSize()) {

                mCustomEditTextConverter.append(textInput);
                mCustomEditTextConverter.setSelection(mCustomEditTextConverter.getText().toString().length());
            } else {

                String textLeftCursor = (String) mCustomEditTextConverter.getText().toString().subSequence(0, getCursorStart());
                String textRightCursor = (String) mCustomEditTextConverter.getText().toString().subSequence(getCursorEnd(), mCustomEditTextConverter.getText().toString().length());
                String allExpressionInput = textLeftCursor + textInput + textRightCursor;

                attCursorPositionBefore = getCursorEnd();
                attCursorPositionNow = attCursorPositionBefore + 1;

                mCustomEditTextConverter.append(allExpressionInput);
                mCustomEditTextConverter.setSelection(attCursorPositionNow);
//                mTextView.append(allExpressionInput);
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

            }  catch (NumberFormatException e) {

                String exception = "Invalid Expression!";
                mTextView.setText(exception);

                e.printStackTrace();
            }

        }
    }
}
