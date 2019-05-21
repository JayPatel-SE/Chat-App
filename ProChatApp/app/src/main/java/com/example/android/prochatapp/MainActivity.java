package com.example.android.prochatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.quickblox.auth.session.QBSettings;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

public class MainActivity extends AppCompatActivity {

    private static final String APP_ID = "77080";
    private static final String AUTH_KEY = "LMN6DWZvPKbnHYb";
    private static final String AUTH_SECRET = "UbLVUYqXAP3TsZn";
    private static final String ACCOUNT_KEY = "yZzhwz3LDsapvX7d_-L7";

    Button btnLogin, btnSignUp;
    EditText editUser, editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeFramework();

        //initialize the buttons and textinputs
        btnLogin = (Button) findViewById(R.id.main_btnLogin);
        btnSignUp = (Button) findViewById(R.id.main_btnSignUp);

        editUser = (EditText) findViewById(R.id.main_editLogin);
        editPassword = (EditText) findViewById(R.id.main_editPassword);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }//end onClick()
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String user = editUser.getText().toString();
                final String password = editPassword.getText().toString();

                QBUser qbUser = new QBUser(user, password);

                QBUsers.signIn(qbUser).performAsync(new QBEntityCallback<QBUser>() {
                    @Override
                    public void onSuccess(QBUser qbUser, Bundle bundle) {

                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(MainActivity.this,ChatDialogsActivity.class);
                        intent.putExtra("user",user);
                        intent.putExtra("password",password);
                        startActivity(intent);
                    }//end onSuccess()

                    @Override
                    public void onError(QBResponseException e) {

                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                    }//end onError()
                });
            }//end OnClick()
        });

    }//end onCreate()

    private void initializeFramework(){
        QBSettings.getInstance().init(getApplicationContext(),APP_ID,AUTH_KEY,AUTH_SECRET);
        QBSettings.getInstance().setAccountKey(ACCOUNT_KEY);
    }

}//end class
