package com.yorkys.plugintest.listeners.match;

import com.yorkys.plugintest.MiniGame;
import com.yorkys.plugintest.gameManager.GameRounds;
import com.yorkys.plugintest.gameManager.GameStates;
import com.yorkys.plugintest.players.MGPlayer;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
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
        // SETTING DEAD STARS
        Player deadPlayer = event.getEntity();
        MGPlayer deadPlayerMGP = miniGame.getMgPlayersManager().getMGPlayerFromPlayer(deadPlayer);

        if (miniGame.getGameManager().getGameState() != GameStates.PLAYING || deadPlayerMGP.isSpectator()) {
            return;
        }

        // CHECK IF DEAD PLAYER WERE KILLED BY ANOTHER PLAYER
        if (deadPlayer.getKiller() == null) {
            event.setDeathMessage(ChatColor.YELLOW + "" + deadPlayer.getName() + ChatColor.GRAY + " è morto perdendo " + ChatColor.RED + deadPlayerMGP.getStars() + ChatColor.GRAY + " stelle rosse");
            deadPlayerMGP.resetStars();
            return;
        }
        deadPlayerMGP.resetStars();

        // SETTING KILLER STARS
        Player killer = event.getEntity().getKiller();
        MGPlayer killerMGP = miniGame.getMgPlayersManager().getMGPlayerFromPlayer(killer);

        int stars = deadPlayerMGP.getStars() < 1 ? 29 : deadPlayerMGP.getStars() * 29;
        killerMGP.updateStars(stars);
        String starsWord = stars == 1 ? " stella " : " stelle ";
        event.setDeathMessage(ChatColor.AQUA + killer.getName() + ChatColor.GRAY + " ha rubato " + stars + starsWord + "rosse uccidendo " + ChatColor.YELLOW + deadPlayer.getName());


        // BLUE STARS AND FINISH GAME CHECK
        if (killerMGP.getTeam().getRedStars() >= 30) {
            killerMGP.getTeam().addBlueStar(1);
            killerMGP.getTeam().resetRedStars();
            deadPlayerMGP.getTeam().resetRedStars();
            Bukkit.getServer().broadcastMessage(ChatColor.GRAY + "il team " + ChatColor.BLUE + killerMGP.getTeam().getColor() + ChatColor.GRAY + " ha ottenuto 1 stella blu!");
            Bukkit.getServer().broadcastMessage(ChatColor.GRAY + "stelle rosse resettate");

            if (GameRounds.fromRound(miniGame.getGameManager().getGameRounds().getRound() + 1) != null) {
                miniGame.getGameManager().setGameRounds(GameRounds.fromRound(miniGame.getGameManager().getGameRounds().getRound() + 1));
            } else {
                Bukkit.getServer().broadcastMessage(ChatColor.GRAY + "il team " + ChatColor.AQUA + killerMGP.getTeam().getColor() + ChatColor.GRAY + " ha vinto la partita!");
                miniGame.getGameManager().stop();
            }
        }
    }
}

