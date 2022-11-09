package com.example.crud.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.crud.data.entity.ShoppingItem;

import java.util.List;

public class ShoppingItemRepository {
    private ShoppingItemDAO shoppingItemDAO;
    private LiveData<List<ShoppingItem>> shoppingItems;

    public ShoppingItemRepository(Application application) {
        ShoppingItemDatabase db = ShoppingItemDatabase.getDatabase(application);
        shoppingItemDAO = db.shoppingItemDao();
        shoppingItems = shoppingItemDAO.getAllShoppingItems();
    }

    public LiveData<List<ShoppingItem>> getShoppingItems() {
        return shoppingItems;
    }

    public void insert(ShoppingItem shoppingItem) {
        ShoppingItemDatabase.databaseWriteExecutor.execute(
                () -> {
                    shoppingItemDAO.insert(shoppingItem);
                }
        );
    }

    public void deleteAll() {
        ShoppingItemDatabase.databaseWriteExecutor.execute(
                () -> {
                    shoppingItemDAO.deleteAll();
                }
        );
    }

    public void delete(ShoppingItem shoppingItem) {
        ShoppingItemDatabase.databaseWriteExecutor.execute(
                () -> {
                    shoppingItemDAO.delete(shoppingItem);
                }
        );
    }

    public void update(ShoppingItem shoppingItem) {
        ShoppingItemDatabase.databaseWriteExecutor.execute(
                () -> {
                    shoppingItemDAO.update(shoppingItem);
                }
        );
    }
}
