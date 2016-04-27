package com.example.syncadapterexample;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;
import android.util.Log;

/**
 * utiliza el Android Sync Adapter framework para sincronizar la transferencia de informacion entre dispositivo y servidor
 * Created by mac on 21/3/16.
 */
public class SyncAdapter extends AbstractThreadedSyncAdapter {

    private static final String TAG  = SyncAdapter.class.getName();

    ContentResolver mContentResolver;

    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        mContentResolver = context.getContentResolver();
    }

    public SyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
        mContentResolver = context.getContentResolver();
    }

    // Aqui va el codigo que se ejecuta en el sync adapter. Corre en un hilo secundario, de manera que no es necesario crear un proceso background manualmente
    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
        // TODO agregar codigo para la data transfer
        Log.d(TAG, "onPerformSync: RUNNING SYNC ROUTINE!!!!!!!!!!!>>>>>>>>>>>>>>>>>>>>>>");
        // Aqui van tareas como:
        // Conectarse al servidor
        // Descargar y cargar data
        // Resolver conflictos de inconsistencia de data
        // Limpieza de recursos
    }
}
