package com.yorkys.plugintest.NPC;

import com.yorkys.plugintest.MiniGame;

import java.util.*;

public class NPCManager {
    private MiniGame miniGame;

    private List<NPC> npcs = new ArrayList<>();
    private HashMap<UUID, String> npcsID = new HashMap<>();

    public NPCManager(MiniGame miniGame) {
        this.miniGame = miniGame;
    }

    public NPC createNPC(NPC npc) {
        npc.spawn();
        addNPC(npc);
        return npc;
    }

    public void addNPC(NPC npc) {
        npcs.add(npc);
        npcsID.put(npc.nmsEntity.getUniqueID(), npc.getID());
    }

    public void removeNPC(NPC npc) {
        npcs.remove(npc);
        npcsID.remove(npc.nmsEntity.getUniqueID());
    }

    public NPC getNPCFromCustomName(String customName) {
        return npcs.stream().filter(npc -> Objects.equals(npc.getCustomName(), customName)).findFirst().orElse(null);
    }

    public NPC getNPCFromID(String ID) {
        return npcs.stream().filter(npc -> Objects.equals(npc.getID(), ID)).findFirst().orElse(null);
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
