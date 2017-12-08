package bpaquette6801.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.content.Context;
import java.util.List;
import android.content.SharedPreferences;
import android.widget.ListView;

import java.util.List;

public class FriendsListActivity extends AppCompatActivity {
        private ListView friends_List;
        private AppDatabase database;
        private User user;
        private Friend friend;
        public static final String mypreference = "mypref";
        private List<Friend> friendsList;
        SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);

                sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        database = AppDatabase.getDatabase(getApplicationContext());
        friends_List = (ListView) findViewById(R.id.friends_list);

        List<Friend> friends = database.friendDao().getAllFriends();

        if (friends.size()==0) {
            database.friendDao().addFriend(new Friend(1,"Azuraith","BlueBarren"));
            database.friendDao().addFriend(new Friend(1,"Azuraith","xGene"));
            database.friendDao().addFriend(new Friend(1,"Azuraith","RyceKryspies"));
        }

        friendsList = database.friendDao().getFriendsForUser(sharedpreferences.getString("current",""));
        Friend[] friendArray = new Friend[friendsList.size()];
        for(int i = 0; i < friendsList.size(); i++){
            friendArray[i] = friendsList.get(i).friendName;
        }


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, friendArray);
        friends_List.setAdapter(adapter);
    }
}
