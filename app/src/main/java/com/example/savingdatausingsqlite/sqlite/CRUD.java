package com.example.savingdatausingsqlite.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.savingdatausingsqlite.adapter.MyAdapter;
import com.example.savingdatausingsqlite.bean.ReadData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aruna on 1/14/18.
 */

public class CRUD {

    List <ReadData> itemIds;
    public MyAdapter adapter;

    public TextView tv_view;

    public RecyclerView recyclerView;
    EditText et_update_title, et_update_subtitle;

    public void insert(Context context, String title, String subTitle) {
        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(context);
        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, title);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, subTitle);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);

        Log.e("newRowId", String.valueOf(newRowId));

    }

    public void read(Context context) {
        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                FeedReaderContract.FeedEntry._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE,
                FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " = ?";
        String[] selectionArgs = { "Aruna" };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            String itemId = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry._ID));

            String title = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE));

            String subTitle = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE));

            ReadData readData = new ReadData(itemId,title,subTitle);
            itemIds.add(readData);

            Log.e("itemId", String.valueOf(itemId));
        }
        cursor.close();

        adapter = new MyAdapter(itemIds);



    }

    public void update(Context context, String id, String title,String subTitle) {
        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, title);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, subTitle);

// Which row to update, based on the title
        String selection = FeedReaderContract.FeedEntry._ID + " LIKE ?";
        String[] selectionArgs = { id };

        int count = db.update(
                FeedReaderContract.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        Log.e("update", String.valueOf(count));

    }

    public void delete(Context context, String id) {
        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        // Define 'where' part of query.
        String selection = FeedReaderContract.FeedEntry._ID + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { id };
        // Issue SQL statement.
        db.delete(FeedReaderContract.FeedEntry.TABLE_NAME, selection, selectionArgs);
    }

    public void closeConnection(Context context) {
        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(context);
        mDbHelper.close();

    }
}
