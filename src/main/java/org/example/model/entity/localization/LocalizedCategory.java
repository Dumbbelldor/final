package org.example.model.entity.localization;

import org.example.model.entity.enumeration.Category;

public record LocalizedCategory(Category category, String name, String flavor) {}