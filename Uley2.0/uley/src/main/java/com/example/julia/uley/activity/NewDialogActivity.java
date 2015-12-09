package com.example.julia.uley.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.julia.uley.R;
import com.example.julia.uley.adapter.UsersAdapter;
import com.example.julia.uley.client.Client;
import com.example.julia.uley.common.Login;
import com.example.julia.uley.common.Package;
import com.example.julia.uley.common.PackageType;
import com.example.julia.uley.manager.ListenerManager;

import java.util.ArrayList;

/**
 * Created by Julia on 23.11.2015.
 */
public class NewDialogActivity extends AppCompatActivity {

    private ArrayList<Login> users = new ArrayList<>();
    private UsersAdapter usersAdapter;
    private Button searchButton;
    private Client client;
    private EditText SearchLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_new);

        SearchLogin = (EditText) findViewById(R.id.editText);

        searchButton = (Button) findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Package searchPackage = new Package(new Login(SearchLogin.getText().toString()));
                SearchTask searchTask = new SearchTask();
                searchTask.execute(searchPackage);
            }
        });


    }

    private class SearchTask extends AsyncTask<Package, Void, Package> {

        @Override
        protected Package doInBackground(Package... params) {
            client = Client.getInstance();
            Package tempPackage;
            try {
                client.send(params[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            ListenerManager listenerManager = new ListenerManager(client);
            tempPackage = listenerManager.listenerManager();
            tempPackage.setLogin(params[0].getLogin());
            return tempPackage;
        }

        @Override
        protected void onPostExecute(Package tempPackage){
            super.onPostExecute(tempPackage);
            if(tempPackage.getType() == PackageType.RESP_SEARCH_ANSWER){
                Intent intent = new Intent(NewDialogActivity.this,DialogsActivity.class);
                intent.putExtra("SearcherUser",tempPackage.getLogin().toString());
                startActivity(intent);
            }
            if(tempPackage.getType() == PackageType.RESP_MESSAGE_USER_NOT_FOUND){
                Toast.makeText(getBaseContext(), "User not found", Toast.LENGTH_LONG).show();
            }
        }
    }

    // генерируем данные для адаптера
    void fillData() {
//        for (int i = 0; i <= 20; i++) {
//            users.add(new Login("User" + Integer.toString(i)));
//        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        return true;
//    }


}
