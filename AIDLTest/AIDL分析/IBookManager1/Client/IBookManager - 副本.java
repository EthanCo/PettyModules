/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: F:\\VersionManger\\GitHub\\PettyModules\\PettyTest\\AIDLClient\\app\\src\\main\\aidl\\com\\ethanco\\aidlservice\\IBookManager.aidl
 */
package com.ethanco.aidlservice;

//继承了IInterface接口(所有可以在Binder中传输的接口都需要继承IInterface接口)
//同时自己也还是一个接口。这个接口的核心实现是它的内部类Stub和Stub的内部代理类Proxy
public interface IBookManager extends android.os.IInterface {
    /**
     * Local-side IPC implementation stub class.
	 * 声明的内部类，是一个Binder类
	 * 当客户端和服务器都位于同一个进程时，方法调用不会走跨进程的transact过程，
	 * 而当两者位于不同进程时，方法调用需要走transact过程，这个逻辑由Stub的内部代理类Proxy来完成
     */
    public static abstract class Stub extends android.os.Binder implements IBookManager {
        //Binder的唯一标识，一般用当前的Binder的类名表示
		private static final String DESCRIPTOR = "com.ethanco.aidlservice.IBookManager";

        /**
         * Construct the stub at attach it to the interface.
         */
        public Stub() {
            this.attachInterface(this, DESCRIPTOR);
        }

        /**
         * Cast an IBinder object into an com.ethanco.aidlservice.IBookManager interface,
         * generating a proxy if needed.
		 * 将服务端的Binder对象转换为客户端所需的AIDL接口类型的对象
		 * 这种转换过程是区分进程的:
		 * 1.如果客户端和服务端位于同一进程，那么此方法返回的就是服务端的Stub本身
		 * 2.否则，返回的是系统封装后的Stub.proxy对象
         */
        public static IBookManager asInterface(android.os.IBinder obj) {
            if ((obj == null)) {
                return null;
            }
            android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (((iin != null) && (iin instanceof IBookManager))) {
                return ((IBookManager) iin);
            }
            return new Proxy(obj);
        }

		/**
		 * 此方法用于返回当前Binder对象
		 */
        @Override
        public android.os.IBinder asBinder() {
            return this;
        }

		/** 对于服务器 **/
		/**
		 * 此方法运行在服务端的Binder线程池中
		 * 当客户端发起跨进程请求时，远程请求通过系统底层封装后，交由此方法来处理。
		 * 该方法的原型是public Boolean onTransact(int code,android.os.Parcel data,android.os.Parcel reply,int flags);
		 * 执行的步骤
		 * 1.服务端通过code可以确定客户端请求的目标方法是什么
		 * 2.通过data去除目标方法所需的参数(如果目标方法有返回值的话)
		 * 3.向reply中写入返回值(如果目标方法有返回值的话)
		 * 
		 * 如果该方法返回false，那么客户端请求就会失败。
		 * 因此我们可以利用这个特性来做权限验证，毕竟我们也不希望随便一个进程都能远程调用我们的服务
		 */
        @Override
        public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException {
            switch (code) {
                case INTERFACE_TRANSACTION: {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                case TRANSACTION_getBookList: {
                    data.enforceInterface(DESCRIPTOR);
                    java.util.List<com.ethanco.aidlservice.bean.Book> _result = this.getBookList();
                    reply.writeNoException();
                    reply.writeTypedList(_result);
                    return true;
                }
                case TRANSACTION_addBook: {
                    data.enforceInterface(DESCRIPTOR);
                    com.ethanco.aidlservice.bean.Book _arg0;
                    if ((0 != data.readInt())) {
                        _arg0 = com.ethanco.aidlservice.bean.Book.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    this.addBook(_arg0);
                    reply.writeNoException();
                    return true;
                }
            }
            return super.onTransact(code, data, reply, flags);
        }

        private static class Proxy implements IBookManager {
            private android.os.IBinder mRemote;

            Proxy(android.os.IBinder remote) {
                mRemote = remote;
            }

            @Override
            public android.os.IBinder asBinder() {
                return mRemote;
            }

            public String getInterfaceDescriptor() {
                return DESCRIPTOR;
            }

			//声明的方法
			/**运行在客户端**/
			/**
			 * 当客户端远程调用这个方法时，它的内部实现是这样的:
			 * 执行顺序:
			 * 1.首先创建该方法所需要的输入型Parcel对象_data、输出型Parcel对象_reply和返回值对象List
			 × 2.把该方法的参数信息写入_data中(如果有参数的话);
			 * 3.调用transact方法来发起RPC(远程过程调用)请求，同时当前线程挂起;
			 * 4.服务端的onTransact方法会被调用
			 * 5.直到RPC过程返回后，当前线程继续执行，并从_reply中取得RPC过程的返回结果
			 * 6.最后，返回_reply中的数据
			 */
            @Override
            public java.util.List<com.ethanco.aidlservice.bean.Book> getBookList() throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                java.util.List<com.ethanco.aidlservice.bean.Book> _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    mRemote.transact(Stub.TRANSACTION_getBookList, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.createTypedArrayList(com.ethanco.aidlservice.bean.Book.CREATOR);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }

			//声明的方法
			/**运行在客户端**/
			/**
			 * 执行过程和getBookList一样，addBook没有返回值，所以不需要从_reply中取出返回值
			 */
            @Override
            public void addBook(com.ethanco.aidlservice.bean.Book book) throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if ((book != null)) {
                        _data.writeInt(1);
                        book.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    mRemote.transact(Stub.TRANSACTION_addBook, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

		//声明整形的id用于识别 声明的方法，此id用于标识在transact过程中客户端到底请求的是哪个方法
        static final int TRANSACTION_getBookList = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
        static final int TRANSACTION_addBook = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
    }

    public java.util.List<com.ethanco.aidlservice.bean.Book> getBookList() throws android.os.RemoteException;

    public void addBook(com.ethanco.aidlservice.bean.Book book) throws android.os.RemoteException;
}
