// src/main/java/org/example/com/hackathonchallenge1/service/ItemService.java
package org.example.com.hackathonchallenge1.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.com.hackathonchallenge1.model.Item;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    private static final String FILE_PATH = "src/main/resources/data.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Item> getAllItems() throws IOException {
        return objectMapper.readValue(new File(FILE_PATH), new TypeReference<List<Item>>() {});
    }

    public Optional<Item> getItemById(String id) throws IOException {
        return getAllItems().stream().filter(item -> item.getId().equals(id)).findFirst();
    }

    public void saveItems(List<Item> items) throws IOException {
        objectMapper.writeValue(new File(FILE_PATH), items);
    }

    public void addItem(Item newItem) throws IOException {
        List<Item> items = getAllItems();
        items.add(newItem);
        saveItems(items);
    }

    public void updateItem(String id, Item updatedItem) throws IOException {
        List<Item> items = getAllItems();
        items.replaceAll(item -> item.getId().equals(id) ? updatedItem : item);
        saveItems(items);
    }

    public void deleteItem(String id) throws IOException {
        List<Item> items = getAllItems();
        items.removeIf(item -> item.getId().equals(id));
        saveItems(items);
    }
}