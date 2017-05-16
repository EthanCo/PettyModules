package com.ethanco.contentprovidertest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * TODO
 *
 * @author EthanCo
 * @since 2017/5/16
 */

public class ExampleProvider extends ContentProvider {
    public static final String AUTHORITY = "com.ethanco.provider";
    private UriMatcher matcher;

    @Override
    public boolean onCreate() {
        //authority
        matcher = new UriMatcher(UriMatcher.NO_MATCH);// 创建内容提供者时执行
        matcher.addURI(AUTHORITY, "version", 1);// 设置一个Uri,如果匹配到person返回1
        matcher.addURI(AUTHORITY, "abc", 2);// 如果匹配到abc返回2
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        MatrixCursor cursor = new MatrixCursor(new String[]{"data"});
        switch (matcher.match(uri)) {
            case 1:
                cursor.addRow(new Object[]{1});
                break;
            case 2:
                cursor.addRow(new Object[]{2});
                break;
            default:
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
