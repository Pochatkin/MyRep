package com.example.julia.uley.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.julia.uley.R;
import com.example.julia.uley.client.Client;
import com.example.julia.uley.common.Login;
import com.example.julia.uley.common.Package;
import com.example.julia.uley.common.PackageType;
import com.example.julia.uley.common.Pass;

/**
 * Created by Михаил on 05.12.2015.
 */
public class NewSignInActivity extends AppCompatActivity {

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private static Context context;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.activity_sign_in);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
       // populateAutoComplete();

        mPasswordView = (EditText) findViewById(R.id.password);

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Trying send Package to server
                try {
                    String email = mEmailView.getText().toString();
                    email = email.trim();
                    String password = mPasswordView.getText().toString();
                    com.example.julia.uley.common.Package signInPackage = new Package(PackageType.REQ_SIGN_IN, new Login(email), new Pass(password));
                    Client client = new Client(context);
                    client.start(signInPackage);
                    //TODO: Check response and going to dialog or push error
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }


//    private void populateAutoComplete() {
//        getLoaderManager().initLoader(0, null, this);
//    }

}
