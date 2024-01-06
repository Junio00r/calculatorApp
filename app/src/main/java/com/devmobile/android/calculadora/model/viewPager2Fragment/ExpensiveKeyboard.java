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
import com.devmobile.android.calculadora.model.interfaces.OnButtonClickListener;
import java.util.stream.Stream;

public class ExpensiveKeyboard
        extends Fragment
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
    private Button buttonSeparatorExp;
    private Button buttonClearAllExp;
    private Button buttonTanExp;
    private Button buttonCosExp;
    private Button buttonSenExp;
    private Button buttonLogExp;
    private ImageButton buttonExpoenteExp;
    private ImageButton buttonFactorialExp;
    private ImageButton buttonRaizQuadradaExp;
    private Button buttonEulerExp;
    private ImageButton buttonPiExp;

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
    private static ExpensiveKeyboard instance;
    private ExpensiveKeyboard() { }

    public static ExpensiveKeyboard getInstance() {

        if (instance != null) {
            return instance;
        }

        return instance = new ExpensiveKeyboard();
    }

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

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("button_click_listener", onButtonClickListener);

    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null) {
            onButtonClickListener =
                    (OnButtonClickListener) savedInstanceState.getSerializable("button_click_listener");
        }
    }

    public void initReferences(@NonNull View v) {

        // symbols
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
        buttonTanExp = v.findViewById(R.id.buttonTanExp);
        buttonCosExp = v.findViewById(R.id.buttonCosExp);
        buttonSenExp = v.findViewById(R.id.buttonSenExp);
        buttonLogExp = v.findViewById(R.id.buttonLogExp);
        buttonExpoenteExp = v.findViewById(R.id.buttonExpoenteExp);
        buttonFactorialExp = v.findViewById(R.id.buttonFactorialExp);
        buttonRaizQuadradaExp = v.findViewById(R.id.buttonRaizQuadradaExp);
        buttonEulerExp = v.findViewById(R.id.buttonEulerExp);
        buttonPiExp = v.findViewById(R.id.buttonPiExp);

        // numbers
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
                , buttonThreeExp, buttonFourExp, buttonFiveExp, buttonSixExp, buttonSevenExp
                , buttonEightExp, buttonNineExp, buttonTanExp, buttonCosExp, buttonSenExp
                , buttonLogExp, buttonExpoenteExp, buttonFactorialExp, buttonRaizQuadradaExp
                , buttonEulerExp, buttonPiExp
        };

        Stream.of(buttons).forEach(button -> button.setOnClickListener(this));
    }

    public void onClick(View viewClicked) {

        if (onButtonClickListener != null) onButtonClickListener.onClickButton(viewClicked);
    }
}
