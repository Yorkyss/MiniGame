package com.yorkys.plugintest.players;

import com.yorkys.plugintest.MiniGame;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MGPlayersManager {
    private final MiniGame miniGame;
    private List<MGPlayer> mgPlayers = new ArrayList<>();

    public MGPlayersManager(MiniGame miniGame) {
        this.miniGame = miniGame;
    }

    public void addPlayer(Player player) {
        mgPlayers.add(new MGPlayer(player));
    }

    public void addPlayer(MGPlayer player) {
        mgPlayers.add(player);
    }

    public boolean removePlayer(Player player) {
            MGPlayer mgp = getMGPlayerFromPlayer(player);
            if (mgp == null) return true;
            mgp.setTeam(null);
            mgPlayers.remove(getMGPlayerFromPlayer(player));
            return true;
    }

    public MGPlayer getMGPlayerFromPlayer(Player player) {
        return mgPlayers.stream()
                .filter(p -> p.getPlayer().getUniqueId().equals(player.getUniqueId()))
                .findFirst()
                .orElse(null);
    }

    public List<MGPlayer> getMgPlayers() {
        return  mgPlayers;
    }
}
