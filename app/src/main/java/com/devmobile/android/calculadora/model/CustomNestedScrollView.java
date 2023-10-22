package com.devmobile.android.calculadora.model;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Scroller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.LiveData;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.RecyclerView;

import com.devmobile.android.calculadora.R;
import com.google.android.material.appbar.AppBarLayout;

public class CustomNestedScrollView extends NestedScrollView {
    private Scroller mScroller;
    private float y1;
    private float y2;
    private AppBarLayout appBarLayout;

    public CustomNestedScrollView(@NonNull Context context) {
        super(context);
        init();
    }

    public CustomNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
        mScroller = new Scroller(context);
        init();
    }

    private void init() {


    }

//    @Override
//    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
////        teste();
//
//        if (t < oldt && findViewById(R.id.recycle_view_historic).getHeight() <= 700) {
//            findViewById(R.id.recycle_view_historic).invalidate();
//
//        } else if (findViewById(R.id.customScrolling).getTop() == 0) {
//            super.onScrollChanged(l, t, oldl, oldt);
//            findViewById(R.id.customScrolling).invalidate();
//        }
//    }


//    public void teste() {
//        NestedScrollView scrollView = findViewById(R.id.customScrolling); // Substitua pelo seu ScrollView
//        LinearLayout linearLayout = findViewById(R.id.layoutScrolling);
//
//        Rect scrollBounds = new Rect();
//        scrollView.getHitRect(scrollBounds); // Obtém os limites visíveis do ScrollView
//        for (int i = 0; i < scrollView.getChildCount(); i++) {
//            findViewById(R.id.recycle_view_historic).isVisibleToUserForAutofill(i);
//            View childView = scrollView.getChildAt(i);
//
//            // Verifica se a visualização está dentro da área visível do ScrollView
//            if (childView.getLocalVisibleRect(scrollBounds)) {
//                // A visualização está visível na tela
//                // Faça o que precisar com a visualização visível
//            } else {
//                computeScrollDeltaToGetChildRectOnScreen(scrollBounds);
//
//            }
//        }
//    }
}
