package com.devmobile.android.calculadora.model;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import com.devmobile.android.calculadora.R;
import com.devmobile.android.calculadora.model.interfaces.DataInsertEditTextConverter;

public class CustomEditTextConverter
        extends CustomEditTextView
        implements DataInsertEditTextConverter {

    private DataInsertEditTextConverter dataInsertEditTextConverter;

    public CustomEditTextConverter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void init() {

        customEditTextView = findViewById(R.id.editTextViewConverter);
        textView = findViewById(R.id.textViewConverter);
    }

    @Override
    protected void setSpecifications() {
       setTextSize(customEditTextView.getTextSize() - 10);
       setShowSoftInputOnFocus(false);
    }

    @Override
    public void insertResultInTextView(@NonNull String partialExpression) {
        String fullExpression = partialExpression.replaceAll("\\s", "");

        if (!fullExpression.isEmpty()) {
            this.dataInsertInEditTextToConverter(partialExpression);
        } else {
            textView.setText("");
        }
    }

    public void addDataInsertEditTextConverter(DataInsertEditTextConverter dataInsertEditTextConverter) {

        this.dataInsertEditTextConverter = dataInsertEditTextConverter;
    }

    @Override
    public void dataInsertInEditTextToConverter(String data) {

        if (this.dataInsertEditTextConverter != null)
            this.dataInsertEditTextConverter.dataInsertInEditTextToConverter(data);
    }
}
