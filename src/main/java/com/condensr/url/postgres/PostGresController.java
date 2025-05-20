package com.condensr.url.postgres;


//package com.example.demo.controller;

//import com.example.demo.model.Item;
//import com.example.demo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/items")
public class PostGresController {

    @Autowired
    private PostgresRepository itemRepository;

    // CREATE
    @PostMapping
    public ResponseEntity<String> createItem(@RequestBody Item item) {
    	log.info("Inside createItems");
        itemRepository.save(item);
        return ResponseEntity.ok("Item created");
    }

    // READ ALL
    @GetMapping
    public List<Item> getAllItems() {
    	log.info("Inside getAllItems");
        return itemRepository.findAll();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        Item item = itemRepository.findById(id);
        if (item == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(item);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<String> updateItem(@PathVariable Long id, @RequestBody Item item) {
        int updated = itemRepository.update(id, item);
        if (updated == 0) return ResponseEntity.notFound().build();
        return ResponseEntity.ok("Item updated");
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        int deleted = itemRepository.delete(id);
        if (deleted == 0) return ResponseEntity.notFound().build();
        return ResponseEntity.ok("Item deleted");
    }
}