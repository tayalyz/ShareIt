package ru.company.shareit.item;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/items")
@RestController
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/{id}")
    public ItemDto getItemById(@PathVariable Long id) {
        return itemService.getItemById(id);
    }

    @PostMapping
    public ItemDto addItem(@RequestBody @Validated Item item, @RequestHeader("X-Sharer-User-Id") Long userId) {
        return itemService.addItem(ItemMapper.toItemDto(item), userId);
    }

    @PatchMapping("/{id}")
    public ItemDto updateItem(@RequestHeader("X-Sharer-User-Id") Long userId, @PathVariable Long id, @RequestBody @Validated Map<String, Object> fields) {
        return itemService.updateItem(userId, id, fields);
    }

    @DeleteMapping("/{id}")
    public void deleteItemById(@PathVariable Long id) {
        itemService.deleteItemById(id);
    }

    @GetMapping()
    public List<ItemDto> getAllItemsByUserId(@RequestHeader("X-Sharer-User-Id") Long userId) {
        return itemService.getAllItemsByUserId(userId);
    }

    @GetMapping("/search")
    public List<ItemDto> getItemsWithName(@RequestParam(value = "text", defaultValue = "LOHLOHLOH") String text) {
        return itemService.getItemsWithName(text);
    }
}
