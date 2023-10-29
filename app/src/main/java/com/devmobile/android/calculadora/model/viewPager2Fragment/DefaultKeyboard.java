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

public class DefaultKeyboard extends Fragment
        implements View.OnClickListener, OnButtonClickListener {
    private OnButtonClickListener onButtonClickListener;
    private ImageButton buttonPercent;
    private ImageButton buttonOpenParenthesis;
    private ImageButton buttonCloseParenthesis;
    private ImageButton buttonBackSpace;
    private ImageButton buttonDivision;
    private ImageButton buttonMultiplication;
    private ImageButton buttonSubtraction;
    private ImageButton buttonSum;
    private ImageButton buttonEquals;
    private ImageButton buttonDown;
    private ImageButton buttonUp;
    private ImageButton buttonMenu;
    private Button buttonSeparator;
    private Button buttonClearAll;
    private Button buttonZero;
    private Button buttonOne;
    private Button buttonTwo;
    private Button buttonThree;
    private Button buttonFour;
    private Button buttonFive;
    private Button buttonSix;
    private Button buttonSeven;
    private Button buttonEight;
    private Button buttonNine;

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
        View viewInflater = inflater.inflate(R.layout.default_keyboard, container, false);
        initReferences(viewInflater);
        onClickButton(viewInflater);

        return viewInflater;
    }

    public void addOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
        this.onButtonClickListener = onButtonClickListener;
    }

    public void initReferences(View v) {
        buttonPercent = v.findViewById(R.id.buttonPercent);
        buttonOpenParenthesis = v.findViewById(R.id.buttonOpenParenthesis);
        buttonCloseParenthesis = v.findViewById(R.id.buttonCloseParenthesis);
        buttonBackSpace = v.findViewById(R.id.buttonBackSpace);
        buttonDivision = v.findViewById(R.id.buttonDivision);
        buttonMultiplication = v.findViewById(R.id.buttonMultiplication);
        buttonSubtraction = v.findViewById(R.id.buttonSubtraction);
        buttonSum = v.findViewById(R.id.buttonSum);
        buttonEquals = v.findViewById(R.id.buttonEquals);
        buttonDown = v.findViewById(R.id.buttonDown);
        buttonUp = v.findViewById(R.id.buttonUp);
        buttonMenu = v.findViewById(R.id.buttonMenu);
        buttonClearAll = v.findViewById(R.id.buttonClearAll);
        buttonSeparator = v.findViewById(R.id.buttonSeparator);
        buttonZero = v.findViewById(R.id.buttonZero);
        buttonOne = v.findViewById(R.id.buttonOne);
        buttonTwo = v.findViewById(R.id.buttonTwo);
        buttonThree = v.findViewById(R.id.buttonThree);
        buttonFour = v.findViewById(R.id.buttonFour);
        buttonFive = v.findViewById(R.id.buttonFive);
        buttonSix = v.findViewById(R.id.buttonSix);
        buttonSeven = v.findViewById(R.id.buttonSeven);
        buttonEight = v.findViewById(R.id.buttonEight);
        buttonNine = v.findViewById(R.id.buttonNine);
    }

    @Override
    public void onClickButton(View viewWithButtons) {
        View[] buttons = {
                buttonPercent, buttonOpenParenthesis, buttonCloseParenthesis, buttonBackSpace
                , buttonDivision, buttonMultiplication, buttonSubtraction, buttonSum, buttonEquals
                , buttonDown, buttonUp, buttonMenu, buttonClearAll, buttonSeparator
                , buttonZero, buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix
                , buttonSeven, buttonEight, buttonNine
        };

        for (View e : buttons) {

            e.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View viewClicked) {
        if (onButtonClickListener != null) {
            onButtonClickListener.onClickButton(viewClicked);
        }
    }
}
