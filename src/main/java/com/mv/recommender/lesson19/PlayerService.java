package com.mv.recommender.lesson19;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private PlayerSpringDataRepository repo;

    List<Player> getAllPlayers() {
        return repo.findAll();
    }

    Player getPlayerById(int id) {
        Optional<Player> playerOptional = repo.findById(id);
        if (playerOptional.isEmpty())
            throw new PlayerNotFoundException("Player with id {" + id + "} not found");

        return playerOptional.orElseThrow(() -> new RuntimeException("Player with id " + id + " not found."));
    }

    public Player addPlayer(Player p) {
        return repo.save(p);
    }

    public Player updatePlayer(int id, Player p) {
        try {
            Player player = repo.getReferenceById(id);
            player.setName(p.getName());
            player.setNationality(p.getNationality());
            player.setTitles(p.getTitles());
            player.setBirthDate(p.getBirthDate());
            return repo.save(player);
        } catch (EntityNotFoundException e){
            throw new PlayerNotFoundException(e);
        }
    }

    public Player patch(int id, Map<String, Object> playerPatch) {

        Optional<Player> player = repo.findById(id);
        if (player.isEmpty())
            throw new PlayerNotFoundException("Player with id {" + id + "} not found");

        player.ifPresent(player1 -> playerPatch.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Player.class, key);
            if (field != null) {
                ReflectionUtils.makeAccessible(field);
                ReflectionUtils.setField(field, player1, value);
            }
        }));
        return repo.save(player.get());
    }


    @Transactional
    public void updateTitles(int id, int titles) {
        Optional<Player> tempPlayer = repo.findById(id);

        if(tempPlayer.isEmpty())
            throw new PlayerNotFoundException("Player with id {"+ id +"} not found");

        repo.updateTitles(id, titles);
    }

    public String deletePlayer(int id) {
        try {
            repo.deleteById(id);
        } catch (Exception e) {
            return "Player with id " + id + "not found";
        }

        return "Deleted player with id: " + id;
    }
}