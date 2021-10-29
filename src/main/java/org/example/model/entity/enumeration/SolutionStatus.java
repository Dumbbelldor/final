package org.example.model.entity.enumeration;

/**
 * Representation of the solution status
 * for the {@link org.example.model.entity.Task}
 * entities.
 */
public enum SolutionStatus {

    SOLVED("+++"),
    UNSOLVED("---");

    private final String desc;

    SolutionStatus(String desc) {
        this.desc = desc;
    }

    public String getDescription() {
        return desc;
    }
}
