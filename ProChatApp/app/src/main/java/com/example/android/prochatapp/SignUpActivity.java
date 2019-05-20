package com.example.android.prochatapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.quickblox.auth.QBAuth;
import com.quickblox.auth.session.QBSession;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

public class SignUpActivity extends AppCompatActivity {

    private Button btnSignUp, btnCancel;
    private EditText editUser, editPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        registerSession();

        btnSignUp = (Button) findViewById(R.id.signup_btnSignUp);
        btnCancel = (Button) findViewById(R.id.signup_btnCancel);

        editUser = (EditText) findViewById(R.id.signup_editLogin);
        editPassword = (EditText) findViewById(R.id.signup_editPassword);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = editUser.getText().toString();
                String password = editPassword.getText().toString();

                QBUser qbUser = new QBUser(user,password);
                QBUsers.signUp(qbUser).performAsync(new QBEntityCallback<QBUser>() {
                    @Override
                    public void onSuccess(QBUser qbUser, Bundle bundle) {
                        Toast.makeText(getBaseContext(), "Sign Up Successful",Toast.LENGTH_SHORT).show();
                        finish();
                    }//end on Success

                    @Override
                    public void onError(QBResponseException e) {
                    Toast.makeText(getBaseContext(),"" + e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }//end onCreate()

    private void registerSession() {

        QBAuth.createSession().performAsync(new QBEntityCallback<QBSession>() {
            @Override
            public void onSuccess(QBSession qbSession, Bundle bundle) {

            }

            @Override
            public void onError(QBResponseException e) {
                Log.e("ERROR",e.getMessage());
            }
        });

    }//end registerSession()
}//end class
