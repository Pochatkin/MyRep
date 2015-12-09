package com.example.julia.uley.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.julia.uley.R;
import com.example.julia.uley.SignOutDialogFragment;
import com.example.julia.uley.adapter.DialogsAdapter;
import com.example.julia.uley.client.Client;
import com.example.julia.uley.common.Dialog;
import com.example.julia.uley.common.Login;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Julia on 20.11.2015.
 */
public class DialogsActivity extends AppCompatActivity {
    public static final String APP_PREFERENCES_LOGIN = "LoginSettings";
    public static final String APP_PREFERENCES_COUNTER_LOGIN = "LoginCounter";
    public static final String APP_PREFERENCES_LASTMESSAGE = "MessageSettings";
    public static final String APP_PREFERENCES_COUNTER_LASTMESSAGE = "MessageCounter";
    private SharedPreferences mSettingsLogin;
    private SharedPreferences mSettingsLastMess;
    private Set<String> friendList;
    private Set<String> lastMessageList;
    private Client client;


    ArrayList<Dialog> dialogs = new ArrayList<Dialog>();
    DialogsAdapter dialogAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogs_activity);
        mSettingsLogin = getSharedPreferences(APP_PREFERENCES_LOGIN, Context.MODE_PRIVATE);
        mSettingsLastMess = getSharedPreferences(APP_PREFERENCES_LASTMESSAGE, Context.MODE_PRIVATE);

        client = Client.getInstance();
        // создаем адаптер
        fillData();
        dialogAdapter = new DialogsAdapter(this, dialogs);

        // настраиваем список
        ListView dialogsListView = (ListView) findViewById(R.id.roomsList);
        dialogsListView.setAdapter(dialogAdapter);
        dialogsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(DialogsActivity.this, ChatActivity.class);
                //TODO: MUST BE REPLACE, second parameter
                intent.putExtra("senderLogin", dialogs.get(position).getLogin());
                startActivity(intent);
            }
        });
        onPause();
    }


    @Override
    public void onBackPressed() {
    }

    // генерируем данные для адаптера
    private void fillData() {
        onResume();
        try {
            if (!getIntent().getExtras().getString("SearcherUser").equals(null)) {
                if (sizeLastMessageList() != 0 && sizeFriendList() != 0) {
                    String[] tempFriendList = new String[sizeFriendList() + 1];
                    String[] tempLastMessList = new String[sizeLastMessageList() + 1];
                    friendList.toArray(tempFriendList);
                    lastMessageList.toArray(tempLastMessList);
                    tempFriendList[sizeFriendList()] = getIntent().getExtras().getString("SearcherUser");
                    tempLastMessList[sizeLastMessageList()] = null;
                    if (friendList.isEmpty()) {
                        dialogs = new ArrayList<>();
                    } else {
                        for (int i = 0; i < friendList.size(); i++) {
                            Login tempLogin = new Login(tempFriendList[i]);
                            Dialog tempDialog = new Dialog(tempLogin, tempLastMessList[i]);
                            dialogs.add(i, tempDialog);
                        }
                    }

                } else {
                    dialogs = new ArrayList<>();
                }
            } else {
                if (sizeLastMessageList() != 0 && sizeFriendList() != 0) {
                    String[] tempFriendList = new String[sizeFriendList()];
                    String[] tempLastMessList = new String[sizeLastMessageList()];
                    friendList.toArray(tempFriendList);
                    lastMessageList.toArray(tempLastMessList);
                    if (friendList.isEmpty()) {
                        dialogs = new ArrayList<>();
                    } else {
                        for (int i = 0; i < friendList.size(); i++) {
                            Login tempLogin = new Login(tempFriendList[i]);
                            Dialog tempDialog = new Dialog(tempLogin, tempLastMessList[i]);
                            dialogs.add(i, tempDialog);
                        }
                    }

                } else {
                    dialogs = new ArrayList<>();
                }
            }
        } catch (Exception e) {
            if (sizeLastMessageList() != 0 && sizeFriendList() != 0) {
                String[] tempFriendList = new String[sizeFriendList()];
                String[] tempLastMessList = new String[sizeLastMessageList()];
                friendList.toArray(tempFriendList);
                lastMessageList.toArray(tempLastMessList);
                if (friendList.isEmpty()) {
                    dialogs = new ArrayList<>();
                } else {
                    for (int i = 0; i < friendList.size(); i++) {
                        Login tempLogin = new Login(tempFriendList[i]);
                        Dialog tempDialog = new Dialog(tempLogin, tempLastMessList[i]);
                        dialogs.add(i, tempDialog);
                    }
                }

            } else {
                dialogs = new ArrayList<>();
            }
        }
//        dialogs = new ArrayList<>();
//        Dialog tempDialog = new Dialog();
//        String tempString;
//        for(int i = 0; i < 10; i++){
//            tempString = "Login" + i;
//            tempDialog = new Dialog(new Login(tempString), "kek");
//            dialogs.add(i,tempDialog);
//        }
    }

    private Integer sizeFriendList() {
        try {
            return friendList.size();
        } catch (NullPointerException e) {
            return 0;
        }
    }

    private Integer sizeLastMessageList() {
        try {
            return lastMessageList.size();
        } catch (NullPointerException e) {
            return 0;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Вынимаем данные из памяти
        if (mSettingsLogin.contains(APP_PREFERENCES_COUNTER_LOGIN)) {
            friendList = mSettingsLogin.getStringSet(APP_PREFERENCES_COUNTER_LOGIN, friendList);  // Не понятен второй параметр
        }
        if (mSettingsLastMess.contains(APP_PREFERENCES_COUNTER_LASTMESSAGE)) {
            lastMessageList = mSettingsLastMess.getStringSet(APP_PREFERENCES_COUNTER_LASTMESSAGE, lastMessageList);  // Так же не ясен второй параметр
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Запоминаем данные
        SharedPreferences.Editor editorLogin = mSettingsLogin.edit();
        SharedPreferences.Editor editorLastMess = mSettingsLastMess.edit();
        editorLogin.putStringSet(APP_PREFERENCES_COUNTER_LOGIN, friendList);
        editorLastMess.putStringSet(APP_PREFERENCES_COUNTER_LASTMESSAGE, lastMessageList);
        editorLogin.apply();
        editorLastMess.apply();
    }


    public void ConfirmSignOut() {
        DialogFragment newFragment = new SignOutDialogFragment();
        newFragment.show(getSupportFragmentManager(), "missiles");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.rooms, menu);
        MenuItem addItem = menu.findItem(R.id.action_add);
        MenuItem signOutItem = menu.findItem(R.id.action_sign_out);

        signOutItem.setOnMenuItemClickListener(
                new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        ConfirmSignOut();
                        return false;

                    }

                }
        );

        addItem.setOnMenuItemClickListener(
                new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Intent intent = new Intent(DialogsActivity.this, NewDialogActivity.class);
                        startActivity(intent);
                        return false;

                    }
                });


        return true;
    }
}
