package ru.company.shareit.item.repository;

import org.springframework.stereotype.Repository;
import ru.company.shareit.item.model.Item;
import ru.company.shareit.util.IdGenerator;

import java.util.*;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

    private final Map<Long, Item> items = new HashMap<>();

    @Override
    public Optional<Item> getItemById(Long id) {
        return Optional.ofNullable(items.get(id));
    }

    @Override
    public Optional<Item> addItem(Item item) {
        item.setId(IdGenerator.INSTANCE.generate(Item.class));
        items.put(item.getId(), item);
        return Optional.ofNullable(items.get(item.getId()));
    }

    @Override
    public Optional<Item> updateItem(Item item) {
        items.put(item.getId(), item);
        return Optional.ofNullable(items.get(item.getId()));
    }

    @Override
    public void deleteItemById(Long id) {
        items.remove(id);
    }

    @Override
    public Map<Long, Item> getAllItems() {
        return items;
    }
}
