package com.devmobile.android.calculadora.model.viewPager2Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.devmobile.android.calculadora.R;

public class ExpensiveKeyboard extends Fragment {

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
        return inflater.inflate(
                R.layout.expensive_keyboard, container, false
        );
    }
}
