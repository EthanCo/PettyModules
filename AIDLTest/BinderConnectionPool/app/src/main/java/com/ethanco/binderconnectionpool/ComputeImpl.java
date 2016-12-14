package com.ethanco.binderconnectionpool;

import android.os.RemoteException;

/**
 * Created by EthanCo on 2016/12/13.
 */

public class ComputeImpl extends ICompute.Stub {
    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }
}
