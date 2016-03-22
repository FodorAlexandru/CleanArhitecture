package com.example.shade_000.cleanarhitecture;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.shade_000.cleanarhitecture.data.database.DatabaseHelper;
import com.example.shade_000.cleanarhitecture.data.models.User;

import common.base.BaseActivity;

public class MainActivity extends BaseActivity{

    //region Fields

    private String[][] data = {{"Gray Watson", "Gray.Watson@gmail.com"}, {"Ravi Verma", "Ravi.Verma@gmail.com"}, {"Ashok Singhal", "Ashok.Singhal@gmail.com"},{"Amit Yadav", "Amit.Yadav@gmail.com"}, {"Jake Wharton", "Jake.Wharton@gmail.com"}};

    //endregion

    //region Overrides

    @Override
    public int getResourceLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public int getTabLayoutId() {
        return R.id.activity_main_toolbar;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_load_data:
                loadInitData();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //endregion

    //region Methods

    private void loadInitData(){
            for(int i=0; i < data.length; i++) {
                User user = new User();
                user.setAlias(data[i][0]);
                user.setEmail(data[i][1]);
                DatabaseHelper.getInstance().addRow(user);
            }
    }

    //endregion
}
