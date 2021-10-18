package org.example.model.entity.enumeration;

public enum Difficulty {

    VERY_EASY("Very Easy"),
    EASY("Easy"),
    MEDIUM("Medium"),
    HARD("Hard"),
    VERY_HARD("Very Hard");

    private final String desc;

    Difficulty(String desc) {
        this.desc = desc;
    }

    public String getDescription() {
        return desc;
    }
}
