package common.base;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import com.j256.ormlite.android.AndroidDatabaseResults;
import com.j256.ormlite.stmt.PreparedQuery;

import java.sql.SQLException;

/**
 * Created by shade_000 on 3/22/2016.
 */
public abstract class OrmliteCursorAdapter<T> extends CursorAdapter{

    //region Fields
    PreparedQuery<T> mQuery;
    //endregion

    //region Constructor
    public OrmliteCursorAdapter(Context context, Cursor c, PreparedQuery<T> query){
        super(context, c, false);
        mQuery = query;
    }
    //endregion

    //region Overrides

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        try
        {
            bindView(view, context,  mQuery.mapRow(new AndroidDatabaseResults(cursor, null)));
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    //endregion

    //region Set Methods

    public void setQuery(PreparedQuery<T> query)
    {
        mQuery = query;
    }

    //endregion

    //region Abstract Methods

    /**
     * Created convenient custom bindView for descendant to get Object itself
     */
    abstract public void bindView(View itemView, Context context, T item);

    abstract public View newView(Context context, Cursor cursor, ViewGroup viewGroup);

    //endregion
}
