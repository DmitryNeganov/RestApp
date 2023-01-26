package org.example.controller;


import org.example.dao.FootballPlayerDao;
import org.example.model.FootballPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/")
public class PlayersController {

    @Autowired
    private final FootballPlayerDao dao;

    public PlayersController(FootballPlayerDao dao) {
        this.dao = dao;
    }

    @GetMapping("/players")
    public List<FootballPlayer> getAllPlayers(){
        List<FootballPlayer> players = dao.getPlayers();
        return players;
    }


    @GetMapping("/{id}")
    public FootballPlayer getFootballPlayer(@PathVariable("id") int id) {
        return dao.getPlayerById(id);
    }

    @PostMapping("/add_player")
    public void createFootballPlayer(@RequestBody FootballPlayer footballPlayer){
        dao.addPlayer(footballPlayer);
    }

    @DeleteMapping("/{id}")
    public void deleteFootballPlayer(@PathVariable("id") int id) {
        dao.deletePlayerById(id);
    }
}
