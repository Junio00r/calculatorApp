package com.devmobile.android.calculadora.model.recicleView;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.devmobile.android.calculadora.R;

public class OperationViewHolder extends RecyclerView.ViewHolder {
    public TextView expression;
    public TextView resultExpression;
    public View itemView;

    public OperationViewHolder(@NonNull View itemView) {
        super(itemView);

        this.itemView = itemView;
        this.expression = itemView.findViewById(R.id.expressionHistoric);
        this.resultExpression = itemView.findViewById(R.id.resultExpressionHistoric);
    }
}