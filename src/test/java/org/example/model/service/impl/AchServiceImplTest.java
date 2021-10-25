package org.example.model.service.impl;

import org.example.model.entity.Achievement;
import org.example.model.entity.enumeration.Language;
import org.example.model.service.AchService;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

import static org.testng.Assert.*;
import static org.mockito.Mockito.*;

public class AchServiceImplTest {

    @Mock
    private final AchService service = mock(AchService.class);

    private Achievement ach;

    @BeforeMethod
    public void setup() {
        ach = Achievement.newBuilder().build();
    }

    @Test
    public void testFindById() {
        when(service.findById(anyLong(), any(Language.class)))
                .thenReturn(Optional.of(ach));
        var actual = service.findById(124L, Language.GERMAN)
                .orElseThrow();
        assertEquals(actual, ach);
    }

    @Test
    public void testFindAll() {
        when(service.findAll(any(Language.class))).thenReturn(List.of(ach));
        var actual = service.findAll(Language.ENGLISH);
        assertEquals(actual, List.of(ach));
    }

    @Test
    public void testSave() {
        when(service.save(any(Achievement.class), any(Language.class)))
                .thenReturn(true);
        assertTrue(service.save(ach, Language.GERMAN));
    }

    @Test
    public void testFindAllAchForUser() {
        when(service.findAllAchForUser(anyLong(), any(Language.class)))
                .thenReturn(List.of(ach));
        var actual = service.findAllAchForUser(214L, Language.ENGLISH);
        assertEquals(actual, List.of(ach));
    }

    @Test
    public void testFindUnclaimedForUser() {
        when(service.findUnclaimedForUser(anyLong(), any(Language.class)))
                .thenReturn(List.of(ach));
        var actual = service
                .findUnclaimedForUser(51L, Language.ENGLISH);
        assertEquals(actual, List.of(ach));
    }
}