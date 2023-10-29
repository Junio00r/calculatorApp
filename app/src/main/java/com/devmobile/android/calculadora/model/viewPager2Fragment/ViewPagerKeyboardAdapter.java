package com.devmobile.android.calculadora.model.viewPager2Fragment;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerKeyboardAdapter
        extends FragmentStateAdapter {

    public ViewPagerKeyboardAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0)
            return new DefaultKeyboard();
        else
            return new ExpensiveKeyboard();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
