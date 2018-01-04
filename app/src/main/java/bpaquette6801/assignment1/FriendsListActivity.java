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
        private List<Friend> listOfFriends;
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
        database.friendDao().removeAllFriends();
        List<Friend> friends = database.friendDao().getAllFriends();

        if (friends.size()==0) {
            database.friendDao().addFriend(new Friend(1,"Brian","BlueBarren"));
            database.friendDao().addFriend(new Friend(2,"Brian","xGene"));
            database.friendDao().addFriend(new Friend(3,"Brian","RyceKryspies"));
            database.friendDao().addFriend(new Friend(4,"Brian","Dude"));
            database.friendDao().addFriend(new Friend(5,"Brian","Bro"));
            database.friendDao().addFriend(new Friend(6,"Brian","Guy"));
            database.friendDao().addFriend(new Friend(7,"Brian","Gal"));
            database.friendDao().addFriend(new Friend(8,"Brian","Girl"));
            database.friendDao().addFriend(new Friend(9,"Brian","Man"));
            database.friendDao().addFriend(new Friend(10,"Brian","Person"));
            database.friendDao().addFriend(new Friend(11,"Brian","People"));

        }

        listOfFriends = database.friendDao().getFriendsForUser(sharedpreferences.getString("current",""));

        String[] friendArray = new String[]{listOfFriends.get(0).friendName,listOfFriends.get(1).friendName,listOfFriends.get(2).friendName,listOfFriends.get(3).friendName
                ,listOfFriends.get(4).friendName
                ,listOfFriends.get(5).friendName
                ,listOfFriends.get(6).friendName
                ,listOfFriends.get(7).friendName
                ,listOfFriends.get(8).friendName
                ,listOfFriends.get(9).friendName
                ,listOfFriends.get(10).friendName};

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, friendArray);
        friends_List.setAdapter(adapter);
    }
}
