package com.devmobile.android.calculadora.model;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.devmobile.android.calculadora.R;

public class CustomEditTextView extends androidx.appcompat.widget.AppCompatEditText {
    private final int distanceFirstBaseLineToTopHeight = 10;
    private final int quantityLinesEditText;
    private final boolean isHasLine = false;
    private final float sizeText = (float) 40.5;
    private CustomEditTextView idEditTextView;
    public TextView textView;
    private String expressionCalculate = "";

    public CustomEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomEditTextView);
        initView();

        try {
            quantityLinesEditText = typedArray.getInt(R.styleable.CustomEditTextView_editTestQuantityLines, 1);
        } finally {

            typedArray.recycle();
        }
    }


    @SuppressLint("ResourceType")
    public void initView() {

        idEditTextView = findViewById(R.id.editTextViewID);
        idEditTextView.setTextSize(45);
        idEditTextView.setLines(1);
        idEditTextView.setCursorVisible(true);
        idEditTextView.setShowSoftInputOnFocus(false);
        idEditTextView.setAutoSizeTextTypeUniformWithConfiguration(
                10,
                40,
                1,
                TypedValue.COMPLEX_UNIT_DIP
        );

        idEditTextView.getSelectionStart();
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

    @SuppressLint("SetTextI18n")
    private void insertResultInTextView(String partialExpression) {

        partialExpression = partialExpression.replaceAll("\\s", "");

        if (!partialExpression.isEmpty()) {

            if (ExpressionInputCheck.isOperator(partialExpression)
                    && !String.valueOf(partialExpression.charAt(partialExpression.length() - 1)).equals("%")
                    && !String.valueOf(partialExpression.charAt(partialExpression.length() - 1)).equals("(")
                    && !String.valueOf(partialExpression.charAt(partialExpression.length() - 1)).equals(")")) {

                expressionCalculate = partialExpression;
                ResultTextView.lastEspecialCharacter(String.valueOf(partialExpression.charAt(partialExpression.length() - 1)));

            } else {

                expressionCalculate = partialExpression;
                String resultOperation = ResultTextView.calculateResultOperation(expressionCalculate);

                if (resultOperation.equals("Invalid Expression!")) {

                    textView.setText(resultOperation);
                } else {

                    textView.setText("=" + resultOperation);
                }
            }

        } else {
            textView.setText("=");
        }
    }

    // Getters
    public int textSize() {
        return idEditTextView.getText().toString().length();
    }

    // Setters
    @Override
    public void setAutoSizeTextTypeUniformWithConfiguration(int autoSizeMinTextSize,
                                                            int autoSizeMaxTextSize,
                                                            int autoSizeStepGranularity,
                                                            int unit) {

        super.setAutoSizeTextTypeUniformWithConfiguration(autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);

    }

    @Override
    public void setTextSize(float size) {

        super.setTextSize(size);
    }

    @Override
    public void setMaxWidth(int maxPixels) {

        super.setMaxWidth(maxPixels);

    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    @Override
    public void setFirstBaselineToTopHeight(int firstBaselineToTopHeight) {

        super.setFirstBaselineToTopHeight(firstBaselineToTopHeight);

    }

    // Drawable de seleção de texto com o cursor
    @Override
    public void setTextSelectHandle(@NonNull Drawable textSelectHandle) {

        super.setTextSelectHandle(textSelectHandle);

    }
}
