### AIDL 传递对象集合 测试Demo  

在项目使用AIDL中，发现自己以前对于AIDL的理解有些误区。  
以前使用AIDL增加方法，只要iadl文件里的方法顺序不变，在所有的方法之后，增加新方法，新的AIDL是可以兼容原先的AIDL的。即用新的AIDL也可以和老的AIDL进行通讯 (只要不调用新增加的方法就不会有问题)。  
但是，在传递`LIst<Bean>`的过程中，发现，如果`Bean`的字段有增减，则新Aidl和老Aidl通讯会有问题，无法兼容。此时，Client端和Server端的`Bean`必须是同一个，不能有差别。   

详见[AIDL 传递对象集合 所遇到的问题](https://blog.csdn.net/EthanCo/article/details/82530566)