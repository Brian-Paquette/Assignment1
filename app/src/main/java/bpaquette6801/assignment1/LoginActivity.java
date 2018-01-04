package bpaquette6801.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;
import android.content.Context;
import java.text.NumberFormat;
import java.util.List;
import android.content.SharedPreferences;
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
    private Button signupButton;
    private EditText userNameEditText;
    private EditText passwordEditText;
    private User user;
    private Status status;
    private AppDatabase database;
    private String stringUser;
    public static final String mypreference = "mypref";
    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Setup Database
        database = AppDatabase.getDatabase(getApplicationContext());
        //Add user
        List<User> users = database.userDao().getAllUser();

        List<Status> status = database.statusDao().getAllStatus();
        if (status.size()==0) {
            database.statusDao().addStatus(new Status(1,"online"));
            database.statusDao().addStatus(new Status(2,"offline"));
            database.statusDao().addStatus(new Status(3,"busy"));
        }


        if (users.size()==0) {
            database.userDao().addUser(new User(users.size() + 1, "Azuraith", "test",
                    "Brian", "Paquette", "online"));


        }



        //to be userd later???? updateFirstUserData();


        //login button for toast
        setContentView(R.layout.activity_login);
        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);
        signupButton = (Button) findViewById(R.id.signupButton);
        signupButton.setOnClickListener(this);


        //The rest of the controls
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        userNameEditText = (EditText) findViewById(R.id.userNameEditText);

    }
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.loginButton:

                sharedpreferences = getSharedPreferences(mypreference,
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();

                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                String text = "null";
                Toast toast;
                stringUser = userNameEditText.getText().toString();
                List<User> users = database.userDao().getAllUser();
                user = (User) database.userDao().getUser(stringUser);
                if (users.size()>0) {
                    if(!user.userName.equals(userNameEditText.getText().toString())){
                        text = "Login Failed - Invalid Username";
                        toast = Toast.makeText(context, text, duration);
                        toast.setGravity(Gravity.CENTER | Gravity.BOTTOM, 0, 100);
                        toast.show();
                    }
                    else if(!user.password.equals(passwordEditText.getText().toString())){
                        text = "Login Failed - Invalid Password";
                        toast = Toast.makeText(context, text, duration);
                        toast.setGravity(Gravity.CENTER | Gravity.BOTTOM, 0, 100);
                        toast.show();
                    }
                    else{
                        editor.putString("current",user.userName);
                        editor.commit();
                        text = "SUCCESS";
                        toast = Toast.makeText(context, text, duration);
                        toast.setGravity(Gravity.CENTER | Gravity.BOTTOM, 0, 100);
                        toast.show();

                        Intent intent = new Intent(this, HomeActivity.class);
                        Bundle userPass = new Bundle();
                        intent.putExtras(userPass);
                        startActivity(intent);
                    }

                }
                else{
                    text = "Login Failed - No users in database";
                    toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.CENTER | Gravity.BOTTOM, 0, 100);
                    toast.show();
                }

                break;
            case R.id.signupButton:
                Intent intent = new Intent(this, SignupActivity.class);
                startActivity(intent);

                break;
        }

        }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        return false;
    }
}


