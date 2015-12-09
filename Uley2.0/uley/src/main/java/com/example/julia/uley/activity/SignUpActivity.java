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

import com.example.julia.uley.R;
import com.example.julia.uley.client.Client;
import com.example.julia.uley.common.Login;
import com.example.julia.uley.common.Package;
import com.example.julia.uley.common.PackageType;
import com.example.julia.uley.common.Pass;
import com.example.julia.uley.manager.ListenerManager;

/**
 * Created by Михаил on 29.11.2015.
 */
public class SignUpActivity extends AppCompatActivity {

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private static Context context;
    private Client client;
    private EditText mPasswordTooView;
    private Button SignUpButton;
    private SignInActivity.UserLoginTask mAuthTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        try {
            client = new Client(SignUpActivity.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_sign_up);

        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordTooView = (EditText) findViewById(R.id.password2);
       /* mPasswordTooView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) { // поменять параметры мб.
                    //вывести ошибку
                    return true;
                }
                //TODO: Need to compare password and passwordToo ...
                return false;
            }
        });*/
        SignUpButton = (Button) findViewById(R.id.email_sign_up_button);
        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = mPasswordView.getText().toString();
                String password2 = mPasswordTooView.getText().toString();
                View focusView = null;
                //TODO: Trying send Package to server ...
                if (password.equals(password2)) {
                    try {
                        String email = mEmailView.getText().toString();
                        email = email.trim();
                        Login login = new Login(email);
                        Pass pass = new Pass(password);
                        //TODO: CHECK
                        Package signUpPackage = new Package(PackageType.REQ_SIGN_UP, login, pass);
                        SignUpTask signUpTask = new SignUpTask();
                        signUpTask.execute(signUpPackage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    mPasswordView.setError(getString(R.string.error_confirm_password));
                    focusView = mPasswordView;
                    focusView.requestFocus();
                }

            }
        });
    }

    private class SignUpTask extends AsyncTask<Package, Void, Package> {

        @Override
        protected void onPreExecute(){
            SignUpButton.setVisibility(View.INVISIBLE);
        }

        @Override
        protected Package doInBackground(Package... params) {
            Package tempPackage = null;
            try {
                //TODO: CHECK
                Package signUpPackage = params[0];
                Client client = new Client(context);
                client.send(signUpPackage);
                ListenerManager listenerManager = new ListenerManager(client);
                tempPackage = listenerManager.listenerManager();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return tempPackage;
        }

        @Override
        protected void onPostExecute(Package result) {
            View focusView = null;
            if (client.getPackageResp().getType() == PackageType.RESP_SIGN_UP_USER_ALREADY_EXIST) {
                mEmailView.setError(getString(R.string.error_user_already_exist));
                focusView = mEmailView;
                focusView.requestFocus();
                SignUpButton.setVisibility(View.VISIBLE);
            }
            if (client.getPackageResp().getType() == PackageType.RESP_SIGN_UP_PASS_FILTER_FAILED ||
                    client.getPackageResp().getType() == PackageType.RESP_SIGN_UP_LOGIN_FILTER_FAILED) {
                mEmailView.setError(getString(R.string.error_log_pass_filter));
                focusView = mEmailView;
                focusView.requestFocus();
                SignUpButton.setVisibility(View.VISIBLE);
            }
            if (client.getPackageResp().getType() == PackageType.RESP_SIGN_UP_OK) {
                Intent intent = new Intent(SignUpActivity.this, DialogsActivity.class);
                intent.putExtra("client", client);
                SignUpButton.setVisibility(View.VISIBLE);
//                startActivity(intent);
                System.out.println("ok!");
            }
        }
    }
}



