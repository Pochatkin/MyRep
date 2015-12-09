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
import com.example.julia.uley.manager.ListenerManager;
import com.example.julia.uley.manager.ListenerREQManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
    private ListView chatListView;
    ArrayList<String> chat = new ArrayList<>();
    ChatAdapter chatAdapter;
    //Login login = new Login(getIntent().getExtras().getString("senderLogin"));
    Login login = new Login("T");
    private EditText messange;
    private Client client;
    private Map<Login, ArrayList<String>> trash = new HashMap<>();

    public static Context getContext() {
        return context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);

//        try {
//            client.deserialize(getIntent().getByteArrayExtra("client"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        mSettingsLogin = getSharedPreferences(APP_PREFERENCES_LOGIN, Context.MODE_PRIVATE);
        mSettingsHistory = getSharedPreferences(APP_PREFERENCES_POSTHISTORY, Context.MODE_PRIVATE);
        //ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        // progressBar.setVisibility(View.VISIBLE);


        // создаем адаптер
        fillData();
        chatAdapter = new ChatAdapter(this, chat);

        // настраиваем список
        chatListView = (ListView) findViewById(R.id.messagesContainer);
        ((TextView) findViewById(R.id.companionLabel)).setText(login.toString());
        chatListView.setAdapter(chatAdapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ListenTask listenTask = new ListenTask();
        listenTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        messange = (EditText) findViewById(R.id.messageEdit);
        sendMessageButton = (Button) findViewById(R.id.chatSendButton);
        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tempString = messange.getText().toString();
                Package aPackage = new Package(tempString, new Login("T"));
//                Package loginInPackage = new Package(PackageType.REQ_SIGN_IN, new Login("M"), new Pass("123"));
                sendTask sendTask = new sendTask();
                sendTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,aPackage);
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
        for (int i = 0; i < 3; i++) {
            temp.add(i, "kek");
        }
        chat = temp;
    }

    private class ListenTask extends AsyncTask<Void, Package, Package> {

        @Override
        protected void onProgressUpdate(Package... params) {
            super.onProgressUpdate(params);
            chat.add(params[0].getMessage().toString());
            chatAdapter = new ChatAdapter(ChatActivity.this, chat);
            ((TextView) findViewById(R.id.messageEdit)).setText("");
            chatListView.setAdapter(chatAdapter);
        }

        @Override
        protected Package doInBackground(Void... params) {
            client = Client.getInstance();
            Package tempPackage;
            ArrayList<String> tempList = new ArrayList<>();
            ListenerREQManager listenerREQManager = new ListenerREQManager(client);
            while (true) {
                tempPackage = listenerREQManager.listen();
                System.out.println(tempPackage.getType().toString());
                System.out.println(tempPackage.getLogin().toString());
                if (!tempPackage.getLogin().equals(login)) {
                    tempList.add(tempPackage.getMessage().toString());
                    trash.put(tempPackage.getLogin(), tempList);
                }
                if (tempPackage.getLogin().equals(login)) {
                    publishProgress(tempPackage);
                }
            }
        }
    }

    private class sendTask extends AsyncTask<Package, Void, Package> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            sendMessageButton.setVisibility(View.INVISIBLE);
        }


        @Override
        protected Package doInBackground(Package... params) {
            Package tempPackage = null;
            client = Client.getInstance();
            for (Package param : params) {
                try {
                    client.send(param);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ListenerManager listenerManager = new ListenerManager(client);
                tempPackage = listenerManager.listenerManager();
                System.out.println(tempPackage.getType().toString());

            }
            return tempPackage;
        }

        @Override
        protected void onPostExecute(Package result) {
            super.onPostExecute(result);
            if (result.getType() == PackageType.RESP_MESSAGE_IN_QUEUE ||
                    result.getType() == PackageType.RESP_MESSAGE_DELIVERED) {
                //TODO: Need post message on screen
                sendMessageButton.setVisibility(View.VISIBLE);
                chat.add(messange.getText().toString());
                chatAdapter = new ChatAdapter(ChatActivity.this, chat);
                ((TextView) findViewById(R.id.messageEdit)).setText("");
                chatListView.setAdapter(chatAdapter);
            }


//            System.out.println(result.getType() + "  KEK");
        }
    }

}