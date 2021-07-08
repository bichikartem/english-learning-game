package ru.bichik.elg.dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.bichik.elg.models.Word;

import java.util.List;

@Component
public class WordDAO {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public WordDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Word> index() {
        return jdbcTemplate.query("SELECT * FROM words", new BeanPropertyRowMapper<>(Word.class));
    }

    public Word show(int id) {
        return jdbcTemplate.query("SELECT * FROM words WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Word.class))
        .stream().findAny().orElse(null);
    }
    public int maxId() {
        List<Word> words = jdbcTemplate.query("SELECT * FROM words", new BeanPropertyRowMapper<>(Word.class));
        int count = 0;
        for(Word word : words){
            if(word.getId() > count){
                count = word.getId();
            }
        }
        return count;
    }

    public void save(Word word) {

        jdbcTemplate.update("INSERT INTO words (en, ru) VALUES(?, ?)", word.getEn(), word.getRu());
    }

    public void update(int id, Word updatedWord) {
        jdbcTemplate.update("UPDATE words SET en=?, ru=? WHERE id=?", updatedWord.getEn(),
                updatedWord.getRu(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM words WHERE id=?", id);
    }

}