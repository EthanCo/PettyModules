package com.ethanco.aidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.ethanco.aidlservice.bean.Book;

import java.util.ArrayList;
import java.util.List;

public class MyBookService extends Service {

    private List<Book> books = new ArrayList<>();
    private List<IOnNewBookArrivedListener> onNewBookArrivedListeners = new ArrayList<>();
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
            for (IOnNewBookArrivedListener onNewBookArrivedListener : onNewBookArrivedListeners) {
                onNewBookArrivedListener.onNewBookArrived(book);
            }
        }

        @Override
        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {
            onNewBookArrivedListeners.add(listener);
        }

        @Override
        public void unregisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
            onNewBookArrivedListeners.remove(listener);
        }
    }
}
