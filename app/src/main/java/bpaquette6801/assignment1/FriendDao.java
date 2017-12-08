package bpaquette6801.assignment1;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


@Dao
public interface FriendDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addFriend(Friend friend);

    @Query("select * from friend")
    public List<Friend> getAllFriends();

    @Query("select * from friend where userName = :userName")
    public List<Friend> getFriendsForUser(String userName);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateFriend(Friend friend);

    @Query("delete from friend")
    void removeAllFriends();
}