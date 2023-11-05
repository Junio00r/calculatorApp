package com.devmobile.android.calculadora;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.devmobile.android.calculadora.model.CustomEditTextView;
import com.devmobile.android.calculadora.model.interfaces.OnButtonClickListener;
import com.devmobile.android.calculadora.model.interfaces.OnItemClickListener;
import com.devmobile.android.calculadora.model.TopSheetBehavior;
import com.devmobile.android.calculadora.model.recicleView.OperationCalculated;
import com.devmobile.android.calculadora.model.recicleView.OperationCalculatedAdapter;
import com.devmobile.android.calculadora.model.viewPager2Fragment.ViewPagerKeyboardAdapter;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, OnItemClickListener, OnButtonClickListener {

    private TextView textView;
    private CustomEditTextView customEditTextView;
    private OperationCalculatedAdapter operationCalculatedAdapter;
    private final List<OperationCalculated> operationsCalculated = new LinkedList<>();
    private RecyclerView recyclerView;
    private View topSheetBehavior;
    private RecyclerView.LayoutManager layoutManager;
    private final HashSet<Integer> alternativeButtonsHash = new HashSet<>();
    private View defaultLayout;
    private ViewPager2 viewPager2;
    private ViewPagerKeyboardAdapter viewPagerKeyboardAdapter;
    private View expensiveKeyboard;
    private int lastExpressionUpAccessed = 0;
    private int lastExpressionDownAccessed = 0;
    private DotsIndicator dotsIndicator;
    private Bundle bundle;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflating all layouts in activity_main.xml
        setContentView(R.layout.activity_main);

        initReferences();
    }

    private void acessMenu() {
        bundle = new Bundle();
        String expressionInEditText = this.customEditTextView.getText().toString();
        bundle.putString("editTextValue", expressionInEditText);
//        this.onSaveInstanceState(bundle);

//        this.onStop();
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    @SuppressLint("ResourceType")
    private void initReferences() {

        customEditTextView = findViewById(R.id.editTextViewID);
        textView = findViewById(R.id.textResultExpression);
        customEditTextView.setTextView(textView);
        recyclerView = findViewById(R.id.recycle_view_historic);
        topSheetBehavior = findViewById(R.id.top_sheet);
        TopSheetBehavior.from(topSheetBehavior).setState(TopSheetBehavior.STATE_COLLAPSED);
        dotsIndicator = findViewById(R.id.dots_indicator);


        LayoutInflater layoutInflater = getLayoutInflater();
        defaultLayout = layoutInflater.inflate(R.layout.default_keyboard, null);
        expensiveKeyboard = layoutInflater.inflate(R.layout.expensive_keyboard, null);

        viewPager2 = findViewById(R.id.viewPager);
        viewPagerKeyboardAdapter = new ViewPagerKeyboardAdapter(this);
        viewPager2.setAdapter(viewPagerKeyboardAdapter);
        dotsIndicator.attachTo(viewPager2);

//

        // buttonsDefault
        buttonBackSpace = defaultLayout.findViewById(R.id.buttonBackSpace).getId();
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

        puttingAlternativeButtons();
        initAdapters();
    }

    private void initAdapters() {
        // RecyclerViewAdapter
        layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        this.operationCalculatedAdapter = new OperationCalculatedAdapter(operationsCalculated, this);
        recyclerView.setAdapter(this.operationCalculatedAdapter);
        recyclerView.setLayoutManager(this.layoutManager);

        // KeyboardAdapter
        this.operationCalculatedAdapter.addItemClickListener(this);
        this.viewPagerKeyboardAdapter.addOnButtonClickListener(this);
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

        if (isAlternativeFunction(viewButton.getId())) {
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
            } else {
                acessMenu();
            }
        } else {
            insertTextInEditText(viewButton.getContentDescription().toString());
        }
    }

    private void insertTextInEditText(String textInput) {
        int attCursorPositionBefore;
        int attCursorPositionNow;

        if (getCustomEditTextSize() > 0 && getTextSize() == 1) {

            customEditTextView.setText(textInput);
            attCursorPositionNow = customEditTextView.getText().length();

            customEditTextView.setSelection(attCursorPositionNow);
        } else {

            if (customEditTextView.getSelectionEnd() == getCustomEditTextSize()) {

                customEditTextView.setText(customEditTextView.getText() + textInput);
                customEditTextView.setSelection(customEditTextView.getText().toString().length());
            } else {

                String textLeftCursor = (String) customEditTextView.getText().toString().subSequence(0, getCursorStart());
                String textRightCursor = (String) customEditTextView.getText().toString().subSequence(getCursorEnd(), customEditTextView.getText().toString().length());
                String allExpressionInput = textLeftCursor + textInput + textRightCursor;

                attCursorPositionBefore = getCursorEnd();
                attCursorPositionNow = attCursorPositionBefore + 1;

                customEditTextView.setText(allExpressionInput);
                customEditTextView.setSelection(attCursorPositionNow);
            }
        }
    }

    private boolean isAlternativeFunction(Integer viewButtonId) {
        return alternativeButtonsHash.contains(viewButtonId);
    }

    private void puttingAlternativeButtons() {
        Integer[] buttonsAlternatives = new Integer[]{
                buttonBackSpace, buttonBackSpaceExp, buttonEquals, buttonEqualsExp
                , buttonClearAll, buttonClearAllExp, buttonUp, buttonUpExp
                , buttonDown, buttonDownExp, buttonMenu
        };

        alternativeButtonsHash.addAll(Arrays.asList(buttonsAlternatives));
    }

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
        if (recyclerView.getChildCount() > 0)
            onRemoveOnPositionRecycler(lastExpressionDownAccessed);
    }

    private void copyCalculate() {
        String messageCopy = "Resultado Copiado!";
        Toast toast = Toast.makeText(this, "Resultado Copiado!", Toast.LENGTH_SHORT);
        String result = getTextViewId().getText().toString().replace("=", "");
        ClipData clipData1 = ClipData.newPlainText(messageCopy, result);
        ClipData clipData2 = ClipData.newPlainText(messageCopy, customEditTextView.getText().toString());
        ClipboardManager clipboardManager = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);

        if (!getTextViewId().getText().toString().equals("=")) {

            clipboardManager.setPrimaryClip(clipData1);
            toast.show();

        } else if (!customEditTextView.getText().toString().isEmpty()) {
            clipboardManager.setPrimaryClip(clipData2);
            toast.show();

        }
    }

    private void equalsResult() {

        if (!getTextViewId().getText().equals("Invalid Expression!")
                && !getTextViewId().getText().toString().equals("=")
                && getCustomEditTextSize() > 0) {

            String resultedInTextView = getTextViewId().getText().toString().replace("=", "");

            putHistoric();

            customEditTextView.setText(resultedInTextView);
            customEditTextView.setSelection(getCustomEditTextSize());

            getTextViewId().setText("=");
        }
    }

    public void excludeCharacter() {
        int cursorPosition = getCursorEnd();

        if (cursorPosition > 0) {
            if (getCursorStart() == getCursorEnd()) {
                if (getCursorStart() < getCustomEditTextSize()
                        && getCursorStart() > 0) {

                    customEditTextView.getText().delete(cursorPosition - 1, cursorPosition);
                    customEditTextView.setSelection(cursorPosition - 1);

                } else {
                    customEditTextView.getText().delete(cursorPosition - 1, cursorPosition);
                }
            } else {
                customEditTextView.getText().delete(getCursorStart(), getCursorEnd());
            }
        }
    }

    private void expressionClear() {
        customEditTextView.setText("");
        getTextViewId().setText("=");
    }

    private void putHistoric() {

        if (getCustomEditTextSize() > 0) {

            String expression = customEditTextView.getText().toString();
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
    public void onRemoveOnPositionRecycler(int positionToRemove) {
        String expressionClicking = operationsCalculated.get(positionToRemove).getExpression();
        operationsCalculated.remove(positionToRemove);
        operationCalculatedAdapter.notifyItemRemoved(positionToRemove);

        if (!this.customEditTextView.getText().equals("")) {
            putHistoric();
            expressionClear();
        }

        insertTextInEditText(expressionClicking);
    }

    // Getters
    public TextView getTextViewId() {
        return findViewById(R.id.textResultExpression);
    }

    public int getCursorEnd() {
        return customEditTextView.getSelectionEnd();
    }

    public int getCursorStart() {
        return customEditTextView.getSelectionStart();
    }

    public int getTextSize() {
        return textView.getText().toString().trim().length();
    }

    public int getCustomEditTextSize() {
        return customEditTextView.getText().toString().trim().length();
    }
}
