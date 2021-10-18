package org.example.model.entity.enumeration;

public enum Level {

    NEWBIE(500, "Newbie"),
    ROOKIE(1000, "Rookie"),
    NOVICE(1500, "Novice"),
    NEOPHYTE(2000, "Neophyte"),
    INITIATE(2500, "Initiate"),

    ADEPT(3000, "Adept"),
    CAPABLE(4000, "Capable"),
    PROSPECTIVE(5000, "Prospective"),
    HOT_SHOT(6000, "Hot-Shot"),
    RISING_STAR(7000, "Rising Star"),

    INGENIOUS(8000, "Ingenious"),
    SCHOLAR(10000, "Scholar"),
    MASTER_OF_ALL_TRADES(12000, "Jack Of All Trades"),
    GRAND_CHIEF(14000, "Grand Chief"),
    GOD_OF_SCIENCE(16000, "God Of Science");

    private final int levelThreshold;
    private final String desc;

    Level(int levelThreshold, String desc) {
        this.levelThreshold = levelThreshold;
        this.desc = desc;
    }

    public int getLevelThreshold() {
        return levelThreshold;
    }

    public String getDescription() {
        return desc;
    }

    public static Level next(Level currentLevel) {
        Level result = currentLevel;
        Level[] values = Level.values();

        int currentPos = currentLevel.ordinal();
        int maxPos = values.length - 1;

        if (currentPos < maxPos) {
            int nextPos = currentPos + 1;
            result = values[nextPos];
        }

        return result;
    }
}
