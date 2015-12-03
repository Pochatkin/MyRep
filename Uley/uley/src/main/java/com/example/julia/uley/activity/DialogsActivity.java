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
    public static final String APP_PREFERENCES_LASTMESS = "MessSettings";
    public static final String APP_PREFERENCES_COUNTER_LASTMESS = "MessCounter";
    private SharedPreferences mSettingsLogin;
    private SharedPreferences mSettingsLastMess;
    private Set<String> friedList;
    private Set<String> lastMessageList;

    ArrayList<Dialog> dialogs = new ArrayList<Dialog>();
    DialogsAdapter dialogAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogs_activity);
        mSettingsLogin = getSharedPreferences(APP_PREFERENCES_LOGIN, Context.MODE_PRIVATE);
        mSettingsLastMess = getSharedPreferences(APP_PREFERENCES_LASTMESS, Context.MODE_PRIVATE);


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
                intent.putExtra("key",position);  //Вторым параметром нужно передать логин
                startActivity(intent);
            }
        });

        onPause();
    }

    // генерируем данные для адаптера
    private void fillData() {
        onResume();
        String[] tempFriendList =  new String[friedList.size()];
        String[] tempLastMessList = new String[lastMessageList.size()];
        friedList.toArray(tempFriendList);
        lastMessageList.toArray(tempLastMessList);
        if (friedList.isEmpty()) {
            dialogs = new ArrayList<>();
        }
        else{
            for(int i = 0; i < friedList.size(); i++){
                Login tempLogin = new Login(tempFriendList[i]);
                Dialog tempDialog = new Dialog(tempLogin,tempLastMessList[i]);
                dialogs.add(i,tempDialog);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Запоминаем данные
        SharedPreferences.Editor editorLogin = mSettingsLogin.edit();
        SharedPreferences.Editor editorLastMess = mSettingsLastMess.edit();
        editorLogin.putStringSet(APP_PREFERENCES_COUNTER_LOGIN,friedList);
        editorLastMess.putStringSet(APP_PREFERENCES_COUNTER_LASTMESS, lastMessageList);
        editorLogin.apply();
        editorLastMess.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Вынимаем данные из памяти
        if (mSettingsLogin.contains(APP_PREFERENCES_COUNTER_LOGIN)) {
            friedList = mSettingsLogin.getStringSet(APP_PREFERENCES_COUNTER_LOGIN,friedList);  // Не понятен второй параметр
        }
        if(mSettingsLastMess.contains(APP_PREFERENCES_COUNTER_LASTMESS)){
            lastMessageList = mSettingsLastMess.getStringSet(APP_PREFERENCES_COUNTER_LASTMESS, lastMessageList);  // Так же не ясен второй параметр
        }
    }

    public void ConfirmSignOut() {
        DialogFragment newFragment = new SignOutDialogFragment();
        newFragment.show(getSupportFragmentManager(), "missiles");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.rooms, menu);
        MenuItem addItem = menu.findItem(R.id.action_add);
        MenuItem signoutItem = menu.findItem(R.id.action_sign_out);

        signoutItem.setOnMenuItemClickListener(
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
