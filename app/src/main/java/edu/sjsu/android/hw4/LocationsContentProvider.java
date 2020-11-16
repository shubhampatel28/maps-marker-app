package edu.sjsu.android.hw4;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class LocationsContentProvider extends ContentProvider {

    static final String PROVIDER_NAME = "edu.sjsu.android.hw4";
    static final String URL = "content://" + PROVIDER_NAME + "/locationInfo";
    static final Uri CONTENT_URI = Uri.parse(URL);

    private static final int LOCATIONS = 1;
    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "locationInfo", LOCATIONS);
    }

    LocationsDB locationsDB;

    @Override
    public boolean onCreate() {
        locationsDB = new LocationsDB(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        if (uriMatcher.match(uri) == LOCATIONS) {
            return locationsDB.getLocations();
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long ID = locationsDB.insert(values);
        Uri _uri = null;
        if (ID > 0) {
            _uri = ContentUris.withAppendedId(CONTENT_URI, ID);
        } else {
            try {
                throw new SQLException("Failed to insert the record : " + uri);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return _uri;
    }


    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count = 0;
        count = locationsDB.delete();
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
