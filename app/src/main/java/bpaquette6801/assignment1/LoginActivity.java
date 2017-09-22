package bpaquette6801.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;
import android.content.Context;
import java.text.NumberFormat;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.app.Activity;

public class LoginActivity extends AppCompatActivity implements OnEditorActionListener ,OnClickListener {

    private Button loginButton;
    private EditText userNameEditText;
    private EditText passwordEditText;
    private String username = "username";
    private String password = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //login button for toast
        setContentView(R.layout.activity_login);
        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);



        //The rest of the controls
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        userNameEditText = (EditText) findViewById(R.id.userNameEditText);

    }
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.loginButton:
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                String text = "null";
                Toast toast = Toast.makeText(context, text, duration);
                String userNameString = userNameEditText.getText().toString();
                String passwordString = passwordEditText.getText().toString();
                if(!userNameString.equals("username")){
                    text = "Login Failed - Invalid Username";
                    toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.CENTER | Gravity.BOTTOM, 0, 100);
                    toast.show();
                }
                else if(!passwordString.equals("password")){
                    text = "Login Failed - Invalid Password";
                    toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.CENTER | Gravity.BOTTOM, 0, 100);
                    toast.show();
                }
                else{
                    text = "SUCCESS";
                    toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.CENTER | Gravity.BOTTOM, 0, 100);
                    toast.show();

                    Intent intent = new Intent(this, HomeActivity.class);
                    Bundle userPass = new Bundle();
                    userPass.putString("user",username);
                    userPass.putString("pass",password);
                    intent.putExtras(userPass);
                    startActivity(intent);
                }
                break;
        }

        }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        return false;
    }
}


