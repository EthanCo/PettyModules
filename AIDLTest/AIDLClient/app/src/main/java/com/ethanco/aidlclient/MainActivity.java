package com.ethanco.aidlclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ethanco.aidlservice.IBookManager;
import com.ethanco.aidlservice.IOnNewBookArrivedListener;
import com.ethanco.aidlservice.bean.Book;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Z-MainActivity";
    private IBookManager bookManager;
    private IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {

        @Override
        public void binderDied() {
            Log.i(TAG, "binderDied "+Thread.currentThread().getName());
            if (bookManager == null) {
                return;
            }
            bookManager.asBinder().unlinkToDeath(deathRecipient, 0);
            bookManager = null;
            //TODO:这里重写绑定远程Service
            bindMyBookService();
        }
    };

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i(TAG, "onServiceConnected " + Thread.currentThread().getName());
            bookManager = IBookManager.Stub.asInterface(iBinder);
            try {
                iBinder.linkToDeath(deathRecipient, 0); //0: 标记为，传0即可
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i(TAG, "onServiceDisconnected " + Thread.currentThread().getName());
            bookManager = null;
        }
    };
    private TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindMyBookService();

        tvInfo = (TextView) findViewById(R.id.tv_info);
        findViewById(R.id.btn_add_book).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (bookManager == null) {
                        Toast.makeText(MainActivity.this, "aidl已断开", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    bookManager.addBook(new Book(new Random().nextInt(), "小工专家"));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.btn_get_book).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (bookManager == null) {
                        Toast.makeText(MainActivity.this, "aidl已断开", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    List<Book> books = bookManager.getBookList();
                    StringBuilder sb = new StringBuilder();
                    for (Book book : books) {
                        sb.append("ID:" + book.bookId + " Name:" + book.bookName + "\r\n");
                    }
                    tvInfo.setText(sb.toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.btn_register_listener).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (bookManager == null) {
                        Toast.makeText(MainActivity.this, "aidl已断开", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    bookManager.registerListener(new IOnNewBookArrivedListener.Stub() {
                        @Override
                        public void onNewBookArrived(Book newBook) throws RemoteException {
                            Toast.makeText(MainActivity.this, "添加成功 ID:" + newBook.bookId, Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void bindMyBookService() {
        Intent intent = new Intent();
        intent.setAction("com.ethanco.aidlservice.mybook");
        intent.setPackage("com.ethanco.aidlservice");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        bindService(intent, conn, BIND_AUTO_CREATE);
    }
}
