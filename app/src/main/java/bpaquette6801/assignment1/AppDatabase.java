package bpaquette6801.assignment1;

/**
 * Created by bpaquette6801 on 11/22/2017.
 */



    import android.content.Context;

    import android.arch.persistence.room.Database;
    import android.arch.persistence.room.Room;
    import android.arch.persistence.room.RoomDatabase;

@Database(entities = {User.class,Status.class,Friend.class}, version = 16, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract UserDao userDao();

    public abstract StatusDao statusDao();

    public abstract FriendDao friendDao();

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context, AppDatabase.class, "userdatabase")
//Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
                            // To simplify the exercise, allow queries on the main thread.
                            // Don't do this on a real app!
                            .allowMainThreadQueries()
                            // recreate the database if necessary
                            .fallbackToDestructiveMigration()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}