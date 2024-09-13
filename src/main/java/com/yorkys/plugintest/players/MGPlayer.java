package com.yorkys.plugintest.players;

import com.yorkys.plugintest.teams.Team;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

@Getter
public class MGPlayer {
    private final Player player;

    private PlayerType type;
    private boolean playing = false;
    private boolean spectator = false;
    private Team team = null;

    private int stars = 0;
    private int kills = 0;
    private int deaths = 0;

    public MGPlayer(Player player) {
        this.player = player;
    }

    public void setTeam(Team team) {
        if (this.team != null) this.team.removePlayer(this);
        this.team = team;
    }

    public void removeTeam() { // use setTeam(null) to remove team from the player and also player from the team
        team = null;
    }

    public boolean setType(PlayerType type) {
        if (team == null) {
            System.out.println("Error: can't set " + player.getName() + "'s type to " + type + " because the player doesn't have a team");
            return false;
        }

        if (team.getPlayerFromType(type) != null) {
            System.out.println("Error: can't set " + player.getName() + "'s type to " + type + " because this type is already taken");
            return false;
        }

        this.type = type;
        return true;
    }

    public int getStars() {
        return stars;
    }

    public void resetStars() {
        stars = 0;
        team.updateRedStars();
    }

    public void updateStars(int value) {
        stars += value;
        team.updateRedStars();
    }

    public boolean isPlaying() {
        return playing;
    }

    public boolean isSpectator() {
        return spectator;
    }

    public void setPlaying(boolean value) {
        playing = value;
    }

    public void setSpectator(boolean value) {
        player.setGameMode(value ? GameMode.ADVENTURE : GameMode.SURVIVAL);
        if (value) {
            for (Player pl : Bukkit.getOnlinePlayers()) {
                if (pl != null) pl.hidePlayer(player);
            }
        } else {
            for (Player pl : Bukkit.getOnlinePlayers()) {
                if (pl != null) pl.showPlayer(player);
            }
        }
        player.setAllowFlight(value);
        spectator = value;
        // teleport spectator to the top center of the map
    }

    public void setDeaths(int deaths) {
        this.deaths += deaths;
    }

    public void setKills(int kills) {
        this.kills += kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getKills() {
        return kills;
    }
}
