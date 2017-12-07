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

public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView sampleTextView;
    private TextView newPassText;
    private Button forgotButton;
    private Spinner forgotSpinner;
    private User user;
    private AppDatabase database;
    public static final String mypreference = "mypref";
    SharedPreferences sharedpreferences;

    //Bundle userPass = this.getIntent().getExtras();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();



        super.onCreate(savedInstanceState);
        database = AppDatabase.getDatabase(getApplicationContext());
        user = (User) database.userDao().getUser(sharedpreferences.getString("current",""));
        setContentView(R.layout.activity_home);
        sampleTextView = (TextView) findViewById(R.id.sampleText);
        //sampleTextView.append(getIntent().getExtras().getString("user"));
        newPassText = (TextView) findViewById(R.id.newPassText);
        newPassText.append(user.password);
        forgotSpinner = (Spinner) findViewById(R.id.forgotSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.menu_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        forgotSpinner.setAdapter(adapter);
        forgotSpinner.setOnItemSelectedListener(this);




        //forgotButton = (Button) findViewById(R.id.forgotButton);
        //forgotButton.setOnClickListener(this);
    }


    public void onItemSelected(AdapterView<?> parent, View v,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        if (parent.getItemAtPosition(pos).toString().equals("Forgot Password")){
            Bundle userPass = getIntent().getExtras();
            Intent intent = new Intent(this, ForgotPassActivity.class);
            intent.putExtras(userPass);
            startActivity(intent);
        }
        else if (parent.getItemAtPosition(pos).toString().equals("Edit Profile")){
            Bundle userPass = getIntent().getExtras();
            Intent intent = new Intent(this, EditProfileActivity.class);
            intent.putExtras(userPass);
            startActivity(intent);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
