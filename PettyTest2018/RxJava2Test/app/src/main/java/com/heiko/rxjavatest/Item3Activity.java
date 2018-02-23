package com.heiko.rxjavatest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class Item3Activity extends AppCompatActivity {

    private EditText et3;
    private TextView tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item3);

        et3 = (EditText) findViewById(R.id.editText3);
        tv3 = (TextView) findViewById(R.id.textView3);

        RxTextView.textChanges(et3)
                .debounce(1, TimeUnit.SECONDS).skip(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                        tv3.setText(charSequence);
                    }
                });
    }
}
