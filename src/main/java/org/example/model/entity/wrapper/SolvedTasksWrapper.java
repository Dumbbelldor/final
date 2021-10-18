package org.example.model.entity.wrapper;

/**
 * Wraps {@link String} category, quantity of solved tasks and
 * quantity of all tasks parameters to store them as one object.
 */
public record SolvedTasksWrapper(String category, int solved, int all) {}