package com.yorkys.plugintest.teams;

import com.yorkys.plugintest.players.PlayerType;
import com.yorkys.plugintest.players.MGPlayer;

import java.util.List;

public class Team {
    private List<MGPlayer> mgPlayers;
    private String color;
    private int size;

    public Team (List<MGPlayer> mgPlayers, String color, int size) {
        this.mgPlayers = mgPlayers;
        this.color = color;
        this.size = size;
    }

    public void formRoles() {
        mgPlayers.get(0).setType(PlayerType.TYPE1);
        mgPlayers.get(1).setType(PlayerType.TYPE2);
        mgPlayers.get(2).setType(PlayerType.TYPE3);
    }

    public int getSize() {
        return size;
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
