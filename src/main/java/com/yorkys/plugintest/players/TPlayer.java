package com.yorkys.plugintest.players;

import org.bukkit.entity.Player;

public class TPlayer {
    private Player player;
    private PlayerType type;

    public TPlayer(Player player) {
        this.player = player;
    }

    public void setType(PlayerType type) {
        this.type = type;
    }

    public PlayerType getType() {
        return type;
    }

    public Player getPlayer() {
        return player;
    }
}
