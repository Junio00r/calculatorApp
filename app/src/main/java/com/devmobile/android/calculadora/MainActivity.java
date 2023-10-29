package com.devmobile.android.calculadora;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.devmobile.android.calculadora.model.CustomEditTextView;
import com.devmobile.android.calculadora.model.OnButtonClickListener;
import com.devmobile.android.calculadora.model.OnItemClickListener;
import com.devmobile.android.calculadora.model.TopSheetBehavior;
import com.devmobile.android.calculadora.model.recicleView.OperationCalculated;
import com.devmobile.android.calculadora.model.recicleView.OperationCalculatedAdapter;
import com.devmobile.android.calculadora.model.viewPager2Fragment.ExpensiveKeyboard;
import com.devmobile.android.calculadora.model.viewPager2Fragment.ViewPagerKeyboardAdapter;
import com.google.android.material.appbar.AppBarLayout;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, OnItemClickListener, OnButtonClickListener {

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
    private HashSet<Integer> alternativeButtonsHash = new HashSet<>();
    private View externalLayout;
    private AppBarLayout appBarLayout;
    private ConstraintLayout defaultKeyboard;
    private ViewPager2 viewPager2;

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

    private ViewPagerKeyboardAdapter viewPagerKeyboardAdapter;
    private View expensiveKeyboard;

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
        defaultKeyboard = findViewById(R.id.default_keyboard);
        topSheetBehavior = findViewById(R.id.top_sheet);
        TopSheetBehavior.from(topSheetBehavior).setState(TopSheetBehavior.STATE_COLLAPSED);
        LayoutInflater layoutInflater = getLayoutInflater();
        externalLayout = layoutInflater.inflate(R.layout.default_keyboard, null);
        expensiveKeyboard = layoutInflater.inflate(R.layout.expensive_keyboard, null);


        viewPager2 = findViewById(R.id.viewPager);
        viewPagerKeyboardAdapter = new ViewPagerKeyboardAdapter(this);
        viewPager2.setAdapter(viewPagerKeyboardAdapter);

        // buttonsDefault
        buttonBackSpace = externalLayout.findViewById(R.id.buttonBackSpace).getId();
        buttonEquals = externalLayout.findViewById(R.id.buttonEquals).getId();
        buttonDown = externalLayout.findViewById(R.id.buttonDown).getId();
        buttonUp = externalLayout.findViewById(R.id.buttonUp).getId();
        buttonMenu = externalLayout.findViewById(R.id.buttonMenu).getId();
        buttonClearAll = externalLayout.findViewById(R.id.buttonClearAll).getId();
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
            } else if (buttonDown == viewButton.getId() || buttonUpExp == viewButton.getId()) {
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

    /**
     * @param viewButton reference at the clicked button in default Keyboard or expensive keyboard
     */
    @Override
    public void onClickButton(View viewButton) {
        this.onClick(viewButton);
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
