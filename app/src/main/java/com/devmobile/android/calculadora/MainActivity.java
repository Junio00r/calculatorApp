package com.devmobile.android.calculadora;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devmobile.android.calculadora.model.CustomEditTextView;
import com.devmobile.android.calculadora.model.OnItemClickListener;
import com.devmobile.android.calculadora.model.TopSheetBehavior;
import com.devmobile.android.calculadora.model.recicleView.OperationCalculated;
import com.devmobile.android.calculadora.model.recicleView.OperationCalculatedAdapter;
import com.google.android.material.appbar.AppBarLayout;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener, OnItemClickListener {
    private TextView textView;
    private CustomEditTextView idEditTextView;
    private OperationCalculatedAdapter operationCalculatedAdapter;
    private Button button;
    private List<OperationCalculated> operationsCalculated = new LinkedList<>();
    public int cursorPosition = 0;
    private RecyclerView recyclerView;
    private View topSheetBehavior;
    private RecyclerView.LayoutManager layoutManager;
    private LinearLayout bottom_sheet;
    private HashSet<View> alternativeButtonsHash = new HashSet<>();
    private AppBarLayout appBarLayout;
    private LinearLayout content_view;
    private ImageButton buttonCopy;
    private ImageButton buttonPercent;
    private ImageButton buttonOpenParenthesis;
    private ImageButton buttonCloseParenthesis;
    private ImageButton buttonBackSpace;
    private ImageButton buttonDivision;
    private ImageButton buttonMultiplication;
    private ImageButton buttonSubtraction;
    private ImageButton buttonSum;
    private ImageButton buttonEquals;
    private ImageButton buttonDown;
    private ImageButton buttonUp;
    private ImageButton buttonMenu;
    private Button buttonSeparator;
    private Button buttonClearAll;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflating all layouts in activity_main.xml
        setContentView(R.layout.activity_main);


        initReferences();

    }

    @SuppressLint("ResourceType")
    private void initReferences() {

        idEditTextView = findViewById(R.id.editTextViewID);
        textView = findViewById(R.id.textResultExpression);
        idEditTextView.setTextView(textView);
        recyclerView = findViewById(R.id.recycle_view_historic);
        content_view = findViewById(R.id.conent_main);
        topSheetBehavior = findViewById(R.id.top_sheet);
        TopSheetBehavior.from(topSheetBehavior).setState(TopSheetBehavior.STATE_COLLAPSED);

        // buttons
        buttonCopy = findViewById(R.id.buttonCopy);
        buttonPercent = findViewById(R.id.buttonPercent);
        buttonOpenParenthesis = findViewById(R.id.buttonOpenParenthesis);
        buttonCloseParenthesis = findViewById(R.id.buttonCloseParenthesis);
        buttonBackSpace = findViewById(R.id.buttonBackSpace);
        buttonDivision = findViewById(R.id.buttonDivision);
        buttonMultiplication = findViewById(R.id.buttonMultiplication);
        buttonSubtraction = findViewById(R.id.buttonSubtraction);
        buttonSum = findViewById(R.id.buttonSum);
        buttonEquals = findViewById(R.id.buttonEquals);
        buttonDown = findViewById(R.id.buttonDown);
        buttonUp = findViewById(R.id.buttonUp);
        buttonMenu = findViewById(R.id.buttonMenu);
        buttonClearAll = findViewById(R.id.buttonClearAll);
        buttonSeparator = findViewById(R.id.buttonSeparator);
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

        puttingAlternativeButtonsHashMap();
        setButtonOnClickListener();
        initAdapterRecyclerView();
    }


    private void initAdapterRecyclerView() {
        layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        this.operationCalculatedAdapter = new OperationCalculatedAdapter(operationsCalculated, this);
        recyclerView.setAdapter(this.operationCalculatedAdapter);
        recyclerView.setLayoutManager(this.layoutManager);
        this.operationCalculatedAdapter.addItemClickListener(this);
    }

    public void setButtonOnClickListener() {
        View[] buttons = {
                buttonCopy, buttonPercent, buttonOpenParenthesis, buttonCloseParenthesis, buttonBackSpace
                , buttonDivision, buttonMultiplication, buttonSubtraction, buttonSum, buttonEquals
                , buttonDown, buttonUp, buttonMenu, buttonClearAll, buttonSeparator
                , buttonZero, buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix
                , buttonSeven, buttonEight, buttonNine
        };

        for (View e : buttons) {
            e.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(@NonNull View viewButton) {

        if (isAlternativeFunction(viewButton)) {
            if (buttonBackSpace.equals(viewButton)) {
                excludeCharacter();
            } else if (buttonEquals.equals(viewButton)) {
                equalsResult();
            } else if (buttonClearAll.equals(viewButton)) {
                expressionClear();
            } else if (buttonCopy.equals(viewButton)) {
                copyCalculate();
            } else if (buttonUp.equals(viewButton)) {
                upExpression();
            } else if (buttonDown.equals(viewButton)) {
                downExpression();
            } else {
                acessMenu();
            }
        } else {
            insertTextInEditText(viewButton.getContentDescription().toString());
        }
    }

    @SuppressLint("SetTextI18n")
    private void insertTextInEditText(String text) {
        int cursorPositionBefore;
        int cursorPositionNow;

        if (getEditTextSize() > 0 && getTextSize() == 1) {
            idEditTextView.setText(text);
            cursorPositionNow = idEditTextView.getText().length();

            idEditTextView.setSelection(cursorPositionNow);
        } else {
            if (idEditTextView.getSelectionEnd() == getEditTextSize()) {

                idEditTextView.setText(idEditTextView.getText() + text);
                idEditTextView.setSelection(idEditTextView.getText().toString().length());
            } else {

                String textLeftCursor = (String) idEditTextView.getText().toString().subSequence(0, getCursorStart());
                String textRightCursor = (String) idEditTextView.getText().toString().subSequence(getCursorEnd(), idEditTextView.getText().toString().length());
                String completedExpressionInput = textLeftCursor + text + textRightCursor;
                cursorPositionBefore = getCursorEnd();
                cursorPositionNow = cursorPositionBefore + 1;

                idEditTextView.setText(completedExpressionInput);
                idEditTextView.setSelection(cursorPositionNow);
            }
        }
        //        if (getEditTextSize() >= 1
//                && ExpressionInputCheck.isOperator(textButton)
//                && isReplace(textButton)) {
//
//            idEditTextView.getText().replace(getEditTextSize() - 1, getEditTextSize(), textButton);
//
//        } else {
    }

    private boolean isAlternativeFunction(View viewButton) {

        return alternativeButtonsHash.contains(viewButton);
    }

    private void puttingAlternativeButtonsHashMap() {
        View[] buttons = {
                buttonBackSpace, buttonEquals, buttonClearAll, buttonUp, buttonDown
                , buttonMenu, buttonCopy
        };

        alternativeButtonsHash.addAll(Arrays.asList(buttons));
    }

    private void acessMenu() {

    }

    private void upExpression() {
        if (recyclerView.getChildCount() > 0) {

        }
    }

    private void downExpression() {
        if (recyclerView.getChildCount() > 0) {

        }
    }

    public void copyCalculate() {
        String messageCopy = "Resultado Copiado!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, messageCopy, duration);
        ClipData clipData1 = ClipData.newPlainText(messageCopy, getTextViewId().getText().toString());
        ClipData clipData2 = ClipData.newPlainText(messageCopy, idEditTextView.getText().toString());
        ClipboardManager clipboardManager = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);

        if (!getTextViewId().getText().toString().equals("=")) {

            clipboardManager.setPrimaryClip(clipData1);
            toast.show();

        } else if (!idEditTextView.getText().toString().isEmpty()) {
            clipboardManager.setPrimaryClip(clipData2);
            toast.show();

        }
    }

    public void equalsResult() {

        if (!getTextViewId().getText().equals("Invalid Expression!")
                && !getTextViewId().getText().toString().equals("=")
                && getEditTextSize() > 0) {

            String resultedInTextView = getTextViewId().getText().toString().replace("=", "");

            setHistoric();

            idEditTextView.setText(resultedInTextView);
            idEditTextView.setSelection(getEditTextSize());

            getTextViewId().setText("=");
        }
    }

    public void excludeCharacter() {

        cursorPosition = getCursorEnd();

        if (cursorPosition > 0) {

            if (getCursorStart() == getCursorEnd()) {

                if (getCursorStart() < getEditTextSize()
                        && getCursorStart() > 0) {

                    idEditTextView.getText().delete(cursorPosition - 1, cursorPosition);
                    idEditTextView.setSelection(cursorPosition - 1);

                } else {
                    idEditTextView.getText().delete(cursorPosition - 1, cursorPosition);
                }

            } else {

                idEditTextView.getText().delete(getCursorStart(), getCursorEnd());
            }
        }
    }

    public void expressionClear() {
        idEditTextView.setText("");
        getTextViewId().setText("=");
    }

    public void setHistoric() {

        if (getEditTextSize() > 0) {

            String expression = idEditTextView.getText().toString();
            String expressionResult = getTextViewId().getText().toString();

            if (operationsCalculated.size() <= 5) {

                operationsCalculated.add(new OperationCalculated(expression, expressionResult));
                operationCalculatedAdapter.notifyItemInserted(recyclerView.getAdapter().getItemCount() - 1);

            } else {
                operationsCalculated.remove(0);
                operationCalculatedAdapter.notifyItemRemoved(0);

                operationsCalculated.add(new OperationCalculated(expression, expressionResult));
                operationCalculatedAdapter.notifyItemInserted(recyclerView.getAdapter().getItemCount() - 1);
            }
        }
    }

    @Override
    public void onClickItem(int positionTouched) {
        String expressionClicking = operationsCalculated.get(positionTouched).getExpression();
        operationsCalculated.remove(positionTouched);
        operationCalculatedAdapter.notifyItemRemoved(positionTouched);

        if (!this.idEditTextView.getText().equals("")) {
            setHistoric();
        }

        insertTextInEditText(expressionClicking);
    }

    // Getters
    public TextView getTextViewId() {
        return findViewById(R.id.textResultExpression);
    }

    public int getCursorEnd() {
        return idEditTextView.getSelectionEnd();
    }

    public int getCursorStart() {
        return idEditTextView.getSelectionStart();
    }

    public int getTextSize() {
        return textView.getText().toString().trim().length();
    }

    public int getEditTextSize() {
        return idEditTextView.getText().toString().trim().length();
    }

}
