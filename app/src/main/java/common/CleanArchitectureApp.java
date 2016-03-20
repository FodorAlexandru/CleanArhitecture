package common;

import android.app.Application;

import com.example.shade_000.cleanarhitecture.data.database.DatabaseHelper;

/**
 * Created by shade_000 on 3/21/2016.
 */
public class CleanArchitectureApp extends Application {

    //region Overrides

    @Override
    public void onCreate() {
        super.onCreate();

        new DatabaseHelper(getApplicationContext());
    }


    //endregion

}
