package com.yorkys.plugintest.listeners.match;

import com.yorkys.plugintest.MiniGame;
import com.yorkys.plugintest.players.MGPlayer;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

@RequiredArgsConstructor
public class KillListener implements Listener {

    private final MiniGame miniGame;

    @EventHandler
    public void onEntityInteract(PlayerDeathEvent event) {
        Player deadPlayer = event.getEntity();
        MGPlayer deadPlayerMGP = miniGame.getTeamsManager().getMGPlayerFromPlayer(deadPlayer);

        if (deadPlayer.getKiller() == null) {
            event.setDeathMessage(ChatColor.GRAY + deadPlayer.getName() + " è morto");
            deadPlayerMGP.resetStars();
            deadPlayer.sendMessage( "hai perso le tue stelle rosse!");
            return;
        }

        Player killer = event.getEntity().getKiller();
        MGPlayer killerMGP = miniGame.getTeamsManager().getMGPlayerFromPlayer(killer);


        int stars = deadPlayerMGP.getStars() < 1 ? 1 : deadPlayerMGP.getStars() * 2;
        killerMGP.updateStars(stars);
        deadPlayerMGP.resetStars();


        if (killerMGP.getTeam().getRedStars() >= 30) {
            killerMGP.getTeam().addBlueStar(1);
            killerMGP.getTeam().resetRedStars();

            deadPlayerMGP.getTeam().resetRedStars();
        }

        event.setDeathMessage(ChatColor.AQUA + deadPlayer.getName() + ChatColor.GRAY + " è stato ucciso da " + ChatColor.AQUA + killer.getName());

        killer.sendMessage(ChatColor.GRAY + "+ " + ChatColor.RED + stars + ChatColor.GRAY + " stelle rosse");
        killer.sendMessage(killerMGP.getStars() + " tue stelle rosse");
        killer.sendMessage(killerMGP.getTeam().getRedStars() + " team stelle rosse");
        killer.sendMessage(killerMGP.getTeam().getBlueStars() + " team stelle blue");

        deadPlayer.sendMessage( "hai perso le tue stelle rosse!");



    }
}

