package com.example.shade_000.cleanarhitecture.business.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shade_000.cleanarhitecture.R;
import com.example.shade_000.cleanarhitecture.data.models.User;
import com.j256.ormlite.stmt.PreparedQuery;

import common.base.OrmliteCursorAdapter;

/**
 * Created by shade_000 on 3/22/2016.
 */
public class UserAdapter extends OrmliteCursorAdapter<User> {

    //region Fields

    private LayoutInflater inflater;

    //endregion

    //region Constructor

    public UserAdapter(Context context, Cursor c, PreparedQuery<User> query) {
        super(context, c, query);
        inflater = LayoutInflater.from(context);
    }

    //endregion

    //region Overrides
    @Override
    public void bindView(View itemView, Context context, User item) {
        ViewHolder viewHolder = (ViewHolder)itemView.getTag();
        viewHolder.userAlis.setText(item.getAlias());
        viewHolder.userEmail.setText(item.getEmail());
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.adapter_user_item, null);
        view.setTag(getDefaultViewHolder(view));
        return view;
    }
    //endregion

    //region View Holders

    private class ViewHolder  {
        TextView userAlis;
        TextView userEmail;
    }

    //endregion

    //region Methods

    private ViewHolder getDefaultViewHolder(View view){
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.userAlis = (TextView)view.findViewById(R.id.adapter_user_item_alias);
        viewHolder.userEmail = (TextView)view.findViewById(R.id.adapter_user_item_email);
        return viewHolder;
    }

    //endregion
}
