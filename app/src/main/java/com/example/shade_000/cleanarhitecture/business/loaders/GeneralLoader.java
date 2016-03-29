package com.example.shade_000.cleanarhitecture.business.loaders;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;

import com.example.shade_000.cleanarhitecture.data.models.eventBuss.DataChangedEvent;
import com.j256.ormlite.android.AndroidDatabaseResults;
import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.sql.SQLException;

import common.base.AbstractLoader;
import common.base.DisableableContentObserver;
import common.constants.EventBussConstants;

/**
 * Created by shade_000 on 3/27/2016.
 */
public class GeneralLoader<T> extends AbstractLoader<Cursor> {
    //region Fields
    private Cursor mCursor;
    private Dao<T, ?> mDao;
    private PreparedQuery<T> mQuery;
    private boolean mIsRegistered;
    //endregion

    //region Constructor
    public GeneralLoader(Context context, Dao<T, ?> dao, PreparedQuery<T> query) {
        super(context);
        this.mDao = dao;
        this.mQuery = query;
    }
    //endregion

    //region Overrides

    @Override
    public Cursor loadInBackground() {
        try {
            CloseableIterator<T> iterator = mDao.iterator(mQuery);
            // get the raw results which can be cast under Android
            AndroidDatabaseResults results =
                    (AndroidDatabaseResults) iterator.getRawResults();
            mCursor = results.getRawCursor();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mCursor;
    }

    @Override
    protected void onNewDataDelivered(Cursor data) {
        super.onNewDataDelivered(data);
        if (!mIsRegistered) {
            mIsRegistered = true;
            registerObserver();
        }
    }

    @Override
    protected void onAbandon() {
        unregisterObserver();
        mIsRegistered = false;
    }

    @Override
    protected void onReset() {
        unregisterObserver();
        mIsRegistered = false;
        super.onReset();
    }

    @Subscribe
    public void handleEventSticky(DataChangedEvent dataChangedEvent){
        switch (dataChangedEvent.getMessage()){
            case EventBussConstants.UserDataChangedEvent:
                onContentChanged();
                break;
        }

        EventBus.getDefault().removeStickyEvent(dataChangedEvent);
    }

    @Override
    protected void releaseResources(Cursor result) {
        if(result!=null && !result.isClosed()){
            result.close();
        }
    }

    protected void registerObserver() {
        EventBus.getDefault().register(this);
    }

    protected void unregisterObserver() {
        EventBus.getDefault().unregister(this);
    }
}
