package com.example.fengh.recyclerviewtest;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.fengh.recyclerviewtest.databinding.ActivityRecyclerViewBindingBinding;

import java.util.Date;

public class RecyclerViewBindingActivity extends AppCompatActivity {
    private ActivityRecyclerViewBindingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recycler_view_binding);
        final ViewModel viewModel = new ViewModel();
        for (int i = 0; i < 20; i++) {
            viewModel.addItem("item" + i);
        }
        binding.setViewModel(viewModel);

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.addItem(new Date().toString());
            }
        });

        binding.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.removeItem();
            }
        });
    }
}
