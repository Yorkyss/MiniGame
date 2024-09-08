package com.yorkys.plugintest.players;

public enum PlayerType {
    TYPE1("type1"),
    TYPE2("type2"),
    TYPE3("type3");

    private final String name;

    PlayerType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}