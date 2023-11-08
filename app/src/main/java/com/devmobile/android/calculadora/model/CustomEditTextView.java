package com.devmobile.android.calculadora.model;

import android.content.ClipboardManager;
import android.content.Context;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.TextView;
import com.devmobile.android.calculadora.R;
import com.devmobile.android.calculadora.model.interfaces.DataInsertEditTextConverter;

public class CustomEditTextView extends androidx.appcompat.widget.AppCompatEditText {
    private CustomEditTextView idEditTextView;
    private TextView textView;

    public CustomEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView();
    }

    public void initView() {

        idEditTextView = findViewById(R.id.editTextViewID);
        idEditTextView.setTextSize(idEditTextView.getTextSize() - 10);
        idEditTextView.setShowSoftInputOnFocus(false);
    }

    @Override
    public boolean onTextContextMenuItem(int id) {
        ClipboardManager clipboardMenu = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);

        if (id == android.R.id.paste
                && clipboardMenu != null
                && !clipboardMenu.getPrimaryClip().toString().equals(" ")) {

            idEditTextView.setText(clipboardMenu
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

    public void insertResultInTextView(String partialExpression) {
        String expressionCalculate = "";

        partialExpression = partialExpression.replaceAll("\\s", "");

        if (!partialExpression.isEmpty()) {

            if (ExpressionInputCheck.isOperator(partialExpression)
                    && !String.valueOf(partialExpression.charAt(partialExpression.length() - 1)).equals("%")
                    && !String.valueOf(partialExpression.charAt(partialExpression.length() - 1)).equals("(")
                    && !String.valueOf(partialExpression.charAt(partialExpression.length() - 1)).equals(")")) {

                expressionCalculate = partialExpression;
                RefactorExpression.LAST_INPUT_CHAR = String.valueOf(partialExpression.charAt(partialExpression.length() - 1));

            } else {

                expressionCalculate = partialExpression;
                String resultOperation = ResultExpression.calculateResultOperation(expressionCalculate);

                if (resultOperation.equals("Invalid Expression!")) {

                    textView.setText(resultOperation);
                } else {
                    String result = "=" + resultOperation;
                    textView.setText(result);
                }
            }

        } else {
            textView.setText("=");
        }
    }

    public void setTextView(TextView textView) {

        this.textView = textView;
    }

}
