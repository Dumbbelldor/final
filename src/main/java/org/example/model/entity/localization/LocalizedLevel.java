package org.example.model.entity.localization;

import org.example.model.entity.enumeration.Level;

/**
 * Wraps {@link Level} level, experience and localized name parameters
 * to store as one object.
 */
public record LocalizedLevel(Level level, int exp, String name) {}
