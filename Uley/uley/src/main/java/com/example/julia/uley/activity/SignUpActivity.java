package com.example.julia.uley.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.julia.uley.R;

/**
 * Created by Михаил on 29.11.2015.
 */
public class SignUpActivity extends AppCompatActivity {

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private EditText mPasswordTooView;
    private SignInActivity.UserLoginTask mAuthTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordTooView = (EditText) findViewById(R.id.password2);
        mPasswordTooView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) { // поменять параметры мб.
                    //вывести ошибку
                    return true;
                }
                //TODO: Need to compare password and passwordToo ...
                return false;
            }
        });
        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_up_buttom);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Trying send Package to server ...
//                try {
//                    String email = mEmailView.getText().toString();
//                    email = email.trim();
//                    String password = mPasswordView.getText().toString();
//                    Login login = new Login(email);
//                    Pass pass = new Pass(password);
//                    Package signUpPackage = new Package(PackageType.REQ_SIGN_UP, login, pass);
//                    Client client = new Client();
//                    client.sendPackage(signUpPackage);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }
        });
    }




}
