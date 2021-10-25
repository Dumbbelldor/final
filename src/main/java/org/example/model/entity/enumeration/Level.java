package org.example.model.entity.enumeration;

/**
 * Representation of a primary key of
 * the Level table.
 */
public enum Level {

    NEWBIE,
    ROOKIE,
    NOVICE,
    NEOPHYTE,
    INITIATE,

    ADEPT,
    CAPABLE,
    PROSPECTIVE,
    HOT_SHOT,
    RISING_STAR,

    INGENIOUS,
    SCHOLAR,
    MASTER_OF_ALL_TRADES,
    GRAND_CHIEF,
    GOD_OF_SCIENCE;

    /**
     * Returns next enum constant relatively to the given
     * if possible, otherwise returns the given constant.
     *
     * @param currentLevel an enum constant
     *
     * @return next enum constant
     */
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
