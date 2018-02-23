package com.heiko.rxjavatest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding2.widget.RxTextView;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;


/**
 * Item1
 * 当账号和密码都不为空时，提交按钮可用
 */
public class Item1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item1);

        EditText et1 = (EditText) findViewById(R.id.editText);
        EditText et2 = (EditText) findViewById(R.id.editText2);
        final Button btnLogin = (Button) findViewById(R.id.btn_login);

        Observable<CharSequence> et1Change = RxTextView.textChanges(et1).skip(1);
        Observable<CharSequence> et2Change = RxTextView.textChanges(et2).skip(1);

        Observable.combineLatest(et1Change, et2Change, new BiFunction<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean apply(CharSequence et1Text, CharSequence et2Text) throws Exception {
                boolean isEt1Normal = !TextUtils.isEmpty(et1Text);
                boolean isEt2Normal = !TextUtils.isEmpty(et2Text);

                return isEt1Normal & isEt2Normal;
            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                btnLogin.setEnabled(aBoolean);
            }
        });
    }
}
