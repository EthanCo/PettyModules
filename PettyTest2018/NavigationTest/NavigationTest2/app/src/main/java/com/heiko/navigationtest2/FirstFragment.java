package com.heiko.navigationtest2;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.navigation.Navigation;

public class FirstFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnGoSecond = (Button) view.findViewById(R.id.btn_go_second);
        btnGoSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Navigation.findNavController(getView()).navigate(R.id.action_nav_first_frag_to_nav_second_frag);
                //传递参数
                Bundle bundle = new Bundle();
                bundle.putString("KEY", "我是从 First 过来的");
                Navigation.findNavController(getView()).navigate(R.id.action_nav_first_frag_to_nav_second_frag, bundle);
            }
        });
    }
}
