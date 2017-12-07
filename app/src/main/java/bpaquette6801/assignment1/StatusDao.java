package bpaquette6801.assignment1;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


@Dao
public interface StatusDao {

    @Query("select * from status")
    public List<Status> getAllStatus();

    @Query("select * from status where id = :statusId")
    public List<Status> getStatus(long statusId);

    @Query("select * from status where state = :state")
    public Status getStatus(String state);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateStatus(Status status);

    @Query("delete from status")
    void removeAllStatus();
}