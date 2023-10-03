package com.devmobile.android.calculadora;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devmobile.android.calculadora.model.EditTextView;
import com.devmobile.android.calculadora.model.recicleView.OperationCalculated;
import com.devmobile.android.calculadora.model.recicleView.OperationCalculatedAdapter;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private EditTextView idEditTextView;
    private Button button;
    public int cursorPosition = 0;
    private RecyclerView recyclerView;
    private List<OperationCalculated> operationsCalculated = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idEditTextView = findViewById(R.id.editTextViewID);
        recyclerView = (RecyclerView) findViewById(R.id.recycle_view_historic);
        this.setResultTextView(getTextViewId());


    }

//    private void initRecyclerView() {
//        operationsCalculated.add(new OperationCalculated("Text", "test"));
//        recyclerView.setAdapter(new OperationCalculatedAdapter(operationsCalculated, this));
//
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
//
//        recyclerView.setLayoutManager(layoutManager);
//    }

    @SuppressLint("SetTextI18n")
    public void onClick(@NonNull View viewButton) {

        Button buttonID = viewButton.findViewById(viewButton.getId());
        String textButton = buttonID.getText().toString();

//        if (getEditTextSize() >= 1
//                && ExpressionInputCheck.isOperator(textButton)
//                && isReplace(textButton)) {
//
//            idEditTextView.getText().replace(getEditTextSize() - 1, getEditTextSize(), textButton);
//
//        } else {

        if (idEditTextView.getSelectionEnd() == idEditTextView.textSize()) {

            idEditTextView.setText(idEditTextView.getText() + textButton);
            idEditTextView.setSelection(idEditTextView.getText().toString().length());
        } else {

            insertTextButton(textButton);
//            }
        }
    }

//    public boolean isReplace(String textButton) {
//
//        return  ExpressionInputCheck.isOperator(String.valueOf(idEditTextView.getText().toString()))
//                && !String.valueOf(idEditTextView.getText().toString().charAt(getEditTextSize() - 1)).equals("(")
//                || !String.valueOf(idEditTextView.getText().toString().charAt(getEditTextSize() - 1)).equals(")")
//                || !(String.valueOf(idEditTextView.getText().toString().charAt(getEditTextSize() - 1)).equals("%"))
//                && textButton.equals("%");
//    }

    public void insertTextButton(String buttonText) {

        String textLeftCursor = (String) idEditTextView.getText().toString().subSequence(0, getCursorStart());
        String textRightCursor = (String) idEditTextView.getText().toString().subSequence(getCursorEnd(), idEditTextView.getText().toString().length());
        String completedExpressionInput = textLeftCursor + buttonText + textRightCursor;
        int cursorPositionBefore = getCursorEnd();
        int cursorPositionNow = cursorPositionBefore + 1;

        idEditTextView.setText(completedExpressionInput);
        idEditTextView.setSelection(cursorPositionNow);
    }

    public void copyCalculate(@NonNull View copyViewButton) {
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

    public void setHistoric() {

        String expression = idEditTextView.getText().toString();
        String expressionResult = getTextViewId().getText().toString();

        operationsCalculated.add(new OperationCalculated(expression, expressionResult));
        recyclerView.setAdapter(new OperationCalculatedAdapter(operationsCalculated, this));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);

    }

    public void equalsResult(View viewEqualButton) {

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

    public void excludeCharacter(View viewExcludeCharacterButton) {

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

    public void expressionClear(View viewCleanButton) {
        idEditTextView.setText("");
        getTextViewId().setText("=");
    }

    // Setters
    public void setResultTextView(TextView textView) {
        idEditTextView.textChangeOnListener(textView);
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

    public int getEditTextSize() {
        return idEditTextView.getText().length();
    }

}
