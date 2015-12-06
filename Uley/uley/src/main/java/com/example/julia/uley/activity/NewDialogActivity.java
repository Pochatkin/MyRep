package com.example.julia.uley.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.julia.uley.R;
import com.example.julia.uley.adapter.UsersAdapter;
import com.example.julia.uley.common.Login;

import java.util.ArrayList;

/**
 * Created by Julia on 23.11.2015.
 */
public class NewDialogActivity extends AppCompatActivity {
    ArrayList<Login> users = new ArrayList<>();
    UsersAdapter usersAdapter;
    SearchView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_new);

        //ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        // progressBar.setVisibility(View.VISIBLE);


        //Нужно че-то делать с поиском.  хз, что это ниже )
        search=(SearchView) findViewById(R.id.search);
        search.setQueryHint("SearchView");

        //*** setOnQueryTextFocusChangeListener ***
        search.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub

                Toast.makeText(getBaseContext(), String.valueOf(hasFocus),
                        Toast.LENGTH_SHORT).show();
            }
        });

        //*** setOnQueryTextListener ***
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                // TODO Auto-generated method stub

                Toast.makeText(getBaseContext(), query,
                        Toast.LENGTH_SHORT).show();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // TODO Auto-generated method stub

                //	Toast.makeText(getBaseContext(), newText,
               // Toast.LENGTH_SHORT.show();
                return false;
            }
        });





        // создаем адаптер
        fillData();
        usersAdapter = new UsersAdapter(this, users);

        // настраиваем список
        ListView userslistview = (ListView) findViewById(R.id.usersList);
        userslistview.setAdapter(usersAdapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        userslistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(NewDialogActivity.this, ChatActivity.class);
                startActivity(intent);

                /*Toast.makeText(getApplicationContext(),
                        "Вы выбрали " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();*/
            }
        });


        }
    // генерируем данные для адаптера
    void fillData() {
        for (int i = 0; i <= 20; i++) {
            users.add(new Login("User" + Integer.toString(i)));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }



}
