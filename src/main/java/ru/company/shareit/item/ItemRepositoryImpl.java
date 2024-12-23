package ru.company.shareit.item;

import org.springframework.stereotype.Repository;
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
        return Optional.ofNullable(items.put(item.id, item));
    }

    @Override
    public Optional<Item> updateItem(Item user) {
        return Optional.ofNullable(items.put(user.id, user));
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
