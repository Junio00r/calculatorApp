package com.devmobile.android.calculadora.model.recicleView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.devmobile.android.calculadora.R;
import java.util.List;

/* Adapter é a ponte entre o entre o AdpaterView (RecyclerView) e os dados que serão
colocados na visiulização (OperationCalculeted). E também é responsavel por fazer uma
visiualização para cada um desses dados.
 */
public class OperationCalculatedAdapter extends RecyclerView.Adapter<OperationViewHolder> {
    private List<OperationCalculated> operations;
    private Context context;
//    private View.OnClickListener clickListener;

    public OperationCalculatedAdapter(List<OperationCalculated> operationsCalculated, Context context) {
        this.operations = operationsCalculated;
        this.context = context;
    }

    @NonNull
    @Override
    public OperationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View operationView = layoutInflater.inflate(R.layout.calcules_historic, parent, false);

        return new OperationViewHolder(operationView);
    }

    /*
    Método responsável por reciclar e excluir os itens (ViewHolders) no RecyclerView.
    Ele se responsabiliza por trazer de volta os itens que não estavam sendo mostrado
    para o usuário. Ou seja, a medida que o user vai descendo (ou subindo), é esse
    método que será responsável por mostrar os itens e gerenciálos.
     */

    /**
     * @param viewHolder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull OperationViewHolder viewHolder, int position) {

        OperationViewHolder holder = (OperationViewHolder) viewHolder;
        OperationCalculated operationRecyclateIndex = operations.get(position);

        holder.expression.setText(operationRecyclateIndex.getExpression());
        holder.resultExpression.setText(operationRecyclateIndex.getResultExpression());

//        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                clickListener.onClick(view) ;
//            }
//        });
    }

    /**
     *
     * @return Item's quantity in list of OperationCalculated
     */
    @Override
    public int getItemCount() {

        return operations.size();

    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void removeOperationCalculated() {
        operations.remove(operations.size());
    }

}
