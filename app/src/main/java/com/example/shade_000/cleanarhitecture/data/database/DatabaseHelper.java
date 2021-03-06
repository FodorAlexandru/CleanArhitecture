package com.example.shade_000.cleanarhitecture.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.shade_000.cleanarhitecture.data.models.User;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import common.base.ModelBase;
import common.constants.SqlConstants;

/**
 * Created by shade_000 on 3/20/2016.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    //region Fields

    private Dao<User, String> simpleDao = null;
    private static DatabaseHelper instance;

    //endregion

    //region Constructor

    private DatabaseHelper(Context context) {
        super(context, SqlConstants.DB_NAME, null, SqlConstants.DB_VERSION);
        //createDatabase(context);
    }

    //endregion

    //region Overrides

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onCreate");

            TableUtils.createTable(connectionSource, User.class);

        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");

            TableUtils.dropTable(connectionSource, User.class, true);
            onCreate(database);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        super.close();
        simpleDao = null;
    }

    //endregion

    //region Get Methods

    public static DatabaseHelper getInstance(){
        return instance;
    }

    public static void init(Context context){
        instance = new DatabaseHelper(context);
    }

    public Dao<User, String> getUserDao(){
        if (simpleDao == null) {
            try {
                simpleDao = getDao(User.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return simpleDao;
    }

    //endregion

    //region Methods

    public void createDatabase(Context context){
        DatabaseInitializer initializer = new DatabaseInitializer(context);
        initializer.createDatabase();
    }

    public void addRow(User user){
        try {
            getUserDao().create(user);
            Log.i(DatabaseHelper.class.getName(), "Db insert:" +user.toString());
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Db insert error:" + user.toString());
        }
    }

    //endregion
}
