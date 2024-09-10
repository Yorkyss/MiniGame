package com.yorkys.plugintest.generators;

public enum GeneratorType {
    IRON("iron"),
    GOLD("gold"),
    DIAMOND("diamond");

    private final String name;

    GeneratorType(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public static GeneratorType fromName(String name) {
        for (GeneratorType type : GeneratorType.values()) {
            if (type.getName().equalsIgnoreCase(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No enum constant with name: " + name);
    }
}