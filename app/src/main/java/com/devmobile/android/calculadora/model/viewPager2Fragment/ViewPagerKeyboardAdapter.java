package com.devmobile.android.calculadora.model.viewPager2Fragment;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.devmobile.android.calculadora.model.interfaces.OnButtonClickListener;

import java.util.ArrayList;

public class ViewPagerKeyboardAdapter
        extends FragmentStateAdapter
        implements OnButtonClickListener {

    private OnButtonClickListener onButtonClickListener;

    public ViewPagerKeyboardAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public void addOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
        this.onButtonClickListener = onButtonClickListener;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        DefaultKeyboard defaultKeyboard;
        ExpensiveKeyboard expensiveKeyboard;

        if (position == 0) {
            defaultKeyboard = new DefaultKeyboard();
            defaultKeyboard.addOnButtonClickListener(this);

            return defaultKeyboard;
        } else {
            expensiveKeyboard = new ExpensiveKeyboard();
            expensiveKeyboard.addOnButtonClickListener(this);

            return expensiveKeyboard;
        }
    }


    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public void onClickButton(View view) {
        if (onButtonClickListener != null) {
            onButtonClickListener.onClickButton(view);
        }
    }
}
