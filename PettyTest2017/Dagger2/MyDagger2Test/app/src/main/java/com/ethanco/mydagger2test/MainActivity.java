package com.ethanco.mydagger2test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    Person person;
    @Inject
    ShoppingCartModel shoppingCart;
    private ActivityComponent activityComponent;
    private TextView tvInfo;
    private ContainerComponent containerComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //activityComponent = DaggerActivityComponent.builder().activityModule(new ActivityModule()).build();
        activityComponent = DaggerActivityComponent.builder().build();
        // person = activityComponent.person();
        containerComponent = DaggerContainerComponent.builder().activityComponent(activityComponent).build();
        containerComponent.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = (TextView) findViewById(R.id.tvInfo);
        //tvInfo.setText("姓名:" + person.getName() + " 年龄:" + person.getAge());
        tvInfo.setText("姓名:" + person.getName() + " 年龄:" + person.getAge() + " 购物清单:" + shoppingCart.getShoppingList());
    }
}
