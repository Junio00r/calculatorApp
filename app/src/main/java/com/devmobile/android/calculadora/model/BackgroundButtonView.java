package com.devmobile.android.calculadora.model;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.RippleDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;

import com.devmobile.android.calculadora.R;

public class BackgroundButtonView extends androidx.appcompat.widget.AppCompatButton {
    private TypedArray typedArray;

    public BackgroundButtonView(Context context, AttributeSet attributes) {
        super(context, attributes);

        this.typedArray = context.obtainStyledAttributes(
                attributes, R.styleable.BackgroundButtonView, 0, 0
        );



    }

//    private void backgruondColorOnClick() {
//
//        setOnClickListener(v -> {
//            if (v.isPressed()) {
////                v.setBackgroundResource(R.drawable.button_background_pressed);
//            } else {
////                v.setBackgroundResource(R.drawable.button_background_pressed);
//            }
//
//
//            v.getAnimation();
//
//
//
//            v.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    v.setBackgroundResource(R.drawable.button_background_default);
//                }
//            }, 100);
//        });
//    }
}