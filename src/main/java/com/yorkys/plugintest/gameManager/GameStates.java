package com.yorkys.plugintest.gameManager;

public enum GameStates {
    QUEUE("queue"),
    PLAYING("playing"),
    FINISHING("finishing");

    private final String name;

    GameStates(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public static GameStates fromName(String name) {
        for (GameStates type : GameStates.values()) {
            if (type.getName().equalsIgnoreCase(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No enum constant with name: " + name);
    }
}