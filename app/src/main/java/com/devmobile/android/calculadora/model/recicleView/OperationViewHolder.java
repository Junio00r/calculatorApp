package com.devmobile.android.calculadora.model.recicleView;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.devmobile.android.calculadora.R;

public class OperationViewHolder extends RecyclerView.ViewHolder {
    public TextView expression;
    public TextView resultExpression;

    public OperationViewHolder(@NonNull View itemView) {
        super(itemView);

        //Define click listener for the ViewHolder's View
        expression = itemView.findViewById(R.id.expressionHistoric);
        resultExpression = itemView.findViewById(R.id.resultExpressionHistoric);
    }

    public void setExpression(TextView expression) {
        this.expression = expression;
    }

    public void setResultExpression(TextView resultExpression) {
        this.resultExpression = resultExpression;
    }

    public TextView getExpression() {
        return expression;
    }

    public TextView getResultExpression() {
        return resultExpression;
    }
}
