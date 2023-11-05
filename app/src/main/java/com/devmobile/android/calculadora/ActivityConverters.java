package com.devmobile.android.calculadora;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.devmobile.android.calculadora.model.CustomEditTextView;
import com.devmobile.android.calculadora.model.constantesTiposConversao.comprimento.TipoComprimento;
import com.devmobile.android.calculadora.model.spinner.CustomSpinner;
import com.devmobile.android.calculadora.model.spinner.SpinnerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class ActivityConverters extends Activity
        implements View.OnClickListener {
    private CustomEditTextView mCustomEditTextView;
    private TextView mTextView;
    private CustomSpinner mSpinner1;
    private Spinner mSpinner2;
    private SpinnerAdapter spinnerAdapter;

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
    private static ArrayList<HashMap<String, String>> SPINNERS_ITEMS = new ArrayList<>();
    private final String[] from = {"abbreviation", "name"};
    private final int[] to =  { R.id.icon_item_text_view, R.id.description_item_text_view };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_converter);

        initReferences();
    }

    private void initReferences() {

        mSpinner1 = findViewById(R.id.firstSpinner);
        mSpinner2 = findViewById(R.id.secondSpinner);
        mCustomEditTextView = findViewById(R.id.editTextViewID);
        mTextView = findViewById(R.id.textViewConverter);

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

        spinnerAdapter = new SpinnerAdapter(this, SPINNERS_ITEMS, R.layout.spinner_item_single, from, to);
        mSpinner1.setAdapter(spinnerAdapter);
        mSpinner2.setAdapter(spinnerAdapter);

        // When the Spinner is drop Down show a new layout to each child view
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_drop_down);
        mSpinner1.setOnItemSelectedListener(spinnerAdapter);
        mSpinner2.setOnItemSelectedListener(spinnerAdapter);
    }

    public void putItem(ArrayList<HashMap<String, String>> enumsItems) {
        SPINNERS_ITEMS = enumsItems;
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

                    mCustomEditTextView.getText().delete(cursorPosition - 1, cursorPosition);
                    mCustomEditTextView.setSelection(cursorPosition - 1);

                } else {
                    mCustomEditTextView.getText().delete(cursorPosition - 1, cursorPosition);
                }
            } else {
                mCustomEditTextView.getText().delete(getCursorStart(), getCursorEnd());
            }
        }
    }

    private void insertTextInEditText(String textInput) {
        int attCursorPositionBefore;
        int attCursorPositionNow;

        if (getCustomEditTextSize() > 0 && getTextSize() == 1) {

            mCustomEditTextView.setText(textInput);
            attCursorPositionNow = mCustomEditTextView.getText().length();

            mCustomEditTextView.setSelection(attCursorPositionNow);
        } else {

            if (mCustomEditTextView.getSelectionEnd() == getCustomEditTextSize()) {

                mCustomEditTextView.setText(mCustomEditTextView.getText().toString() + textInput);
                mCustomEditTextView.setSelection(mCustomEditTextView.getText().toString().length());
            } else {

                String textLeftCursor = (String) mCustomEditTextView.getText().toString().subSequence(0, getCursorStart());
                String textRightCursor = (String) mCustomEditTextView.getText().toString().subSequence(getCursorEnd(), mCustomEditTextView.getText().toString().length());
                String allExpressionInput = textLeftCursor + textInput + textRightCursor;

                attCursorPositionBefore = getCursorEnd();
                attCursorPositionNow = attCursorPositionBefore + 1;

                mCustomEditTextView.setText(allExpressionInput);
                mCustomEditTextView.setSelection(attCursorPositionNow);
            }
        }
    }

    private void acessMenu() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    private void expressionClear() {
        mCustomEditTextView.setText("");
    }


    private int getCursorEnd() {
        return mCustomEditTextView.getSelectionEnd();
    }

    private int getCursorStart() {
        return mCustomEditTextView.getSelectionStart();
    }

    private int getCustomEditTextSize() {

        return mCustomEditTextView.getText().toString().trim().length();
    }

    private int getTextSize() {

        return mTextView.getText().toString().trim().length();
    }
}
