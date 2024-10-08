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
import org.bukkit.scheduler.BukkitRunnable;

@RequiredArgsConstructor
public class KillListener implements Listener {

    private final MiniGame miniGame;

    @EventHandler
    public void onEntityInteract(PlayerDeathEvent event) {
        Player deadPlayer = event.getEntity();
        MGPlayer deadPlayerMGP = miniGame.getMgPlayersManager().getMGPlayerFromPlayer(deadPlayer);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (deadPlayer.isOnline() && deadPlayer.isDead()) {
                    deadPlayer.spigot().respawn();
                    deadPlayerMGP.setSpectator(true);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            deadPlayerMGP.setSpectator(false);
                        }
                    }.runTaskLater(miniGame.getPlugin(), 90);
                }
            }
        }.runTaskLater(miniGame.getPlugin(), 1L);

        // SETTING DEAD STARS

        if (miniGame.getGameManager().getGameState() != GameStates.PLAYING || deadPlayerMGP.isSpectator()) {
            return;
        }

        // CHECK IF DEAD PLAYER WERE KILLED BY ANOTHER PLAYER
        if (deadPlayer.getKiller() == null) {
            event.setDeathMessage(ChatColor.YELLOW + "" + deadPlayer.getName() + ChatColor.GRAY + " è morto perdendo " + ChatColor.RED + deadPlayerMGP.getStars() + ChatColor.GRAY + " stelle rosse");
            deadPlayerMGP.resetStars();
            return;
        }

        // SETTING KILLER STARS
        Player killer = event.getEntity().getKiller();
        MGPlayer killerMGP = miniGame.getMgPlayersManager().getMGPlayerFromPlayer(killer);

        int stars = deadPlayerMGP.getStars() < 1 ? 1 : deadPlayerMGP.getStars() * 2;
        killerMGP.updateStars(stars);
        String starsWord = stars == 1 ? " stella " : " stelle ";
        Bukkit.getServer().broadcastMessage(ChatColor.AQUA + killer.getName() + ChatColor.GRAY + " ha rubato " + stars + starsWord + "rosse uccidendo " + ChatColor.YELLOW + deadPlayer.getName());
        event.setDeathMessage("");
        deadPlayerMGP.resetStars();

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

