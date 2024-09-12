package com.yorkys.plugintest.gameManager;

public enum GameRounds {
    ROUND1(1),
    ROUND2(2),
    ROUND3(3);

    private final int round;

    GameRounds(int round) {
        this.round = round;
    }

    public int getRound() { return round; }

    public static GameRounds fromRound(int round) {
        for (GameRounds type : GameRounds.values()) {
            if (type.getRound() == round) {
                return type;
            }
        }
        return null;
    }
}