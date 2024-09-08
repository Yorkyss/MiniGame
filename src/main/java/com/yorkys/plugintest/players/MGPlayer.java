package com.yorkys.plugintest.players;

import com.yorkys.plugintest.teams.Team;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

@Getter
@Setter
public class MGPlayer {
    private final Player player;

    private PlayerType type;
    private Team team = null;

    public MGPlayer(Player player) {
        this.player = player;
    }

    public void setTeam(Team team) {
        if (this.team != null) this.team.removePlayer(this);
        this.team = team;
    }

    public void removeTeam() { // use setTeam(null) to remove team from the player and player from the team
        team = null;
    }
}
