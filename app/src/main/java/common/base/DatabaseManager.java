package common.base;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;

/**
 * Created by shade_000 on 3/20/2016.
 */
public class DatabaseManager<T extends OrmLiteSqliteOpenHelper> {
    //region Fields
    private T helper;
    //endregion

    //region Methods

    @SuppressWarnings("unchecked")
    public T getHelper(Context context){
        if(helper == null){
            helper = (T) OpenHelperManager.getHelper(context);
        }
        return helper;
    }

    public void releaseHelper(T helper)
    {
        if (helper != null) {
            OpenHelperManager.releaseHelper();
            helper = null;
        }
    }
    //endregion
}
