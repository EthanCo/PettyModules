# AIDL #
AIDL是一种接口描述语言，通常用于进程间通信。编译器根据AIDL文件生成一个一列对应的Java语言，通过预先定义的接口以及Binder机制达到进程间通信的目的。

	AIDL就是一个接口，客户端通过bindService来与远程服务器建立一个连接，  
	在该连接建立时会返回一个从服务器端传过来的Binder对象 (以下称为A)，  
	在建立连接时，客户端通过asInterface函数将 A 包装成本地的Proxy，(将 A 赋值给Proxy类的mRemote字段)  

## AIDL生成的java文件分析 ##
根据一个AIDL的Demo的AIDL生成的java文件进行分析  

	package com.ethanco.aidlservice;
	
	//根据SsoAuth.aidl生成的接口
	public interface SsoAuth extends android.os.IInterface
	{
		/** Stub类继承自Binder，并且实现了SsoAuth接口 */
		public static abstract class Stub extends android.os.Binder implements com.ethanco.aidlservice.SsoAuth
		{
			static final int TRANSACTION_basicTypes = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
			static final int TRANSACTION_ssoAuth = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
	
			private static final java.lang.String DESCRIPTOR = "com.ethanco.aidlservice.SsoAuth";
			
			/** Construct the stub at attach it to the interface. */
			public Stub()
			{
				this.attachInterface(this, DESCRIPTOR);
			}
			
			/**
			 * 将Binder转换为com.ethanco.aidlservice.SsoAuth接口或者包装为一个Proxy
			 */
			public static com.ethanco.aidlservice.SsoAuth asInterface(android.os.IBinder obj)
			{
				//查询本地的IInterface
				android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
				
				//判断obj参数的类型
				//如果iin不为空，并且是com.ethanco.aidlservice.SsoAuth类型(说明不是进程间的通信)，直接转换成SsoAuth返回
				if (((iin!=null)&&(iin instanceof com.ethanco.aidlservice.SsoAuth))) {
					return ((com.ethanco.aidlservice.SsoAuth)iin);
				}
				
				//否则，返回代理类Proxy
				//Proxy类也实现了SsoAuth接口，不同的是它是通过Binder机制来与远程进程进行交互，
				//它请求的类型是TRANSACTION_ssoAuth，参数分别为String类型的userName和pwd
				return new com.ethanco.aidlservice.SsoAuth.Stub.Proxy(obj);
			}
	
			@Override 
			public android.os.IBinder asBinder()
			{
				return this;
			}
	
			/** 对于服务器 **/
			//客户端的调用会通过Binder机制传递到服务器，最终调用Stub类中的onTransact函数  
			//可以看到，在case TRANSACTION_ssoAuth:处执行了this.ssoAuth()函数，意思是当接收到客户端的TRANSACTION_ssoAuth请求时，执行this.ssoAuth()函数
			//通过客户端的分析我们知道，当我们调用ssoAuth()时，实际上是通过mRemote向服务器提交了一个TRANSACTION_ssoAuth请求
			//因此，这两端通过Binder机制就对接上了
			@Override 
			public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
			{
				switch (code)
				{
					case INTERFACE_TRANSACTION:
					{
						reply.writeString(DESCRIPTOR);
						return true;
					}
					case TRANSACTION_basicTypes:
					{
						data.enforceInterface(DESCRIPTOR);
						int _arg0;
						_arg0 = data.readInt();
						long _arg1;
						_arg1 = data.readLong();
						boolean _arg2;
						_arg2 = (0!=data.readInt());
						float _arg3;
						_arg3 = data.readFloat();
						double _arg4;
						_arg4 = data.readDouble();
						java.lang.String _arg5;
						_arg5 = data.readString();
						this.basicTypes(_arg0, _arg1, _arg2, _arg3, _arg4, _arg5);
						reply.writeNoException();
						return true;
					}
					case TRANSACTION_ssoAuth:
					{
						data.enforceInterface(DESCRIPTOR);
						java.lang.String _arg0;
						_arg0 = data.readString();
						java.lang.String _arg1;
						_arg1 = data.readString();
						boolean _result = this.ssoAuth(_arg0, _arg1);
						reply.writeNoException();
						reply.writeInt(((_result)?(1):(0)));
						return true;
					}
				}
				return super.onTransact(code, data, reply, flags);
			}
	
			/*
			 * 本地代理，通过Binder与服务端的对象进行交互
			 */
			private static class Proxy implements com.ethanco.aidlservice.SsoAuth
			{
				private android.os.IBinder mRemote;
				
				Proxy(android.os.IBinder remote)
				{
					mRemote = remote;
				}
				
				@Override public android.os.IBinder asBinder()
				{
					return mRemote;
				}
				
				public java.lang.String getInterfaceDescriptor()
				{
					return DESCRIPTOR;
				}
				
				/**
					 * Demonstrates some basic types that you can use as parameters
					 * and return values in AIDL.
					 */
				@Override 
				public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, java.lang.String aString) throws android.os.RemoteException
				{
					android.os.Parcel _data = android.os.Parcel.obtain();
					android.os.Parcel _reply = android.os.Parcel.obtain();
					try {
						_data.writeInterfaceToken(DESCRIPTOR);
						_data.writeInt(anInt);
						_data.writeLong(aLong);
						_data.writeInt(((aBoolean)?(1):(0)));
						_data.writeFloat(aFloat);
						_data.writeDouble(aDouble);
						_data.writeString(aString);
						mRemote.transact(Stub.TRANSACTION_basicTypes, _data, _reply, 0);
						_reply.readException();
					}
					finally {
						_reply.recycle();
						_data.recycle();
					}
				}
				//实现SSO授权
	
				@Override 
				public boolean ssoAuth(java.lang.String userName, java.lang.String pwd) throws android.os.RemoteException
				{
					android.os.Parcel _data = android.os.Parcel.obtain();
					android.os.Parcel _reply = android.os.Parcel.obtain();
					boolean _result;
					try {
						_data.writeInterfaceToken(DESCRIPTOR);
						_data.writeString(userName);
						_data.writeString(pwd);
						mRemote.transact(Stub.TRANSACTION_ssoAuth, _data, _reply, 0);
						_reply.readException();
						_result = (0!=_reply.readInt());
					}
					finally {
						_reply.recycle();
						_data.recycle();
					}
					return _result;
				}
			}
		}
	
		/**
	     * Demonstrates some basic types that you can use as parameters
	     * and return values in AIDL.
	     */
		public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, java.lang.String aString) throws android.os.RemoteException;
	
		//实现SSO授权
		public boolean ssoAuth(java.lang.String userName, java.lang.String pwd) throws android.os.RemoteException;
	}


在客户端调用bindService，绑定成功后，会调用onServiceConnected(ComonentName name,IBinder service)，这里的service就是服务端返回的Binder对象，