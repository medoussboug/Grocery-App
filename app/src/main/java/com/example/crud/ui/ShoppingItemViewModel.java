package com.example.crud.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.crud.data.ShoppingItemRepository;
import com.example.crud.data.entity.ShoppingItem;

import java.util.List;

public class ShoppingItemViewModel extends AndroidViewModel {

    private ShoppingItemRepository shoppingItemRepository;
    private final LiveData<List<ShoppingItem>> shoppingItems;

    public ShoppingItemViewModel(@NonNull Application application) {
        super(application);
        shoppingItemRepository = new ShoppingItemRepository(application);
        shoppingItems = shoppingItemRepository.getShoppingItems();
    }

    LiveData<List<ShoppingItem>> getShoppingItems() {
        return shoppingItems;
    }

    void insert(ShoppingItem shoppingItem) {
        shoppingItemRepository.insert(shoppingItem);
    }

    void delete(ShoppingItem shoppingItem) {
        shoppingItemRepository.delete(shoppingItem);
    }

    void update(ShoppingItem shoppingItem) {
        shoppingItemRepository.update(shoppingItem);
    }
}
