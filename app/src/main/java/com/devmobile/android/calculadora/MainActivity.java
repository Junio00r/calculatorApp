package com.devmobile.android.calculadora;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.devmobile.android.calculadora.model.BackgroundButtonView;
import com.devmobile.android.calculadora.model.CustomEditTextView;
import com.devmobile.android.calculadora.model.interfaces.OnButtonClickListener;
import com.devmobile.android.calculadora.model.interfaces.OnItemClickListener;
import com.devmobile.android.calculadora.model.TopSheetBehavior;
import com.devmobile.android.calculadora.model.recicleView.OperationCalculated;
import com.devmobile.android.calculadora.model.recicleView.OperationCalculatedAdapter;
import com.devmobile.android.calculadora.model.viewPager2Fragment.ViewPagerKeyboardAdapter;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, OnItemClickListener, OnButtonClickListener, Serializable {

    private static final HashSet<Integer> alternativeButtonsHash = new HashSet<>();
    private static final List<OperationCalculated> operationsCalculated = new LinkedList<>();
    @SuppressLint("StaticFieldLeak")
    private static TextView textView;
    @SuppressLint("StaticFieldLeak")
    private static CustomEditTextView customEditTextView;
    private static RecyclerView recyclerView;
    private static ViewPagerKeyboardAdapter viewPagerKeyboardAdapter;
    private static OperationCalculatedAdapter operationCalculatedAdapter;
    private int lastExpressionUpAccessed = 0;
    private int cursorPosition = 0;
    private final View.OnClickListener clickListener = this;

    private int buttonBackSpace;
    private int buttonEquals;
    private int buttonDown;
    private int buttonUp;
    private int buttonMenu;
    private int buttonClearAll;
    private int buttonBackSpaceExp;
    private int buttonEqualsExp;
    private int buttonDownExp;
    private int buttonUpExp;
    private int buttonClearAllExp;
    private static int layoutId = R.layout.activity_main;
    private static final Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        // Inflating all layouts in activity_main.xml
        if (savedInstanceState != null) {
            layoutId = savedInstanceState.getInt("layoutId", layoutId);
            setContentView(layoutId);
            initReferences();
            customEditTextView.setText(savedInstanceState.getString("custom_editText_text"));
        } else {

            setContentView(layoutId);
            initReferences();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        customEditTextView = null;
        textView = null;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("layoutId", layoutId);
        outState.putString("custom_editText_text", customEditTextView.getText().toString());
    }

    private void acessMenu() {

        String expressionInEditText = customEditTextView.getText().toString();
        bundle.putString("editTextValue", expressionInEditText);
        onSaveInstanceState(bundle);

        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    private void initReferences() {

        customEditTextView = findViewById(R.id.editTextViewID);
        textView = findViewById(R.id.textResultExpression);
        customEditTextView.setTextView(textView);
        textView.setMovementMethod(new ScrollingMovementMethod());

        recyclerView = findViewById(R.id.recycle_view_historic);
        View topSheetBehavior = findViewById(R.id.top_sheet);
        TopSheetBehavior.from(topSheetBehavior).setState(TopSheetBehavior.STATE_COLLAPSED);
        DotsIndicator dotsIndicator = findViewById(R.id.dots_indicator);


        LayoutInflater layoutInflater = getLayoutInflater();
        View defaultLayout = layoutInflater.inflate(R.layout.default_keyboard, null);
        View expensiveKeyboard = layoutInflater.inflate(R.layout.expensive_keyboard, null);

        ViewPager2 viewPager2 = findViewById(R.id.viewPager);
        viewPagerKeyboardAdapter = ViewPagerKeyboardAdapter.getInstance(this);
        viewPager2.setAdapter(viewPagerKeyboardAdapter);
        dotsIndicator.attachTo(viewPager2);

        // buttonsDefault
        buttonBackSpace = defaultLayout.findViewById(R.id.buttonBackSpace).getId();
        BackgroundButtonView buttonSeparator = defaultLayout.findViewById(R.id.buttonSeparator);
        buttonEquals = defaultLayout.findViewById(R.id.buttonEquals).getId();
        buttonDown = defaultLayout.findViewById(R.id.buttonDown).getId();
        buttonUp = defaultLayout.findViewById(R.id.buttonUp).getId();
        buttonMenu = defaultLayout.findViewById(R.id.buttonMenu).getId();
        buttonClearAll = defaultLayout.findViewById(R.id.buttonClearAll).getId();
        findViewById(R.id.buttonCopy).setOnClickListener(v -> copyCalculate());

        // buttonsExpensive
        buttonBackSpaceExp = expensiveKeyboard.findViewById(R.id.buttonBackSpaceExp).getId();
        buttonEqualsExp = expensiveKeyboard.findViewById(R.id.buttonEqualsExp).getId();
        buttonDownExp = expensiveKeyboard.findViewById(R.id.buttonDownExp).getId();
        buttonUpExp = expensiveKeyboard.findViewById(R.id.buttonUpExp).getId();
        buttonClearAllExp = expensiveKeyboard.findViewById(R.id.buttonClearAllExp).getId();

//        puttingAlternativeButtons();
        initAdapters();
    }

    private void initAdapters() {
        // RecyclerViewAdapter
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        operationCalculatedAdapter = OperationCalculatedAdapter.getInstance(operationsCalculated, this);
        recyclerView.setAdapter(operationCalculatedAdapter);
        recyclerView.setLayoutManager(layoutManager);

        // KeyboardAdapter
        operationCalculatedAdapter.addItemClickListener(this);
        viewPagerKeyboardAdapter.addOnButtonClickListener(this);
    }

    /**
     * @param viewButton reference at the clicked button in default Keyboard or expensive keyboard
     */
    @Override
    public void onClickButton(View viewButton) {

        this.onClick(viewButton);
    }

    @Override
    public void onClick(@NonNull View viewButton) {

//        if (isAlternativeFunction(viewButton.getId())) {
            if (buttonBackSpace == viewButton.getId() || buttonBackSpaceExp == viewButton.getId()) {
                excludeCharacter();
            } else if (buttonEquals == viewButton.getId() || buttonEqualsExp == viewButton.getId()) {
                equalsResult();
            } else if (buttonClearAll == viewButton.getId() || buttonClearAllExp == viewButton.getId()) {
                expressionClear();
            } else if (buttonUp == viewButton.getId() || buttonUpExp == viewButton.getId()) {
                upExpression();
            } else if (buttonDown == viewButton.getId() || buttonDownExp == viewButton.getId()) {
                downExpression();
            } else if (buttonMenu == viewButton.getId() || buttonMenu == viewButton.getId()){
                acessMenu();
            } else {
//        } else {
            insertTextInEditText(viewButton.getContentDescription().toString());
        }
    }

    private void insertTextInEditText(String textInput) {
        int attCursorPositionBefore;
        int attCursorPositionNow;

        if (getCustomEditTextSize() > 0 && getTextSize() == 1) {

            customEditTextView.setText(textInput);
            attCursorPositionNow = customEditTextView.length();

            customEditTextView.setSelection(attCursorPositionNow);
            attCursorPosition();
        } else {

            if (customEditTextView.getSelectionEnd() == getCustomEditTextSize()) {

                customEditTextView.setText(customEditTextView.getText().toString() + textInput);
                customEditTextView.setSelection(customEditTextView.getText().toString().length());
                attCursorPosition();
            } else {

                String textLeftCursor = (String) customEditTextView.getText().toString().subSequence(0, getCursorStart());
                String textRightCursor = (String) customEditTextView.getText().toString().subSequence(getCursorEnd(), customEditTextView.getText().toString().length());
                String allExpressionInput = textLeftCursor + textInput + textRightCursor;

                attCursorPositionBefore = getCursorEnd();
                attCursorPositionNow = attCursorPositionBefore + 1;

                customEditTextView.setText(allExpressionInput);
                customEditTextView.setSelection(attCursorPositionNow);
                attCursorPosition();
            }
        }
    }

//    private boolean isAlternativeFunction(Integer viewButtonId) {
//        return alternativeButtonsHash.contains(viewButtonId);
//    }


    /**
     * Aparatemente esses botoes nao tem necessidade por conta deles estare nas classes
     * do teclado padrao e teclado expansivo
     */
//    private void puttingAlternativeButtons() {
//        Integer[] buttonsAlternatives = new Integer[]{
//                buttonBackSpace, buttonBackSpaceExp, buttonEquals, buttonEqualsExp
//                , buttonClearAll, buttonClearAllExp, buttonUp, buttonUpExp
//                , buttonDown, buttonDownExp, buttonMenu
//        };
//
//        alternativeButtonsHash.addAll(Arrays.asList(buttonsAlternatives));
//    }

    private void upExpression() {

        if (recyclerView.getChildCount() > 0) {

            if (lastExpressionUpAccessed == 0) {

                lastExpressionUpAccessed = recyclerView.getChildCount() - 1;
            } else {
                lastExpressionUpAccessed--;
            }

            onRemoveOnPositionRecycler(lastExpressionUpAccessed);
        }
    }

    private void downExpression() {
        int lastExpressionDownAccessed = 0;
        if (recyclerView.getChildCount() > 0)
            onRemoveOnPositionRecycler(lastExpressionDownAccessed);
    }

    private void copyCalculate() {
        String messageCopy = "Resultado Copiado!";
        Toast toast = Toast.makeText(this, messageCopy, Toast.LENGTH_SHORT);
        String result = textView.getText().toString().replace("=", "");
        ClipData clipData1 = ClipData.newPlainText(messageCopy, result);
        ClipData clipData2 = ClipData.newPlainText(messageCopy, customEditTextView.getText().toString());
        ClipboardManager clipboardManager = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);

        if (!textView.getText().toString().equals("=")) {

            clipboardManager.setPrimaryClip(clipData1);
            toast.show();

        } else if (!customEditTextView.getText().toString().isEmpty()) {
            clipboardManager.setPrimaryClip(clipData2);
            toast.show();

        }
    }

    private void equalsResult() {

        if (!textView.getText().equals("Invalid Expression!")
                && !textView.getText().toString().equals("=")
                && getCustomEditTextSize() > 0) {

            String resultedInTextView = textView.getText().toString().replace("= ", "");

            putHistoric();

            customEditTextView.setText(resultedInTextView);
            customEditTextView.setSelection(getCustomEditTextSize());

            textView.setText("=");
        } else {
            String teste = textView.getText().toString();
            String teste2 = customEditTextView.getText().toString();
            Log.i("tests", teste);
            Log.i("tests", teste2);

        }
    }

    public void excludeCharacter() {

        if (cursorPosition > 0) {

            if (getCursorStart() == getCursorEnd()) {


                customEditTextView.getText().delete(cursorPosition - 1, cursorPosition);
                attCursorPosition();

            } else {
                customEditTextView.getText().delete(getCursorStart(), getCursorEnd());
                attCursorPosition();
            }
        }
    }

    private void expressionClear() {

        customEditTextView.setText("");
        textView.setText("");
    }

    private void putHistoric() {

        if (getCustomEditTextSize() > 0) {

            String expression = customEditTextView.getText().toString();
            String expressionResult = textView.getText().toString();

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
    public void onRemoveOnPositionRecycler(int positionToRemove) {
        try {
            String expressionClicking = operationsCalculated.get(positionToRemove).getExpression();
            operationsCalculated.remove(positionToRemove);
            operationCalculatedAdapter.notifyItemRemoved(positionToRemove);

            if (!customEditTextView.getText().equals("")) {
                putHistoric();
                expressionClear();
            }

            insertTextInEditText(expressionClicking);

        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();

            Toast.makeText(this, "Operação não encontrada!", Toast.LENGTH_SHORT).show();
        }
    }

    // Getters
    public int  getTextViewId() {
        return textView.getId();
    }

    public int getCursorEnd() {
        return customEditTextView.getSelectionEnd();
    }

    public int getCursorStart() {
        return customEditTextView.getSelectionStart();
    }

    public int getTextSize() {
        return textView.getText().length();
    }

    public int getCustomEditTextSize() {
        return customEditTextView.getText().length();
    }

    private void attCursorPosition() {
        cursorPosition = getCustomEditTextSize();
    }
}
