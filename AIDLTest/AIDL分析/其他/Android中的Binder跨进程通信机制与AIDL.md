# Android中的Binder跨进程通信机制与AIDL #

## Binder的4个主要模块 ##
Binder Client  

	相当于客户端PC

Binder Service  

	相当于服务器  

ServerManager  

	相当于DNS服务器   

Binder Driver  

	相当于一个路由器  

Binder Driver 在内核控件中，而其余3者在用户空间中  

### Binder Driver ###
位于内核控件中，其以字符设备中的misc类型注册，用户可以从/dev/binder 设备文件节点上 ，通过open和ioctl文件操作函数与Binder Driver进行通信，其**主要负责Binder通信的建立**，以及其**在进程间的传递和Binder引用计数管理/数据包的传递**等

### Binder Clinet 与 Binder Service ###
Binder Clinet 与 Binder Service 之间的跨进程通信则统一通过Binder Driver处理转发，对于Binder Clinet 来说，其只需要知道自己要使用的Binder的名字以及该Binder实体在ServerManager中的0号引用即可。访问的原理也必要简单，Binder Client 先是通过0号引用去访问ServerManager获取该Binder的引用，得到引用后就可以向普通方法调用那样调用Binder实体的方法。最后我们的ServerManager则用来管理Binder Server，Binder Clinet可以通过它来查询Binder Service接口，刚才我们说到Binder Clinet 可以通过ServerManager 来获取Binder的引用，这个Binder的引用就是由ServerManager来转换(映射)的，Binder Server在生成一个Binder实体的同时会为其绑定一个名字并将这个名字封装成一个数据包传递给Binder Driver，