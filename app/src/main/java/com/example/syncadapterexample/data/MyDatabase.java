package com.example.syncadapterexample.data;

import android.net.Uri;

import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.annotation.provider.ContentProvider;
import com.raizlabs.android.dbflow.annotation.provider.ContentUri;
import com.raizlabs.android.dbflow.annotation.provider.TableEndpoint;

/**
 * Created by mac on 4/21/16.
 */
@ContentProvider(authority = MyDatabase.AUTHORITY,
        database = MyDatabase.class,
        baseContentUri = MyDatabase.BASE_CONTENT_URI)
@Database(name = MyDatabase.NAME, version = MyDatabase.VERSION)
public class MyDatabase {

    public static final String NAME = "MyDatabase";

    public static final int VERSION = 3;

    public static final String AUTHORITY = "com.example.syncadapterexample.provider";

    // The scheme is always present and it represents
    public static final String BASE_CONTENT_URI = "content://";

    /**
     * Builds an URI from the paths array specified as params
     * @param paths
     * @return
     */
    private static Uri buildUri(String... paths) {
        Uri.Builder builder = Uri.parse(MyDatabase.BASE_CONTENT_URI + MyDatabase.AUTHORITY).buildUpon();
        for (String path : paths) {
            builder.appendPath(path);
        }
        return builder.build();
    }

    // Declare endpoints here
    @TableEndpoint(name = Person.ENDPOINT)
    public static class Person {

        public static final String ENDPOINT = "Person";

        @ContentUri(path = Person.ENDPOINT,
                type = ContentUri.ContentType.VND_MULTIPLE + ENDPOINT)
        public static final Uri CONTENT_URI = buildUri(ENDPOINT);
    }


}
