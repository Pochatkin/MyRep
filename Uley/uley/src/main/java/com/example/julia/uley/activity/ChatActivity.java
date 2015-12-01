package com.example.julia.uley.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.example.julia.uley.*;
import com.example.julia.uley.common.Dialog;
import com.example.julia.uley.common.Package;
import com.example.julia.uley.adapter.ChatAdapter;
import com.example.julia.uley.common.Login;

import java.util.ArrayList;
import java.util.Set;

public class ChatActivity extends AppCompatActivity {
    public static final String APP_PREFERENCES_LOGIN = "LoginSettings";
    public static final String APP_PREFERENCES_COUNTER_LOGIN = "LoginCounter";
    public static final String APP_PREFERENCES_LASTMESS = "MessSettings";
    public static final String APP_PREFERENCES_COUNTER_LASTMESS = "MessCounter";
    private SharedPreferences mSettingsLogin;
    private SharedPreferences mSettingsLastMess;
    private Set<String> friedList;
    private Set<String> lastMessageList;
    ArrayList<String> chat = new ArrayList<>();
    ChatAdapter chatAdapter;
    Login Bob = new Login("Bob1");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);
        mSettingsLogin = getSharedPreferences(APP_PREFERENCES_LOGIN, Context.MODE_PRIVATE);
        mSettingsLastMess = getSharedPreferences(APP_PREFERENCES_LASTMESS, Context.MODE_PRIVATE);

        //ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        // progressBar.setVisibility(View.VISIBLE);

        // создаем адаптер
        fillData();
        chatAdapter = new ChatAdapter(this, chat);

        // настраиваем список
        ListView chatListView = (ListView) findViewById(R.id.messagesContainer);
        ((TextView) findViewById(R.id.companionLabel)).setText(Bob.toString());
        chatListView.setAdapter(chatAdapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    // генерируем данные для адаптера
    private void fillData() {
        // Нужно принять данные из DialogActivity и по ним восстановить историю сообщений
        String[] tempFriendList =  new String[friedList.size()];
        String[] tempLastMessList = new String[lastMessageList.size()];
        friedList.toArray(tempFriendList);
        lastMessageList.toArray(tempLastMessList);
        if (friedList.isEmpty()) {
            chat = new ArrayList<>();
        }
        else{
            for(int i = 0; i < friedList.size(); i++){
                chat.add(i,tempDialog);
            }
        }
    }

}