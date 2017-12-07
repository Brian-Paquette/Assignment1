package bpaquette6801.assignment1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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

public class ForgotPassActivity extends AppCompatActivity implements OnClickListener {
    private Button confirmButton;
    private EditText passwordEditText;
    private User user;
    private AppDatabase database;

    public static final String mypreference = "mypref";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        confirmButton = (Button) findViewById(R.id.confirmButton);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        confirmButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirmButton:
                sharedpreferences = getSharedPreferences(mypreference,
                        Context.MODE_PRIVATE);
                database = AppDatabase.getDatabase(getApplicationContext());
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                String text = "null";
                Toast toast;
                String passwordString = passwordEditText.getText().toString();
                if(passwordEditText.getText().toString().equals("") || passwordEditText.getText().equals(null)){
                    text = "Login Failed - Enter something for goodness sake";
                    toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.CENTER | Gravity.BOTTOM, 0, 100);
                    toast.show();
                }
                else if(passwordEditText.getText().toString().equals("")){
                    text = "Cannot change your password to your current password";
                    toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.CENTER | Gravity.BOTTOM, 0, 100);
                    toast.show();
                }
                else{
                    user = (User) database.userDao().getUser(sharedpreferences.getString("current",""));
                    int id = user.id;
                    String userName = user.userName;
                    String password = passwordEditText.getText().toString();
                    String firstName = user.firstName;
                    String lastName = user.lastName;
                    database.userDao().deleteOne(sharedpreferences.getString("current",""));
                    database.userDao().addUser(new User(id, userName,password,firstName,lastName));
                    text = "SUCCESS";
                    toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.CENTER | Gravity.BOTTOM, 0, 100);
                    toast.show();

                    Intent intent = new Intent(this, HomeActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }
}
