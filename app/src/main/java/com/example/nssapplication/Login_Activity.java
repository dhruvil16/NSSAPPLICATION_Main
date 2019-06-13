package com.example.nssapplication;

import android.content.Intent;
import android.security.keystore.UserNotAuthenticatedException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Login_Activity extends AppCompatActivity {

    EditText UsernameTextBox,PasswordTextBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        UsernameTextBox = findViewById(R.id.UserName_Id);
        PasswordTextBox = findViewById(R.id.Password_Id);
    }

    public void loginClicked(View view){

        String username = UsernameTextBox.getText().toString();
        String password = PasswordTextBox.getText().toString();

        /*
         *  change this logic with database login credential
         */

  //      if(username.equals("dhruvil"))
  //      {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
   //     }
    }

    public void createNewAccountClicked(View view){
        // create new account
        Intent intent = new Intent(this,CreateNewAccount.class);
        startActivity(intent);
    }
}

/*
    https://735ad2a6-a-62cb3a1a-s-sites.googlegroups.com/site/anycarknowledge/Home/bugatti-veyron.jpg?attachauth=ANoY7cqK6Jty8QPrtiDT2DFGFY6SXwi6R7gm0YZ4Wm80R-1Lxb-wUL-7P35a_z8n9z6176y0bs_BwrKMYX8CRZZmvBVOrrastU4-sHxywpWOjS7wEjmfg3HC_m9GK8muz6AHnHoaIwfW3Cf0TncfZPrqmzXw4TdS57HreFu6iLrS3hHjLP2DXA7sY5tEQnCOxxqgL1KGio8dlPfXb7LvlzNcvHr4BZ7zDmXZmds8i9C4_yHfEWG6YXQ%3D&attredirects=0
*/