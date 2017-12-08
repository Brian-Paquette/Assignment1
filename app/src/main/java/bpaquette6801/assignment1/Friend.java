package bpaquette6801.assignment1;

/**
 * Created by bpaquette6801 on 11/24/2017.
 */

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Friend {

    @PrimaryKey
    public final int id;
    public String userName;
    public String friendName;




    public Friend(int id, String userName, String friendName) {
        this.id = id;
        this.userName = userName;
        this.friendName  = friendName;

    }

}
