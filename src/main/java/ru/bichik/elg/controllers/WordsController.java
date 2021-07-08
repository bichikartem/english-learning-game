package ru.bichik.elg.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bichik.elg.dao.WordDAO;
import ru.bichik.elg.models.Word;
import java.util.Random;

@Controller
@RequestMapping("/words")
public class WordsController {
    private final WordDAO wordDAO;
    @Autowired
    public WordsController(WordDAO wordDAO) {
        this.wordDAO = wordDAO;
    }

    @GetMapping()
    public String startPage(Model model) {
        model.addAttribute("words", wordDAO.index());
        return "words/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("word", wordDAO.show(id));
        return "words/show";
    }
    @GetMapping("/game")
    public String game(Model model) {
        Random random = new Random();
        int randomId = random.nextInt(wordDAO.maxId() + 1);
        model.addAttribute("word", wordDAO.show(randomId));
        return "words/game";
    }
    @PostMapping ("/game")
    public String gameEqual(@ModelAttribute("word") Word word, Model model){
        if(word.getTryEn().equals(word.getEn())) {
            return "words/successPage";
        } else {
            model.addAttribute(word);
            return "words/unsuccessfulPage";
        }
    }

    @GetMapping("/new")
    public String newWord(@ModelAttribute("word") Word word) {
        return "words/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("word") Word word) {
        wordDAO.save(word);
        return "redirect:/words";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("word", wordDAO.show(id));
        return "words/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("word") Word word, @PathVariable("id") int id) {
        wordDAO.update(id, word);
        return "redirect:/words";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        wordDAO.delete(id);
        return "redirect:/words";
    }
}