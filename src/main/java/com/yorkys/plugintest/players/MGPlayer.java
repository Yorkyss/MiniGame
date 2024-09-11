package com.yorkys.plugintest.players;

import com.yorkys.plugintest.teams.Team;
import lombok.Getter;
import org.bukkit.entity.Player;

@Getter
public class MGPlayer {
    private final Player player;

    private PlayerType type;
    private Team team = null;
    private int stars = 0;

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
    }

    public void updateStars(int value) {
        stars += value;
        team.updateRedStars();
    }
}
