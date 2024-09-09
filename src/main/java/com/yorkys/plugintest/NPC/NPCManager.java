package com.yorkys.plugintest.NPC;

import com.yorkys.plugintest.MiniGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NPCManager {
    private MiniGame miniGame;

    private List<NPC> npcs = new ArrayList<>();

    public NPCManager(MiniGame miniGame) {
        this.miniGame = miniGame;
    }

    public void addNPC(NPC npc) {
        npcs.add(npc);
    }

    public void removeNPC(NPC npc) {
        npcs.remove(npc);
    }

    public NPC getNPC(String name) {
        return npcs.stream().filter(npc -> Objects.equals(npc.getName(), name)).findFirst().orElse(null);
    }
}
