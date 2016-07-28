package com.abo.mddemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by abo on 16/7/28.
 */
public class EditTextExampleActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edittext);
        Toolbar toolbar = (Toolbar) findViewById(R.id.edt_toolbar);
        toolbar.setTitle("EditText");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextInputLayout usernameWrapper = (TextInputLayout) findViewById(R.id.email_input);
        TextInputLayout passwordWrapper = (TextInputLayout) findViewById(R.id.password_input);
        usernameWrapper.setHint("Username");
        passwordWrapper.setHint("Password");

    }
}
