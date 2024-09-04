package com.yorkys.plugintest.players;

public enum PlayerType {
    TYPE1(1),
    TYPE2(2),
    TYPE3(3);

    private final int test;

    PlayerType(int test) {
        this.test = test;
    }

    public int getTest() {
        return test;
    }

}