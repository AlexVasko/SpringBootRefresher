package com.mv.recommender.dbrelationships.onetoone.bidirectional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerProfileService {

    @Autowired
    PlayerProfileRepository repo;

    public List<PlayerProfile> allPlayerProfiles() {
        return repo.findAll();
    }

    public PlayerProfile getPlayerProfile(int id){
        return repo.findById(id).get();
    }

    public PlayerProfile addPlayerProfile(PlayerProfile profile) {
        profile.setId(0);
        return repo.save(profile);
    }

    public void deletePlayerProfile(int id) {
        PlayerProfile tempPlayerProfile = repo.findById(id).get();
        //set the playerProfile field of the Player object to null
        tempPlayerProfile.getPlayer().setPlayerProfile(null);
        //set the player field of the PlayerProfile object to null
        tempPlayerProfile.setPlayer(null);
        //save changes
        repo.save(tempPlayerProfile);
        //delete the PlayerProfile object
        repo.delete(tempPlayerProfile);
    }
}