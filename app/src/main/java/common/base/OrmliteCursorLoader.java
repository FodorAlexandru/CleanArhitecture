package common.base;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;

import com.j256.ormlite.android.AndroidCompiledStatement;
import com.j256.ormlite.android.AndroidDatabaseResults;
import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.StatementBuilder;
import com.j256.ormlite.support.DatabaseConnection;

import java.sql.SQLException;

/**
 * Created by shade_000 on 3/21/2016.
 */
public class OrmliteCursorLoader<T> extends android.support.v4.content.AsyncTaskLoader<Cursor> {

    //region Fields

    private final ForceLoadContentObserver mObserver;
    private Cursor mCursor;
    private Dao<T, ?> mDao;
    private PreparedQuery<T> mQuery;

    //endregion

    //region Constructor

    public OrmliteCursorLoader(Context context, Dao<T, ?> dao, PreparedQuery<T> query) {
        super(context);
        mDao = dao;
        mQuery = query;
        mObserver = new ForceLoadContentObserver();
    }

    //endregion

    //region Overrides

    @Override
    public Cursor loadInBackground() {
        Cursor cursor = null;
        CloseableIterator<T> iterator = null;
        try {
            iterator = mDao.iterator(mQuery);
            // get the raw results which can be cast under Android
            AndroidDatabaseResults results =
                    (AndroidDatabaseResults) iterator.getRawResults();
            cursor = results.getRawCursor();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*try {
            DatabaseConnection readOnlyConn = mDao.getConnectionSource().getReadOnlyConnection();
            AndroidCompiledStatement stmt = (AndroidCompiledStatement) mQuery.compile(readOnlyConn, StatementBuilder.StatementType.SELECT);
            cursor = stmt.getCursor();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        if (cursor != null) {
            // Ensure the cursor window is filled
            cursor.getCount();
            registerContentObserver(cursor, mObserver);
        }

        return cursor;
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        cancelLoad();
    }

    @Override
    protected void onStartLoading() {
        if (mCursor != null) {
            deliverResult(mCursor);
        }

        if (takeContentChanged() || mCursor == null) {
            forceLoad();
        }
    }

    @Override
    protected void onReset() {
        super.onReset();

        onStopLoading();
        closeCursor(mCursor);

        mCursor = null;
    }

    @Override
    public void onCanceled(Cursor data) {
        closeCursor(data);
    }

    @Override
    public void deliverResult(Cursor data) {
        if (isReset()) {
            // Data is invalidated due to reset request
            if (data != null) {
                data.close();
            }
            return;
        }

        //Old data is being hold so it doesn't get garbage collected
        Cursor oldCursor = mCursor;
        mCursor = data;

        if (isStarted()) {
            super.deliverResult(data);
        }

        // Old data is invalidated due to not being needed
        if (oldCursor != null && oldCursor != data && !oldCursor.isClosed()) {
            oldCursor.close();
        }
    }

    //endregion

    //region Get Methods

    public Dao<T, ?> getDao() {
        return mDao;
    }

    public PreparedQuery<T> getQuery() {
        return mQuery;
    }

    //endregion

    //region Set Methods

    public void setQuery(PreparedQuery<T> mQuery) {
        this.mQuery = mQuery;
    }

    //endregion

    //region Methods

    private void closeCursor(Cursor cursor) {
        if (mCursor != null && !mCursor.isClosed())
            cursor.close();
    }

    /**
     * Registers an observer to get notifications from the content provider
     * when the cursor needs to be refreshed.
     */
    private void registerContentObserver(Cursor cursor, ContentObserver observer) {
        cursor.registerContentObserver(observer);
    }

    //endregion
}
