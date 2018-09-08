// IMyAidlInterface.aidl
package com.heiko.aidltest;

import com.heiko.aidltest.bean.Person;
import com.heiko.aidltest.bean.RemotePlayList;

//import java.lang.Integer;

// Declare any non-default types here with import statements

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    //inout、out 需要自己实现readFromParcel方法，详见https://blog.csdn.net/flowingflying/article/details/22276821
    void operPerson(in Person person);

    //无法传递Integer等包装类 (没有实现Parcelable接口)
    //void integerType(in Integer aInteger);

    //传递
    RemotePlayList getLocalList();
}
