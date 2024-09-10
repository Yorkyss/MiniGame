package com.yorkys.plugintest.NPC;

import com.yorkys.plugintest.MiniGame;

import java.util.*;

public class NPCManager {
    private MiniGame miniGame;

    private List<NPC> spawnedNPCs = new ArrayList<>();
    private HashMap<UUID, String> npcsID = new HashMap<>();

    public NPCManager(MiniGame miniGame) {
        this.miniGame = miniGame;
    }

    private void addNPC(NPC npc) {
        spawnedNPCs.add(npc);
        npcsID.put(npc.getUUID(), npc.getID());
    }

    private void removeNPC(NPC npc) {
        spawnedNPCs.remove(npc);
        npcsID.remove(npc.getUUID());
        npc.removeNPC();
    }

    private void spawnNPC(NPC npc) {
        npc.spawn();
        addNPC(npc);
    }

    public void spawnAllNPCs() {
        miniGame.getConfigManager().getSettingsConfig().getShopNPCs().forEach(this::spawnNPC);
    }

    public void removeAllNPCs() {
        System.out.println(spawnedNPCs);
        spawnedNPCs.forEach(NPC::removeNPC);
    }


    public NPC getNPCFromCustomName(String customName) {
        return spawnedNPCs.stream().filter(npc -> Objects.equals(npc.getCustomName(), customName)).findFirst().orElse(null);
    }

    public NPC getNPCFromID(String ID) {
        return spawnedNPCs.stream().filter(npc -> Objects.equals(npc.getID(), ID)).findFirst().orElse(null);
    }

    public NPC getNPCFromID(UUID uuid) {
        return getNPCFromID(npcsID.get(uuid));
    }

    public boolean existNPC(String customName) {
        customName = customName.replace("§", "&");
        return getNPCFromCustomName(customName) != null;
    }

    public boolean existNPC(UUID uuid) {
        return getNPCFromID(uuid) != null;
    }
}
