package com.example.newshub.chrometab;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by pitta on 22/3/2559.
 */
public class KeepAliveService extends Service {
    private static final Binder sBinder = new Binder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return sBinder;
    }
}
