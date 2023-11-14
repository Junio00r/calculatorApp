package com.devmobile.android.calculadora.model;

import android.content.ClipboardManager;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.devmobile.android.calculadora.R;
import com.singularsys.jep.JepException;

public class CustomEditTextView extends androidx.appcompat.widget.AppCompatEditText {
    protected TextView textView;
    protected CustomEditTextView customEditTextView;

    public CustomEditTextView(Context context, AttributeSet attrs) {

        super(context, attrs);

        init();
    }

    protected void init() {

        customEditTextView = findViewById(R.id.editTextViewID);
        textView = findViewById(R.id.textResultExpression);
        setSpecifications();
    }

    protected void setSpecifications() {

        customEditTextView.setTextSize(customEditTextView.getTextSize() - 10);
        customEditTextView.setShowSoftInputOnFocus(false);
    }

    // finally
    @Override
    public boolean onTextContextMenuItem(int id) {

        ClipboardManager clipboardMenu = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);

        if (id == android.R.id.paste
                && clipboardMenu != null
                && !clipboardMenu.getPrimaryClip().toString().equals(" ")) {

            customEditTextView.setText(clipboardMenu
                    .getPrimaryClip()
                    .getItemAt(0)
                    .getText().toString());

            return true;
        }

        return false;
    }

    @Override
    public void addTextChangedListener(TextWatcher watcher) {

        super.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }


            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (textView != null)
                    insertResultInTextView(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    protected void insertResultInTextView(@NonNull String data) {

        String fullExpression = data.replaceAll("\\s", "");
        String lastChar = lastChar(fullExpression);
        String expressionCalculate = "";

        if (!fullExpression.isEmpty()) {

            if (CharCheck.lastCharIsOperator(lastChar)) {

                RefactorExpression.LAST_INPUT_CHAR = lastChar;
            } else {

                try {

                    expressionCalculate = fullExpression;
                    String resultOperation = ResultExpression.calculateResultOperation(expressionCalculate);
                    String resultToTextView  = "= " + resultOperation;
                    textView.setText(resultToTextView);

                } catch (JepException e) {

                    String exception = "Invalid Expression!";
                    textView.setText(exception);

                    e.printStackTrace();
                }
            }
        }
    }

    // finally
    public String lastChar(@NonNull String expression) {

        return (expression.isEmpty()) ? "" : String.valueOf(expression.charAt(expression.length() - 1));
    }

    // finally
    public void setTextView(TextView textView) {

        this.textView = textView;
    }
}
