package com.example.shade_000.cleanarhitecture;

import android.app.LoaderManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import common.base.BaseFragment;

/**
 * Created by shade_000 on 3/20/2016.
 */
public class MainFragment extends BaseFragment{

    //region Fields

    //endregion

    //region Overrides
    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initControls(view);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(),container,false);
    }

    //endregion

    //region Methods

    private void initControls(View view){

    }

    //endregion
}
