package com.condensr.url.postgres;

import org.springframework.stereotype.Repository;

import com.condensr.url.postgres.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostgresRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // CREATE
    public int save(Item item) {
        String sql = "INSERT INTO item (name) VALUES (?)";
        return jdbcTemplate.update(sql, item.getName());
    }

    // READ ALL
    public List<Item> findAll() {
        String sql = "SELECT id, name FROM item";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Item(rs.getLong("id"), rs.getString("name")));
    }

    // READ BY ID
    public Item findById(Long id) {
        String sql = "SELECT id, name FROM items WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                new Item(rs.getLong("id"), rs.getString("name")));
    }

    // UPDATE
    public int update(Long id, Item item) {
        String sql = "UPDATE items SET name = ? WHERE id = ?";
        return jdbcTemplate.update(sql, item.getName(), id);
    }

    // DELETE
    public int delete(Long id) {
        String sql = "DELETE FROM items WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}