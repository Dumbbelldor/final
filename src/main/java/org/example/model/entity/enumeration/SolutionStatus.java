package org.example.model.entity.enumeration;

public enum SolutionStatus {

    SOLVED("Solved"),
    UNSOLVED("Unsolved");

    private final String desc;

    SolutionStatus(String desc) {
        this.desc = desc;
    }

    public String getDescription() {
        return desc;
    }
}
