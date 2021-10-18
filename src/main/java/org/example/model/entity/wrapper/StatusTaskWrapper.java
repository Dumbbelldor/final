package org.example.model.entity.wrapper;

import org.example.model.entity.Task;
import org.example.model.entity.enumeration.SolutionStatus;

/**
 * Wraps {@link Task}, {@link SolutionStatus} parameters
 * to store them as one object.
 */
public record StatusTaskWrapper(Task task, SolutionStatus status) {}
