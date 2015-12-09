package com.example.julia.uley.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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
import com.example.julia.uley.common.Pass;
import com.example.julia.uley.manager.ListenerManager;

import java.util.ArrayList;
import java.util.Set;

public class ChatActivity extends AppCompatActivity {
    public static final String APP_PREFERENCES_LOGIN = "LoginSettings";
    public static final String APP_PREFERENCES_COUNTER_LOGIN = "LoginCounter";
    public static final String APP_PREFERENCES_POSTHISTORY = "MessSettings";
    public static final String APP_PREFERENCES_COUNTER_POSTHISTORY = "MessCounter";
    private static Context context;
    private Button sendMessageButton;
    private SharedPreferences mSettingsLogin;
    private SharedPreferences mSettingsHistory;
    private Set<String> friedList;
    private Set<String> postHistoryList;
    ArrayList<String> chat = new ArrayList<>();
    ChatAdapter chatAdapter;
    //Login login = new Login(getIntent().getExtras().getString("key"));
    Login login = new Login("Bob");
    private EditText messange;
    private Client client;

    public static Context getContext() {
        return context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);

        try {
            client = (Client) getIntent().getSerializableExtra("client");
        } catch (Exception e) {
            e.printStackTrace();
        }
        mSettingsLogin = getSharedPreferences(APP_PREFERENCES_LOGIN, Context.MODE_PRIVATE);
        mSettingsHistory = getSharedPreferences(APP_PREFERENCES_POSTHISTORY, Context.MODE_PRIVATE);
        //ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        // progressBar.setVisibility(View.VISIBLE);


        // создаем адаптер
        fillData();
        chatAdapter = new ChatAdapter(this, chat);

        // настраиваем список
        final ListView chatListView = (ListView) findViewById(R.id.messagesContainer);
        ((TextView) findViewById(R.id.companionLabel)).setText(login.toString());
        chatListView.setAdapter(chatAdapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        messange = (EditText) findViewById(R.id.messageEdit);
        sendMessageButton = (Button) findViewById(R.id.chatSendButton);
        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tempString = messange.getText().toString();
                Package aPackage = new Package(tempString, new Login("T"));
//                Package loginInPackage = new Package(PackageType.REQ_SIGN_IN, new Login("M"), new Pass("123"));
                sendTask sendTask = new sendTask();
                sendTask.execute(aPackage);
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
//        onResume();
//        String[] tempFriendList = new String[friedList.size()];
//        String[] tempLastMessList = new String[postHistoryList.size()]; //Нужно придумать как сохранить коллекцию массивов
//        friedList.toArray(tempFriendList);
//        postHistoryList.toArray(tempLastMessList);
//        if (friedList.isEmpty()) {
//            chat = new ArrayList<>();
//        } else {
//            if (search(tempFriendList, login.toString()) != -1) {
//                //Получить
//            } else {
//
//            }
//        }
        ArrayList<String> temp = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            temp.add(i, "kek");
        }
        chat = temp;
    }

    private class sendTask extends AsyncTask<Package, Void, Package> {

        @Override
        protected void onPreExecute(){
            sendMessageButton.setVisibility(View.INVISIBLE);
        }


        @Override
        protected Package doInBackground(Package... params) {
            Package tempPackage = null;
            for (Package param : params) {
                try {
                    client = new Client(ChatActivity.this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    Package loginPackage = new Package(PackageType.REQ_SIGN_IN, new Login("M"), new Pass("123"));
                    client.send(loginPackage);

                    client.send(param);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("start listening");
                ListenerManager listenerManager = new ListenerManager(client);
                tempPackage = listenerManager.listenerManager();

            }
            return tempPackage;
        }

        @Override
        protected void onPostExecute(Package result) {
            super.onPostExecute(result);
            //TODO: Need post message on screen
            sendMessageButton.setVisibility(View.VISIBLE);
            System.out.println(result.getType() + "  KEK");
        }
    }

}