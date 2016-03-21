package com.example.shade_000.cleanarhitecture;

import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;

import com.example.shade_000.cleanarhitecture.business.adapters.UserAdapter;
import com.example.shade_000.cleanarhitecture.data.database.DatabaseHelper;
import com.example.shade_000.cleanarhitecture.data.models.User;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import common.base.BaseFragment;
import common.base.OrmliteCursorLoader;

/**
 * Created by shade_000 on 3/20/2016.
 */
public class MainFragment extends BaseFragment implements android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor>{

    //region Fields
    UserAdapter userAdapter;
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
        initLoader();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(),container,false);
    }

    @Override
    public android.support.v4.content.Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        Dao<User, String> userDao = DatabaseHelper.getInstance().getUserDao();
        try {
            return new OrmliteCursorLoader<>(getContext(), userDao, userDao.queryBuilder().prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<Cursor> loader, Cursor cursor) {
        CursorAdapter _adapter = userAdapter;
        if(_adapter != null && cursor != null){
            _adapter.swapCursor(cursor);
        }
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<Cursor> loader) {
        CursorAdapter _adapter = userAdapter;
        if(_adapter != null){
            _adapter.swapCursor(null);
        }
    }

    //endregion

    //region Methods

    private void initControls(View view){
        ListView listView = (ListView) view.findViewById(R.id.fragment_main_list_view);
        try {
            userAdapter = new UserAdapter(getContext(),null,DatabaseHelper.getInstance().getUserDao().queryBuilder().prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        listView.setAdapter(userAdapter);
    }

    private void initLoader(){
        getLoaderManager().initLoader(1,null,this);
    }

    //endregion
}
