package com.devmobile.android.calculadora.model;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class CustomRecyclerView extends RecyclerView {
    private Context context;

    public CustomRecyclerView(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public CustomRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }
}
