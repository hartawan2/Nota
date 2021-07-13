package com.alay.notaservice;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {Nota.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static com.alay.notaservice.AppDatabase sInstance;
    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    @VisibleForTesting
    public static final String DATABASE_NAME = "my_database";

    public abstract NotaDao notaDao();

    private void setmIsDatabaseCreated(){
        mIsDatabaseCreated.postValue(true);
    }

    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()){
            setmIsDatabaseCreated();
        }
    }

    public static com.alay.notaservice.AppDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context, com.alay.notaservice.AppDatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        com.alay.notaservice.AppDatabase database = com.alay.notaservice.AppDatabase.getInstance(context);
                        database.setmIsDatabaseCreated();
                    }
                }).allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }

    public static com.alay.notaservice.AppDatabase getInstance(final Context context){
        if (sInstance == null){
            synchronized (com.alay.notaservice.AppDatabase.class) {
                if (sInstance == null){
                    sInstance = buildDatabase(context);
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }
}
