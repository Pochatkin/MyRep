package com.example.julia.uley.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.julia.uley.R;
import com.example.julia.uley.client.Client;
import com.example.julia.uley.common.Login;
import com.example.julia.uley.common.Package;
import com.example.julia.uley.common.PackageType;
import com.example.julia.uley.common.Pass;
import com.example.julia.uley.manager.ListenerManager;

/**
 * Created by Михаил on 05.12.2015.
 */
public class NewSignInActivity extends AppCompatActivity {

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private static Context context;
    private View mProgressView;
    private Client client;
    private Button SignInButton;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        try {
//            client = Client.deserialize(getIntent().getByteArrayExtra("client"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if(client == null){
//            System.out.println("null");
//        }
        setContentView(R.layout.activity_sign_in);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        // populateAutoComplete();

        mPasswordView = (EditText) findViewById(R.id.password);

        SignInButton = (Button) findViewById(R.id.email_sign_in_button);
        SignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Trying send Package to server
                try {
                    String email = mEmailView.getText().toString();
                    //email = email.trim();
                    String password = mPasswordView.getText().toString();
                    Package signInPackage = new Package(PackageType.REQ_SIGN_IN, new Login(email), new Pass(password));
                    SignInTask signInTask = new SignInTask();
                    signInTask.execute(signInPackage);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        TextView registerScreen = (TextView) findViewById(R.id.link_to_sign_up);

        // Listening to register new account link
        registerScreen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Switching to Register screen
                Intent i = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(i);
            }
        });
    }


    private class SignInTask extends AsyncTask<Package, Void, Package>{

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            SignInButton.setVisibility(View.INVISIBLE);
        }

        @Override
        protected Package doInBackground(Package... params) {
            client = Client.getInstance();
            try {
                client.send(params[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }

            ListenerManager listenerManager = new ListenerManager(client);
            Package tempPackage = listenerManager.listenerManager();
            return tempPackage;
        }

        @Override
        protected void onPostExecute(Package result){
            Package tempPackage = result;
            if (tempPackage.getType() == PackageType.RESP_SIGN_IN_FAILED) {
                Toast.makeText(getBaseContext(), "Sign in failed", Toast.LENGTH_LONG).show();
                SignInButton.setVisibility(View.VISIBLE);
            }
            if (tempPackage.getType() == PackageType.RESP_SIGN_IN_OK) {
                Intent intent = new Intent(NewSignInActivity.this, NewDialogActivity.class);
                SignInButton.setVisibility(View.VISIBLE);
                System.out.println("ok!");
                startActivity(intent);
                //System.out.println("ok!");
            }
        }

    }



//    private void populateAutoComplete() {
//        getLoaderManager().initLoader(0, null, this);
//    }


}
