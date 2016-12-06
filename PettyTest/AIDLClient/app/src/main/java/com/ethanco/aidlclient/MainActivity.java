package com.ethanco.aidlclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ethanco.aidlservice.IBookManager;
import com.ethanco.aidlservice.IOnNewBookArrivedListener;
import com.ethanco.aidlservice.bean.Book;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private IBookManager bookManager;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            bookManager = IBookManager.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            bookManager = null;
        }
    };
    private TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent();
        intent.setAction("com.ethanco.aidlservice.mybook");
        intent.setPackage("com.ethanco.aidlservice");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        bindService(intent, conn, BIND_AUTO_CREATE);

        tvInfo = (TextView) findViewById(R.id.tv_info);
        findViewById(R.id.btn_add_book).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
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
}
