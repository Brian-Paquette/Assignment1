package bpaquette6801.assignment1;

/**
 * Created by bpaquette6801 on 10/13/2017.
 */

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey
    public final int id;
    public String userName;
    public String password;
    public String firstName;
    public String lastName;



    public User(int id, String userName, String password,String firstName,String lastName) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.firstName  = firstName;
        this.lastName = lastName;
    }

}