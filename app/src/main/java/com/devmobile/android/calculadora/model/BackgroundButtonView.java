package com.devmobile.android.calculadora.model;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.devmobile.android.calculadora.R;

public class BackgroundButtonView extends androidx.appcompat.widget.AppCompatButton {
    private final TypedArray typedArray;

    public BackgroundButtonView(Context context, AttributeSet attributes) {
        super(context, attributes);

        this.typedArray = context.obtainStyledAttributes(
                attributes, R.styleable.BackgroundButtonView, 0, 0
        );
    }
}