package com.yorkys.plugintest.teams;

import com.yorkys.plugintest.players.PlayerType;
import com.yorkys.plugintest.players.TPlayer;

import java.util.List;

public class Team {
    private List<TPlayer> tplayers;
    private String color;
    private int size;

    public Team (List<TPlayer> tplayers, String color, int size) {
        this.tplayers = tplayers;
        this.color = color;
        this.size = size;
    }

    public void formRoles() {
        tplayers.get(0).setType(PlayerType.TYPE1);
        tplayers.get(1).setType(PlayerType.TYPE2);
        tplayers.get(2).setType(PlayerType.TYPE3);
    }

    public int getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public List<TPlayer> getPlayers() {
        return  tplayers;
    }

    public TPlayer getType1() {
        return tplayers.stream()
                .filter(tPlayer -> tPlayer.getType() == PlayerType.TYPE1)
                .findFirst()
                .orElse(null);
    }

    public TPlayer getType2() {
        return tplayers.stream()
                .filter(tPlayer -> tPlayer.getType() == PlayerType.TYPE2)
                .findFirst()
                .orElse(null);
    }

    public TPlayer getType3() {
        return tplayers.stream()
                .filter(tPlayer -> tPlayer.getType() == PlayerType.TYPE3)
                .findFirst()
                .orElse(null);
    }
}
