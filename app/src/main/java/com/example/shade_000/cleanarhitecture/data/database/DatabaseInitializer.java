package com.example.shade_000.cleanarhitecture.data.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import common.constants.SqlConstants;

/**
 * Created by shade_000 on 3/20/2016.
 */
public class DatabaseInitializer extends SQLiteOpenHelper {

    //region Fields


    //endregion

    //region Constructors

    protected DatabaseInitializer(Context context) {
        super(context, SqlConstants.DB_NAME, null, SqlConstants.DB_VERSION);
    }

    //endregion

    //region Overrides
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
    //endregion

    //region Methods

    protected void createDatabase(){
        getWritableDatabase();
    }

   /* private boolean checkDatabase(){
        SQLiteDatabase checkDb = null;

        try{
            String dbPath = context.getDatabasePath(SqlConstants.DB_NAME).getPath();
            checkDb = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);
        }
        catch(SQLiteException ignored) {
        }

        if(checkDb !=null){
            checkDb.close();
            return true;
        }

        return false;
    }*/

    /*private void copyDatabase() throws IOException {

        InputStream myInput = context.getAssets().open(DB_NAME);

        String outFileName = DB_PATH + DB_NAME;

        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();

    }*/

    //endregion
}
