package com.example.crud.data;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.crud.data.entity.ShoppingItem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {ShoppingItem.class}, version = 1, exportSchema = false)
abstract class ShoppingItemDatabase extends RoomDatabase {
    abstract ShoppingItemDAO shoppingItemDao();

    private static volatile ShoppingItemDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            INSTANCE.shoppingItemDao().deleteAll();
            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                ShoppingItemDAO dao = INSTANCE.shoppingItemDao();
                dao.deleteAll();

                ShoppingItem shoppingItem = new ShoppingItem("Hello", 2);
                dao.insert(shoppingItem);
                shoppingItem = new ShoppingItem("World", 3);
                dao.insert(shoppingItem);
            });
        }
    };

    static ShoppingItemDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ShoppingItemDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    ShoppingItemDatabase.class, "shopping_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
