package com.example.syncadapterexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Define un servicio que retorna un IBinder a la clase Sync Adapter, permitiéndole al framework de sincronizacion, llamar al método onPerformSync
 * Created by mac on 21/3/16.
 */
public class SyncService extends Service {

    private static final String TAG  = SyncService.class.getName();

    // Almacena el singleton de la clase syncAdapter que creamos
    // tiene que haber una sola instancia durante el ciclo de vida de la app
    private static SyncAdapter sSyncAdapter = null;

    // para threadSafe
    private static final Object sSyncAdapterLock = new Object();

    @Override
    public void onCreate() {
        synchronized (sSyncAdapterLock) {
            if (sSyncAdapter == null) {
                Log.d(TAG, "onCreate: CREANDO SYNCADAPTER");
                sSyncAdapter = new SyncAdapter(getApplicationContext(), true);
            }
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return sSyncAdapter.getSyncAdapterBinder();
    }
}
