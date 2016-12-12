package com.ethanco.aidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import com.ethanco.aidlservice.bean.Book;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class MyBookService extends Service {

    private List<Book> books = new ArrayList<>();
    //RemoteCallbackList 系统专门提供的用于删除跨进程listener的接口。(普通的对象、接口通过AIDL获取的不是同一个对象)，故不能进行unregisterListener，而RemoteCallbackList可以
    //1.RemoteCallbackList根据AIDL底层的Binder对象是同一个的特性，遍历服务端所有的listener，从而找到具有相同Binder对象的服务端listener
    //2.当客户端进程终止后，它能够自动移除客户端所注册的listener
    private RemoteCallbackList<IOnNewBookArrivedListener> onNewBookArrivedListeners = new RemoteCallbackList<>();
    //private List<IOnNewBookArrivedListener> onNewBookArrivedListeners = new ArrayList<>();
    private BookManagerImpl bookManager = new BookManagerImpl();

    @Override
    public void onCreate() {
        super.onCreate();
        books.add(new Book(1001, "艺术开发探索"));
    }

    public MyBookService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return bookManager;
    }

    class BookManagerImpl extends IBookManager.Stub {

        @Override
        public List<Book> getBookList() throws RemoteException {
            return books;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            books.add(book);
            final int N = onNewBookArrivedListeners.beginBroadcast();
            for (int i = 0; i < N; i++) {
                IOnNewBookArrivedListener onNewBookArrivedListener = onNewBookArrivedListeners.getBroadcastItem(i);
                if (onNewBookArrivedListener != null) {
                    try {
                        onNewBookArrivedListener.onNewBookArrived(book);
                    } catch (Exception e) {
                        Log.e(TAG, e.getMessage());
                    }
                }
            }
            onNewBookArrivedListeners.finishBroadcast();
            /*new Thread() {
                @Override
                public void run() {
                    SystemClock.sleep(5000);
                    throw new IllegalArgumentException("xxxx format");
                }
            }.start();*/
        }

        @Override
        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {
            //onNewBookArrivedListeners.add(listener);
            onNewBookArrivedListeners.register(listener);
        }

        @Override
        public void unregisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
            //onNewBookArrivedListeners.remove(listener);
            onNewBookArrivedListeners.unregister(listener);
        }
    }
}
