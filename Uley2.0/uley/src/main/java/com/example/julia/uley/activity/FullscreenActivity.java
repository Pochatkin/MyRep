package com.example.julia.uley.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.julia.uley.R;
import com.example.julia.uley.client.Client;

public class FullscreenActivity extends Activity {

    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
//        try {
//            setClient();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        Intent intent = new Intent(FullscreenActivity.this, NewSignInActivity.class);
        startActivity(intent);
    }

    public void setClient() throws Exception {
        this.client = new Client(FullscreenActivity.this);
    }

    public Client getClient() {
        return client;
    }

    public void onSuccess() {
        // Go to Dialogs screen
        //
        Intent intent = new Intent(FullscreenActivity.this, SignInActivity.class);
        startActivity(intent);
        finish();
    }

}