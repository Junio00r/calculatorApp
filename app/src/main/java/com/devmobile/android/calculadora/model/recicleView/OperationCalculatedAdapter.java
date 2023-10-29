package com.devmobile.android.calculadora.model.recicleView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.devmobile.android.calculadora.R;
import com.devmobile.android.calculadora.model.OnItemClickListener;
import java.util.List;

/** Adapter é a ponte entre o entre o AdpaterView (RecyclerView) e os dados que serão
 colocados na visiulização (OperationCalculeted). E também é responsavel por fazer uma
 visiualização para cada um desses dados. O RecyclerView se comunica com esse Adapter e então
 o Adapter carrega/infla um determinado view holder  e é retornado para o recycler view a referencia
 dessa holder, mas ainda para definir os dados de uma determinada holder, o recycler view precisa
 comunicar-se com este adapter, e então o adapter faz a ponte transferindo os dados para a determinada
 ViewHolder.
 */
public class OperationCalculatedAdapter extends RecyclerView.Adapter<OperationViewHolder> {
    private List<OperationCalculated> operations;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public OperationCalculatedAdapter(List<OperationCalculated> operationsCalculated, Context context) {
        this.operations = operationsCalculated;
    }

    public void addItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    /**
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return OperationViewHolder Return a view holder created/inflated for the this one Adapter, and send to
     * RecyclerView that call this Adapter.
     */
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

        OperationCalculated operationRecyclateIndex = operations.get(position);
        ((OperationViewHolder) viewHolder).expression.setText(operationRecyclateIndex.getExpression());
        ((OperationViewHolder) viewHolder).resultExpression.setText(operationRecyclateIndex.getResultExpression());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClickItem(viewHolder.getAbsoluteAdapterPosition());
                }
            }
        });
    }

    /**
     *
     * @return Item's quantity in list of OperationCalculated
     */
    @Override
    public int getItemCount() {

        return operations.size();

    }

}
