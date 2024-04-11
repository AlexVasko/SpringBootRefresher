package com.mv.recommender.lesson19;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class PlayerController{

    @Autowired
    PlayerService playerService;

    @GetMapping("/welcome")
    public String welcome() {
        return "Tennis Player REST API";
    }

    @GetMapping("/players")
    public List<Player> getAllPlayers(){
        return playerService.getAllPlayers();
    }

    @GetMapping("/players/{id}")
    public Player getPlayerById(@PathVariable int id){
        return playerService.getPlayerById(id);
    }

    @PostMapping("/players")
    public Player addPlayer(@RequestBody Player player){
        player.setId(0);
        return playerService.addPlayer(player);
    }

    @PutMapping("/players/{id}")
    public Player updatePlayer(@PathVariable int id, @RequestBody Player player) {
        return playerService.updatePlayer(id,player);
    }

    @PatchMapping("/players/{id}")
    public Player patchPlayer(@PathVariable int id,  @RequestBody Map<String, Object> playerPatch) {
        return playerService.patch(id,playerPatch);
    }

    @PatchMapping("/players/{id}/titles")
    public void updateTitles(@PathVariable int id, @RequestBody int titles) {
        playerService.updateTitles(id, titles);
    }

    @DeleteMapping("/players/{id}")
    public String deletePlayer(@PathVariable int id) {
        return playerService.deletePlayer(id);
    }
}
