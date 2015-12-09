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
    ArrayList<Dialog> dialogs;

    public DialogsAdapter(Context context, ArrayList<Dialog> dialogs) {
        ctx = context;
        this.dialogs = dialogs;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return dialogs.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return dialogs.get(position);
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
        if (dialogs != null) {
            if (dialogs.size() != 0) {
                String lastMessage = getDialog(position).getLastMessage();
                Login login = getDialog(position).getLogin();

                // заполняем View в пункте списка данными из товаров: наименование, цена
                // и картинка
                ((TextView) view.findViewById(R.id.roomName)).setText(login.toString());
                ((TextView) view.findViewById(R.id.lastMessage)).setText(lastMessage.toString());

            }
        }
        return view;
    }


    // товар по позиции
    public Dialog getDialog(int position) {
        return ((Dialog) getItem(position));
    }

}
