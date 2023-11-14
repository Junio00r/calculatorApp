package com.devmobile.android.calculadora.model.viewPager2Fragment;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.devmobile.android.calculadora.model.interfaces.OnButtonClickListener;
import java.util.ArrayList;
import java.util.List;

public class ViewPagerKeyboardAdapter
        extends FragmentStateAdapter
        implements OnButtonClickListener {

    private OnButtonClickListener onButtonClickListener;
    private final List<Fragment> keyboardsFragments = new ArrayList<>();
    private final DefaultKeyboard defaultKeyboard = new DefaultKeyboard();
    private final ExpensiveKeyboard expensiveKeyboard = new ExpensiveKeyboard();

    public ViewPagerKeyboardAdapter(@NonNull FragmentActivity fragmentActivity) {

        super(fragmentActivity);
    }

    public void addOnButtonClickListener(OnButtonClickListener onButtonClickListener) {

        this.onButtonClickListener = onButtonClickListener;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        if (keyboardsFragments.isEmpty()) {

            keyboardsFragments.add(defaultKeyboard);
            keyboardsFragments.add(expensiveKeyboard);
            defaultKeyboard.addOnButtonClickListener(this);
            expensiveKeyboard.addOnButtonClickListener(this);
        }

        return keyboardsFragments.get(position);
    }


    @Override
    public int getItemCount() {

        return 2;
    }

    @Override
    public void onClickButton(View view) {

        if (onButtonClickListener != null) onButtonClickListener.onClickButton(view);
    }
}
