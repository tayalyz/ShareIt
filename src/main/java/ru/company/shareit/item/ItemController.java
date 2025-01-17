package ru.company.shareit.item;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.company.shareit.item.dto.ItemDto;
import ru.company.shareit.item.dto.ItemUpdateDto;
import ru.company.shareit.item.service.ItemService;

import java.util.List;

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
    public ItemDto addItem(@RequestBody @Valid ItemDto item, @RequestHeader("X-Sharer-User-Id") Long userId) {
        return itemService.addItem(item, userId);
    }

    @PatchMapping("/{id}")
    public ItemDto updateItem(@RequestHeader("X-Sharer-User-Id") Long userId,
                              @PathVariable Long id,
                              @RequestBody @Validated ItemUpdateDto itemUpdateDto) {
        return itemService.updateItem(userId, id, itemUpdateDto);
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
    public List<ItemDto> getItemsWithName(@RequestParam(value = "text") String text) {
        return itemService.getItemsWithName(text);
    }
}
