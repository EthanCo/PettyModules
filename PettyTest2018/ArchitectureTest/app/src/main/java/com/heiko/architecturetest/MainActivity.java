package com.heiko.architecturetest;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.heiko.architecturetest.lifecycle.CallBack;
import com.heiko.architecturetest.lifecycle.TestObserver;
import com.heiko.architecturetest.lifecycle.User;
import com.heiko.architecturetest.room.AppDatabase;
import com.heiko.architecturetest.viewModel.RoomViewModel;

import java.util.Date;
import java.util.List;

/**
 * https://mp.weixin.qq.com/s/JCZGiv6sGZLvm-sVoZfSAw
 * http://mp.weixin.qq.com/s/rdRozpkXcz_5_QRWhTqLpQ
 * http://mp.weixin.qq.com/s/B7Os6xvKRkgthwV6G-uAKA
 * http://mp.weixin.qq.com/s/PnCQwctmWR9SYOWSe_TaKg
 * http://mp.weixin.qq.com/s/qdgzo5nlsZEFic77LCoV6A
 */
public class MainActivity extends AppCompatActivity {
    private TestObserver testObserver;
    public static final String TAG = "Z-MainActivity";
    private Button btnAddUser;
    private TextView tvInfo;
    private Button btnAddUserByRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddUser = (Button) findViewById(R.id.btn_add_user);
        tvInfo = (TextView) findViewById(R.id.tv_info);
        btnAddUserByRoom = (Button) findViewById(R.id.btn_add_user_by_name);

        testObserver = new TestObserver(this, new CallBack() {
            @Override
            public void start() {
                Log.e(TAG, "start");
            }

            @Override
            public void stop() {
                Log.e(TAG, "stop");
            }
        });

        getLifecycle().addObserver(testObserver);


        RoomViewModel model = ViewModelProviders.of(this).get(RoomViewModel.class);

        // model.getUsers().observeForever();
        // 通过observerForver(Observer) 去向一个不具备生命周期的对象注册观察者，
        // 此时观察者将被永远视为激活状态。可以通过removeObserver(Observer)移除观察者。
        model.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                // update UI
                //tvInfo.setText(String.valueOf(users.size()));
                AppDatabase _appDatabase = AppDatabase.getInMemoryDatabase(getApplication());
                List<User> uuu = _appDatabase.userModal().loadAllUsers();
                tvInfo.setText(String.valueOf(uuu.size())+"-"+users.size());
            }
        });

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "add User", Toast.LENGTH_SHORT).show();
                User user = new User(new Date().toString());
                model.addUser(user);
            }
        });

        btnAddUserByRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(new Date().toString());
                model.addUserByRoom(user);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getLifecycle().removeObserver(testObserver);
    }
}
