package com.devmobile.android.calculadora.model.spinner;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.devmobile.android.calculadora.ActivityConverters;
import com.devmobile.android.calculadora.R;
import com.devmobile.android.calculadora.model.interfaces.OnItemClickListener;
import com.devmobile.android.calculadora.model.interfaces.OnItemSpinnerListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomSpinnerAdapter
        extends SimpleAdapter
        implements AdapterView.OnItemSelectedListener
        , OnItemSpinnerListener {
    private List<? extends Map<String, ?>> items;
    private final int FIRST_SPINNER_ID = R.id.firstSpinner;
    private final int SECOND_SPINNER_ID = R.id.secondSpinner;
    private String firstSpinnerItemSelectedAbbreviation;
    private String secondSpinnerItemSelectedAbbreviation;
    private OnItemSpinnerListener onItemSpinnerListener;

    /**
     * Constructor
     *
     * @param context  The context where the View associated with this SimpleAdapter is running
     * @param items    A List of Maps. Each entry in the List corresponds to one row in the list. The
     *                 Maps contain the data for each row, and should include all the entries specified in
     *                 "from"
     * @param resource Resource identifier of a view layout that defines the views for this list
     *                 item. The layout file should include at least those named views defined in "to"
     * @param from     A list of column names that will be added to the Map associated with each
     *                 item.
     * @param to       The views that should display column in the "from" parameter. These should all be
     *                 TextViews. The first N views in this list are given the values of the first N columns
     *                 in the from parameter.
     */
    public CustomSpinnerAdapter(Context context, List<? extends Map<String, ?>> items, int resource, String[] from, int[] to) {
        super(context, items, resource, from, to);
        this.items = items;

        init();
    }
    public void addOnItemSpinnerSelected(OnItemSpinnerListener onItemSpinnerListener) {
        this.onItemSpinnerListener = onItemSpinnerListener;
    }

    private void init() {

        this.setDropDownViewResource(R.layout.spinner_drop_down_item);
    }

    @Override
    public void setDropDownViewResource(int resource) {

        super.setDropDownViewResource(resource);
    }

    /**
     * @param parent   The AdapterView where the selection happened
     * @param view     The view within the AdapterView that was clicked
     * @param position The position of the view in the adapter
     * @param id       The row id of the item that is selected
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (parent.getId() == FIRST_SPINNER_ID)
            this.firstSpinnerItemSelectedAbbreviation = items.get(position).get("abbreviation").toString();

        if (parent.getId() == SECOND_SPINNER_ID)
            this.secondSpinnerItemSelectedAbbreviation = items.get(position).get("abbreviation").toString();

        spinnerItemSelected(this.firstSpinnerItemSelectedAbbreviation, this.secondSpinnerItemSelectedAbbreviation);
    }

    /**
     * When isn't more been clickable, the parent pass to here.
     *
     * @param parent The AdapterView that now contains no selected item.
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    public void spinnerItemSelected(String firstSpinnerItemSelected, String secondSpinnerItemSelected) {
        if (this.onItemSpinnerListener != null)
            this.onItemSpinnerListener.spinnerItemSelected(this.firstSpinnerItemSelectedAbbreviation
            , this.secondSpinnerItemSelectedAbbreviation);
    }
}

