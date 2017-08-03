package com.ssjjsy.sdk.plugin.binder;


import android.os.RemoteException;

import com.qihoo360.replugin.base.IPC;
import com.sm.LogUtil;

/**
 * Created by Administrator on 2017/7/25.
 */

public class Data0Impl extends IData0.Stub {

    public static String name;
    public static Data0Info info;

    @Override
    public void setName(String name) throws RemoteException {
        LogUtil.i("Data0Impl in process: " + IPC.getCurrentProcessName());
        LogUtil.i("setName: " + name);
        Data0Impl.name = name;
    }

    @Override
    public void setData0Info(Data0Info info) throws RemoteException {
        LogUtil.i("Data0Impl in process: " + IPC.getCurrentProcessName());
        LogUtil.i("info.date: " + info.getDate());
        LogUtil.i("info.location: " + info.getLocation());
        LogUtil.i("info.behavior: " + info.getBehavior());
        Data0Impl.info = info;
    }
}
