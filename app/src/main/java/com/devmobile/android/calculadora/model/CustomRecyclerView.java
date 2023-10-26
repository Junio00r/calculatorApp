package com.devmobile.android.calculadora.model;

import static android.os.Build.VERSION_CODES.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class CustomRecyclerView extends RecyclerView implements View.OnClickListener {
    private Context context;

    public CustomRecyclerView(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public CustomRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        System.out.println("Here!");;
    }
}
