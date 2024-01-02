package com.devmobile.android.calculadora.model;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.devmobile.android.calculadora.Country;
import com.devmobile.android.calculadora.R;

public class BackgroundButtonView extends androidx.appcompat.widget.AppCompatButton {
    private final TypedArray typedArray;

    public BackgroundButtonView(Context context, AttributeSet attributes) {
        super(context, attributes);

        this.typedArray = context.obtainStyledAttributes(
                attributes, R.styleable.BackgroundButtonView, 0, 0
        );

        init(this);
    }

    public void init(Object testando) {

        if (testando != null
                && (this.getId() == R.id.buttonSeparator)
                || (this.getId() == R.id.buttonSeparatorExp)) {

            this.setText(Country.getDecimalSymbolSeparator());
            this.setContentDescription(Country.getDecimalSymbolSeparator());
        }
    }
}