package com.devmobile.android.calculadora;

import android.content.Context;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.TextView;
import com.devmobile.android.calculadora.model.CustomEditTextView;
import com.devmobile.android.calculadora.model.RefactorExpression;
import com.devmobile.android.calculadora.model.interfaces.DataInsertEditTextConverter;

public class CustomEditTextConverter extends CustomEditTextView
    implements DataInsertEditTextConverter {

    private CustomEditTextView idEditTextViewConverter;
    private DataInsertEditTextConverter dataInsertEditTextConverter;

    public CustomEditTextConverter(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView();
    }

    @Override
    public void initView() {

        idEditTextViewConverter = findViewById(R.id.editTextViewConverter);
        idEditTextViewConverter.setShowSoftInputOnFocus(false);
    }

    @Override
    public void addTextChangedListener(TextWatcher watcher) {
        super.addTextChangedListener(watcher);
    }

    @Override
    public boolean onTextContextMenuItem(int id) {

        return super.onTextContextMenuItem(id);
    }

    @Override
    public void insertResultInTextView(String partialExpression) {
        this.dataInsertInEditTextToConverter(partialExpression);
    }

    @Override
    public void setTextView(TextView textView) {
        super.setTextView(textView);
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
