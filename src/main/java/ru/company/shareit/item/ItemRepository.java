package ru.company.shareit.item;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public interface ItemRepository {

    Optional<Item> getItemById(Long id);

    Optional<Item> addItem(Item item);

    Optional<Item> updateItem(Item item);

    void deleteItemById(Long id);

    Map<Long, Item> getAllItems();
}
