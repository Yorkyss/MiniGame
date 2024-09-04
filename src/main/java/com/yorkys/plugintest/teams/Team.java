package com.yorkys.plugintest.teams;

import com.yorkys.plugintest.players.PlayerType;
import com.yorkys.plugintest.players.MGPlayer;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private List<MGPlayer> mgPlayers = new ArrayList<>();
    private String color;
    private int maxSize;

    public Team (String color, int maxSize) {
        this.color = color;
        this.maxSize = maxSize;
    }

    public void formRoles() {
        mgPlayers.get(0).setType(PlayerType.TYPE1);
        mgPlayers.get(1).setType(PlayerType.TYPE2);
        mgPlayers.get(2).setType(PlayerType.TYPE3);
    }

    public boolean addPlayer(MGPlayer mgPlayer) {
        if (mgPlayers.size() < maxSize) {
            mgPlayers.add(mgPlayer);
            mgPlayer.setTeam(this);
            return true;
        }
        return false;
    }



    public boolean removePlayer(MGPlayer mgPlayer) {
        if (mgPlayers.contains(mgPlayer)) {
            mgPlayers.remove(mgPlayer);
            mgPlayer.setTeam(null);
            return true;
        }
        return false;
    }

    public int getSize() {
        return mgPlayers.size();
    }

    public int getMaxSize() {
        return maxSize;
    }

    public String getColor() {
        return color;
    }

    public List<MGPlayer> getPlayers() {
        return mgPlayers;
    }

    public MGPlayer getType1() {
        return mgPlayers.stream()
                .filter(MGPlayer -> MGPlayer.getType() == PlayerType.TYPE1)
                .findFirst()
                .orElse(null);
    }

    public MGPlayer getType2() {
        return mgPlayers.stream()
                .filter(MGPlayer -> MGPlayer.getType() == PlayerType.TYPE2)
                .findFirst()
                .orElse(null);
    }

    public MGPlayer getType3() {
        return mgPlayers.stream()
                .filter(MGPlayer -> MGPlayer.getType() == PlayerType.TYPE3)
                .findFirst()
                .orElse(null);
    }
}
