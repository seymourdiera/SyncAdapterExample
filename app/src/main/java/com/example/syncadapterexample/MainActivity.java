package com.example.syncadapterexample;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.syncadapterexample.data.MyDatabase;
import com.example.syncadapterexample.entity.Person;
import com.example.syncadapterexample.entity.Person_Table;
import com.raizlabs.android.dbflow.sql.language.ConditionGroup;
import com.raizlabs.android.dbflow.structure.provider.ContentUtils;

public class MainActivity extends Activity{


    // CONSTANTES
    // tomado desde el provider declarado del manifest
    public static final String AUTHORITY = "com.example.syncadapterexample.provider";
    // tomado desde el authenticator.xml
    public static final String ACCOUNT_TYPE = "example.com";
    // nombre cualquiera para la cuent
    public static final String ACCOUNT = "dummyaccount";

    // CAMPOS DE INSTANCIA
    // Para almacenar la cuenta recuperada
    Account mAccount;
    Button saveButton;
    EditText nameEditText;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Crea la cuenta
        mAccount = createSyncAccount(this);

        // Retrieves data for filling the listView
        listView = (ListView) findViewById(R.id.listview);


        queryList();

        saveButton = (Button) findViewById(R.id.savebutton);

        nameEditText = (EditText) findViewById(R.id.nameedittext);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person person = new Person();
                person.setName(nameEditText.getText().toString());
                person.save();
                queryList();
            }
        });



    }

    private void queryList() {
        Cursor elementsCursor = ContentUtils.query(getContentResolver(), MyDatabase.Person.CONTENT_URI, ConditionGroup.clause().and(Person_Table.name.isNotNull()), null, new String[]{"_id", "name"});

        CursorAdapter cursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, elementsCursor, new String[]{"name"},new int[]{android.R.id.text1});

        listView.setAdapter(cursorAdapter);
    }

    /**
     * Crea una nueva cuenta inutil solo para que sea utilizada por el sync adapter
     *
     * @param context The application context
     */
    public static Account createSyncAccount(Context context) {

        // Create the account type and default account
        Account newAccount = new Account(
                ACCOUNT, ACCOUNT_TYPE);

        // Get an instance of the Android account manager
        AccountManager accountManager =
                (AccountManager) context.getSystemService(
                        ACCOUNT_SERVICE);
    /*
     * Add the account and account type, no password or user data
     * If successful, return the Account object, otherwise report an error.
     */
        if (accountManager.addAccountExplicitly(newAccount, null, null)) {
        /*
         * If you don't set android:syncable="true" in
         * in your <provider> element in the manifest,
         * then call context.setIsSyncable(account, AUTHORITY, 1)
         * here.
         */
        } else {
            Log.v(MainActivity.class.getName(), "La cuenta ya existe o ha ocurrido un error.");
        }
        return newAccount;
    }
}
