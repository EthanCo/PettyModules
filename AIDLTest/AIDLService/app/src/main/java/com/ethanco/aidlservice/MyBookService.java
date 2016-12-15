package com.ethanco.aidlservice;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import com.ethanco.aidlservice.bean.Book;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MyBookService extends Service {

    public static final String TAG = "Z-MyBookService";
    //CopyOnWriteArrayList支持并发读/写    (类似的还有ConcurrentHashMap)
    //AIDL方法是在服务端的Binder线程池中执行的，因此当多个客户端同时连接时，会存在多个线程同时访问的情形，所以我们要在AIDL方法中处理线程同步，而我们这里直接使用CopyOnWriteArrayList来进行自动的线程同步
    private CopyOnWriteArrayList<Book> books = new CopyOnWriteArrayList<>();
    //private List<Book> books = new ArrayList<>();


    //RemoteCallbackList 系统专门提供的用于删除跨进程listener的接口。(普通的对象、接口通过AIDL获取的不是同一个对象)，故不能进行unregisterListener，而RemoteCallbackList可以
    //1.RemoteCallbackList根据AIDL底层的Binder对象是同一个的特性，遍历服务端所有的listener，从而找到具有相同Binder对象的服务端listener
    //2.当客户端进程终止后，它能够自动移除客户端所注册的listener
    //3.RemoteCallbackList内部自动实现了线程同步的功能，所以我们使用它来注册和解除注册的时候，不需要做额外的线程同步工作。
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
            //beginBroadcast和finishBroadcast必须成对出现，哪怕是获取RemoteCallbackList里的个数
            final int N = onNewBookArrivedListeners.beginBroadcast();
            for (int i = 0; i < N; i++) {
                IOnNewBookArrivedListener onNewBookArrivedListener = onNewBookArrivedListeners.getBroadcastItem(i);
                if (onNewBookArrivedListener != null) {
                    try {
                        onNewBookArrivedListener.onNewBookArrived(book);
                    } catch (Exception e) {
                        Log.e(ContentValues.TAG, e.getMessage());
                    }
                }
            }
            onNewBookArrivedListeners.finishBroadcast();

            throw new RuntimeException("xxxx format");
            //throw new IllegalArgumentException("xxxx format");
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

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            //此处可进行权限检查
            String[] packages = getPackageManager().getPackagesForUid(getCallingUid());
            String packageName = null;
            if (packages != null && packages.length > 0) {
                packageName = packages[0];
            }
            Log.i(TAG, "packageName:" + packageName);
            return super.onTransact(code, data, reply, flags);
        }
    }
}
