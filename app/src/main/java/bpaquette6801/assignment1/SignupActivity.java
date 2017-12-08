package bpaquette6801.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;
import android.content.Context;
import java.text.NumberFormat;
import java.util.List;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.app.Activity;

public class SignupActivity extends AppCompatActivity implements OnClickListener {

    private AppDatabase database;
    private User user;
    private Button confirmButton;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText userNameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        database = AppDatabase.getDatabase(getApplicationContext());

        confirmButton = (Button) findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(this);

        firstNameEditText = (EditText) findViewById(R.id.firstNameEditText);
        lastNameEditText = (EditText) findViewById(R.id.lastNameEditText);
        userNameEditText = (EditText) findViewById(R.id.userNameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);

    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.confirmButton:

                List<User> users = database.userDao().getAllUser();
                for (int i=0; i<users.size();i++){
                    User user = database.userDao().getAllUser().get(i);
                    if (user.userName.equals(userNameEditText.getText().toString())){
                        Toast.makeText(this, String.valueOf("User already exists, please try a different username"), Toast.LENGTH_SHORT).show();
                    }
                    else{
                        database.userDao().addUser(new User(users.size()+1, userNameEditText.getText().toString(),passwordEditText.getText().toString(),
                                firstNameEditText.getText().toString(),lastNameEditText.getText().toString(),"online"));
                        user = database.userDao().getAllUser().get(users.size());
                        Intent intent = new Intent(this, LoginActivity.class);
                        startActivity(intent);
                    }
                }




                break;

        }

    }

}
