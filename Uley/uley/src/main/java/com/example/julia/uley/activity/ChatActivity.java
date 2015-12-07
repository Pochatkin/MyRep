package com.example.julia.uley.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.julia.uley.R;
import com.example.julia.uley.adapter.ChatAdapter;
import com.example.julia.uley.client.Client;
import com.example.julia.uley.common.Login;
import com.example.julia.uley.common.Package;
import com.example.julia.uley.common.PackageType;

import java.util.ArrayList;
import java.util.Set;

public class ChatActivity extends AppCompatActivity {
    public static final String APP_PREFERENCES_LOGIN = "LoginSettings";
    public static final String APP_PREFERENCES_COUNTER_LOGIN = "LoginCounter";
    public static final String APP_PREFERENCES_POSTHISTORY = "MessSettings";
    public static final String APP_PREFERENCES_COUNTER_POSTHISTORY = "MessCounter";
    private static Context context;
    private SharedPreferences mSettingsLogin;
    private SharedPreferences mSettingsHistory;
    private Set<String> friedList;
    private Set<String> postHistoryList;
    ArrayList<String> chat = new ArrayList<>();
    ChatAdapter chatAdapter;
    Login login = new Login(getIntent().getExtras().getString("key"));
    private EditText message;

    public static Context getContext(){
        return context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);
        mSettingsLogin = getSharedPreferences(APP_PREFERENCES_LOGIN, Context.MODE_PRIVATE);
        mSettingsHistory = getSharedPreferences(APP_PREFERENCES_POSTHISTORY, Context.MODE_PRIVATE);
        //ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        // progressBar.setVisibility(View.VISIBLE);
        context = getBaseContext();


        // создаем адаптер
        fillData();
        chatAdapter = new ChatAdapter(this, chat);

        // настраиваем список
        ListView chatListView = (ListView) findViewById(R.id.messagesContainer);
        ((TextView) findViewById(R.id.companionLabel)).setText(login.toString());
        chatListView.setAdapter(chatAdapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        message = (EditText) findViewById(R.id.messageEdit);
        Button sendMessageButton = (Button) findViewById(R.id.chatSendButton);
        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //TODO: Send message ...
                    String temp = message.toString();
                    Package aPackage = new Package(temp, new Login("T"));
                    Client client = new Client(ChatActivity.this);
                    client.send(aPackage);
                    if(client.getPackage().getType() == PackageType.RESP_MESSAGE_IN_QUEUE){
                        //TODO: Need show message in chat and save message
                    }
                    if(client.getPackage().getType() == PackageType.RESP_MESSAGE_DELIVERED){
                        //TODO: Need show message in chat
                    }
                    if(client.getPackage().getType() == PackageType.RESP_MESSAGE_USER_NOT_FOUND){
                        //TODO: Push error message
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                onPause();
            }
        });



    }

    private int search(String[] list, String searcherElement) {
        int temp = -1;

        for (int i = 0; i < list.length; i++) {
            if (searcherElement.equals(list[i])) {
                temp = i;
                break;
            }
        }
        return temp;
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Запоминаем данные
        SharedPreferences.Editor editorLogin = mSettingsLogin.edit();
        SharedPreferences.Editor editorLastMess = mSettingsHistory.edit();
        editorLogin.putStringSet(APP_PREFERENCES_COUNTER_LOGIN, friedList);
        editorLastMess.putStringSet(APP_PREFERENCES_COUNTER_POSTHISTORY, postHistoryList);
        editorLogin.apply();
        editorLastMess.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Вынимаем данные из памяти
        if (mSettingsLogin.contains(APP_PREFERENCES_COUNTER_LOGIN)) {
            friedList = mSettingsLogin.getStringSet(APP_PREFERENCES_COUNTER_LOGIN, friedList);  // Не понятен второй параметр
        }
        if (mSettingsHistory.contains(APP_PREFERENCES_COUNTER_POSTHISTORY)) {
            postHistoryList = mSettingsHistory.getStringSet(APP_PREFERENCES_COUNTER_POSTHISTORY, postHistoryList);  // Так же не ясен второй параметр
        }
    }


    // генерируем данные для адаптера
    private void fillData() {
        onResume();
        String[] tempFriendList = new String[friedList.size()];
        String[] tempLastMessList = new String[postHistoryList.size()]; //Нужно придумать как сохранить коллекцию массивов
        friedList.toArray(tempFriendList);
        postHistoryList.toArray(tempLastMessList);
        if (friedList.isEmpty()) {
            chat = new ArrayList<>();
            chat.add("  ");
        } else {
            if (search(tempFriendList, login.toString()) != -1) {
                chat.add(tempLastMessList[search(tempFriendList,login.toString())]);
            } else {
                //TODO: Push error message
            }
        }

        //Testing data
//        ArrayList<String> temp = new ArrayList<>();
//        for(int i = 0; i < 20; i++){
//            temp.add(i,"kek");
//        }
//        chat = temp;
    }

}