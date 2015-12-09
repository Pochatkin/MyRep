package com.example.julia.uley.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.julia.uley.R;
import com.example.julia.uley.client.Client;

public class FullscreenActivity extends Activity {

    private Client client;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        FullScreenTask fullScreenTask = new FullScreenTask();
        fullScreenTask.execute();
        //TODO: Push client to next activity

    }

    public void setClient() {
        client = Client.getInstance();
        client.init(FullscreenActivity.this);
    }

    public Client getClient() {
        return client;
    }


    private class FullScreenTask extends AsyncTask<Void, Integer, Void> {


        @Override
        protected void onProgressUpdate(Integer... params) {
            super.onProgressUpdate(params);
            progressBar.setProgress(params[0]);

        }

        @Override
        protected Void doInBackground(Void... params) {
            setClient();
//            for (int i = 0; i < 100; i++) {
//                publishProgress(i);
//            }
            return null;
        }

        @Override
        protected void onPostExecute(Void param) {
            super.onPostExecute(param);
            Intent intent = new Intent(FullscreenActivity.this, NewSignInActivity.class);
            startActivity(intent);
        }

    }

}