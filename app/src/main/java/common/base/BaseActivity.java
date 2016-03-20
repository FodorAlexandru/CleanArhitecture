package common.base;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.shade_000.cleanarhitecture.R;

/**
 * Created by shade_000 on 3/20/2016.
 */
public abstract class BaseActivity extends AppCompatActivity {
    //region Overrides

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResourceLayoutId());
        Toolbar toolbar = (Toolbar) findViewById(getTabLayoutId());
        setSupportActionBar(toolbar);

    }
    //endregion
    //region Abstract Methods
    public abstract int getResourceLayoutId();

    public abstract int getTabLayoutId();
    //endregion
}
