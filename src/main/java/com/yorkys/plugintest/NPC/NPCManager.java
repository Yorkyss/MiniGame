package com.yorkys.plugintest.NPC;

import com.yorkys.plugintest.MiniGame;

import java.util.*;

public class NPCManager {
    private MiniGame miniGame;

    private List<NPC> npcs = new ArrayList<>();
    private List<NPC> spawnedNPCs = new ArrayList<>();
    private HashMap<UUID, String> spawnedNPCsID = new HashMap<>();

    public NPCManager(MiniGame miniGame) {
        this.miniGame = miniGame;
    }

    public void addNPC(NPC npc) {
        npcs.add(npc);
    }


    private void spawnNPC(NPC npc) {
        npc.spawn();
        spawnedNPCs.add(npc);
        spawnedNPCsID.put(npc.getUUID(), npc.getID());
    }

    private void removeAllNPCs(NPC npc, String ID) {
        if (spawnedNPCs.removeIf(p -> Objects.equals(p.getID(), ID))) {
            spawnedNPCsID.remove(npc.getUUID());
            npc.removeNPC();
        }
    }

    public void spawnAllNPCs(String ID) {
        npcs.forEach(npc -> {
            if (Objects.equals(npc.getID(), ID)) {
                spawnNPC(npc);
            }
        });
    }

    public void removeAllNPCs(String ID) {
        Iterator<NPC> iterator = spawnedNPCs.iterator();
        while (iterator.hasNext()) {
            NPC npc = iterator.next();
            if (npc.getID().equals(ID)) {
                iterator.remove(); // Safely remove the element from the list
                spawnedNPCsID.remove(npc.getUUID());
                npc.removeNPC();
            }
        }
    }


    public NPC getNPCFromCustomName(String customName) {
        return spawnedNPCs.stream().filter(npc -> Objects.equals(npc.getCustomName(), customName)).findFirst().orElse(null);
    }

    public NPC getNPCFromID(String ID) {
        return spawnedNPCs.stream().filter(npc -> Objects.equals(npc.getID(), ID)).findFirst().orElse(null);
    }

    public NPC getNPCFromID(UUID uuid) {
        return getNPCFromID(spawnedNPCsID.get(uuid));
    }

    public boolean existNPC(String customName) {
        customName = customName.replace("§", "&");
        return getNPCFromCustomName(customName) != null;
    }

    public boolean existNPC(UUID uuid) {
        return getNPCFromID(uuid) != null;
    }
}
