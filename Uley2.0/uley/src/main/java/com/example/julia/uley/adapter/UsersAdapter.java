package com.example.julia.uley.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.julia.uley.R;
import com.example.julia.uley.common.Login;

import java.util.ArrayList;

/**
 * Created by Julia on 20.11.2015.
 */
public class UsersAdapter extends BaseAdapter {

    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Login> objects;

    public UsersAdapter(Context context, ArrayList<Login> users) {
        ctx = context;
        objects = users;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return objects.size();
    }
    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.list_item_user, parent, false);
        }

        Login d = getUser(position);

        // заполняем View в пункте списка данными из юзеров
        ((TextView) view.findViewById(R.id.userLogin)).setText(d.getLogin());
        return view;
    }

    // юзер по позиции
    public Login getUser(int position) {
        return ((Login) getItem(position));
    }

}
