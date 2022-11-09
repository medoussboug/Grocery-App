package com.example.crud.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.crud.data.entity.ShoppingItem;

import java.util.List;

@Dao
public interface ShoppingItemDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ShoppingItem shoppingItem);

    @Query("DELETE FROM shopping_item")
    void deleteAll();

    @Query("SELECT * FROM shopping_item")
    LiveData<List<ShoppingItem>> getAllShoppingItems();

    @Delete
    void delete(ShoppingItem shoppingItem);

    @Update
    void update(ShoppingItem shoppingItem);
}
