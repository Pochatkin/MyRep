package com.example.root.testapp.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.root.testapp.R;

import java.sql.SQLOutput;

/**
 * Created by root on 27.01.17.
 */

public class WelcomeActivity extends AppCompatActivity {

    String email;
    String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarWelcome);
        setSupportActionBar(toolbar);
        final Context context = this;
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.registrFab);
        floatingActionButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = ((EditText) findViewById(R.id.registrEmail)).getText().toString().trim();
                pass = ((EditText) findViewById(R.id.registrPass)).getText().toString();

                /*Runnable runnable = new Runnable() {
                    @Override
                    public void run() {

                    }
                };
                Thread thread = new Thread(runnable);
                thread.start();*/
                Toast.makeText(context, "email: " + email + "   " + "pass: " + pass, Toast.LENGTH_LONG).show();
            }

        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_welcome);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}