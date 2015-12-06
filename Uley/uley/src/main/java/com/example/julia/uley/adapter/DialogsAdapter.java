package com.example.julia.uley.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.julia.uley.common.Login;
import com.example.julia.uley.R;
import com.example.julia.uley.common.Dialog;

import java.util.ArrayList;

/**
 * Created by Julia on 20.11.2015.
 */
public class DialogsAdapter extends BaseAdapter {

    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Dialog> objects;

    public DialogsAdapter(Context context, ArrayList<Dialog> dialogs) {
        ctx = context;
        objects = dialogs;
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
            view = lInflater.inflate(R.layout.list_item_room, parent, false);
        }

        Dialog d = getDialog(position);
        Login Bob = new Login("Bob");

        // заполняем View в пункте списка данными из товаров: наименование, цена
        // и картинка
        ((TextView) view.findViewById(R.id.roomName)).setText(Bob.toString());
        ((TextView) view.findViewById(R.id.lastMessage)).setText(d.getLastMessage());
        return view;
    }

    // товар по позиции
    public Dialog getDialog(int position) {
        return ((Dialog) getItem(position));
    }

}
