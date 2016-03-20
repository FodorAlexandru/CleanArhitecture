package com.example.shade_000.cleanarhitecture;

import common.base.BaseActivity;

public class MainActivity extends BaseActivity{

    //region Overrides

    @Override
    public int getResourceLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public int getTabLayoutId() {
        return R.id.activity_main_toolbar;
    }

    //endregion

}
