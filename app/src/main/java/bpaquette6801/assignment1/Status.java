package bpaquette6801.assignment1;

/**
 * Created by bpaquette6801 on 11/24/2017.
 */

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Status {

    @PrimaryKey
    public final int id;
    public String name;

    public Status(int id, String name) {
        this.id = id;
        this.name = name;
    }

}