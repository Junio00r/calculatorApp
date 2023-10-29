package com.devmobile.android.calculadora.model.viewPager2Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.devmobile.android.calculadora.R;
import com.devmobile.android.calculadora.model.OnButtonClickListener;

public class ExpensiveKeyboard extends Fragment
        implements View.OnClickListener, OnButtonClickListener {

    private OnButtonClickListener onButtonClickListener;
    private ImageButton buttonPercentExp;
    private ImageButton buttonOpenParenthesisExp;
    private ImageButton buttonCloseParenthesisExp;
    private ImageButton buttonBackSpaceExp;
    private ImageButton buttonDivisionExp;
    private ImageButton buttonMultiplicationExp;
    private ImageButton buttonSubtractionExp;
    private ImageButton buttonSumExp;
    private ImageButton buttonEqualsExp;
    private ImageButton buttonDownExp;
    private ImageButton buttonUpExp;
    private ImageButton buttonMenuExp;
    private Button buttonSeparatorExp;
    private Button buttonClearAllExp;
    private Button buttonZeroExp;
    private Button buttonOneExp;
    private Button buttonTwoExp;
    private Button buttonThreeExp;
    private Button buttonFourExp;
    private Button buttonFiveExp;
    private Button buttonSixExp;
    private Button buttonSevenExp;
    private Button buttonEightExp;
    private Button buttonNineExp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        /* 1 - resource – ID for an XML layout resource to load (e.g., R.layout.main_page)

         * 2 - root – Optional view to be the parent of the generated hierarchy (if attachToRoot is true),
         * or else simply an object that provides a set of LayoutParams values for root of the
         * returned hierarchy (if attachToRoot is false.).
         * The root parameter is typically used when you are inflating an individual view from a
         larger layout or when you want to specify a specific parent for the view you are inflating.

         * 3 - attachToRoot – Whether the inflated hierarchy should be attached to the root parameter?
         * If false, root is only used to create the correct subclass of LayoutParams for the root view in the XML
         */
        View viewInflater = inflater.inflate(R.layout.expensive_keyboard, container, false);
        initReferences(viewInflater);
        onClickButton(viewInflater);

        return viewInflater;

    }

    public void addOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
        this.onButtonClickListener = onButtonClickListener;
    }

    public void initReferences(View v) {
        buttonPercentExp = v.findViewById(R.id.buttonPercentExp);
        buttonOpenParenthesisExp = v.findViewById(R.id.buttonOpenParenthesisExp);
        buttonCloseParenthesisExp = v.findViewById(R.id.buttonCloseParenthesisExp);
        buttonBackSpaceExp = v.findViewById(R.id.buttonBackSpaceExp);
        buttonDivisionExp = v.findViewById(R.id.buttonDivisionExp);
        buttonMultiplicationExp = v.findViewById(R.id.buttonMultiplicationExp);
        buttonSubtractionExp = v.findViewById(R.id.buttonSubtractionExp);
        buttonSumExp = v.findViewById(R.id.buttonSumExp);
        buttonEqualsExp = v.findViewById(R.id.buttonEqualsExp);
        buttonDownExp = v.findViewById(R.id.buttonDownExp);
        buttonUpExp = v.findViewById(R.id.buttonUpExp);
        buttonClearAllExp = v.findViewById(R.id.buttonClearAllExp);
        buttonSeparatorExp = v.findViewById(R.id.buttonSeparatorExp);
        buttonZeroExp = v.findViewById(R.id.buttonZeroExp);
        buttonOneExp = v.findViewById(R.id.buttonOneExp);
        buttonTwoExp = v.findViewById(R.id.buttonTwoExp);
        buttonThreeExp = v.findViewById(R.id.buttonThreeExp);
        buttonFourExp = v.findViewById(R.id.buttonFourExp);
        buttonFiveExp = v.findViewById(R.id.buttonFiveExp);
        buttonSixExp = v.findViewById(R.id.buttonSixExp);
        buttonSevenExp = v.findViewById(R.id.buttonSevenExp);
        buttonEightExp = v.findViewById(R.id.buttonEightExp);
        buttonNineExp = v.findViewById(R.id.buttonNineExp);
    }

    @Override
    public void onClickButton(View viewWithButtons) {
        View[] buttons = {
                buttonPercentExp, buttonOpenParenthesisExp, buttonCloseParenthesisExp
                , buttonBackSpaceExp, buttonDivisionExp, buttonMultiplicationExp
                , buttonSubtractionExp, buttonSumExp, buttonEqualsExp, buttonDownExp, buttonUpExp
                , buttonClearAllExp, buttonSeparatorExp, buttonZeroExp, buttonOneExp, buttonTwoExp
                , buttonThreeExp, buttonFourExp, buttonFiveExp, buttonSixExp
                , buttonSevenExp, buttonEightExp, buttonNineExp
        };

        for (View e : buttons) {

            e.setOnClickListener(this);
        }
    }

    public void onClick(View viewClicked) {
        if (onButtonClickListener != null) {
            onButtonClickListener.onClickButton(viewClicked);
        }
    }
}
