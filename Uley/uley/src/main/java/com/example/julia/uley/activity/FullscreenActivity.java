package com.example.julia.uley.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.julia.uley.R;

public class FullscreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

    }

    public void onSuccess() {
        // Go to Dialogs screen
        //
        Intent intent = new Intent(FullscreenActivity.this, SignInActivity.class);
        startActivity(intent);
        finish();
    }

}