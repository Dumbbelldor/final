package org.example.model.service.impl;

import org.example.model.entity.User;
import org.example.model.entity.enumeration.Language;
import org.example.model.entity.enumeration.Level;
import org.example.model.entity.localization.LocalizedLevel;
import org.example.model.service.UserService;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

public class UserServiceImplTest {

    @Mock
    private final UserService service = mock(UserService.class);

    private User optionalUser;

    @BeforeMethod
    public void setup() {
        optionalUser = User.newBuilder().build();
    }

    @Test
    public void testFindById() {
        when(service.findById(anyLong(), any(Language.class)))
                .thenReturn(Optional.of(optionalUser));
        User actual = service.findById(1L, Language.RUSSIAN).orElseThrow();
        assertEquals(actual, optionalUser);
    }

    @Test
    public void testFindAll() {
        when(service.findAll(Language.ENGLISH)).thenReturn(List.of(optionalUser));
        User actual = service.findAll(Language.ENGLISH).get(0);
        assertEquals(actual, optionalUser);
    }


    @Test
    public void testSave() {
        when(service.save(any(User.class))).thenReturn(true);
        boolean actual = service.save(optionalUser);
        assertTrue(actual);
    }

    @Test
    public void testFindByLogin() {
        when(service.findByLogin(anyString(), any(Language.class)))
                .thenReturn(Optional.of(optionalUser));
        User actual = service.findByLogin("whatLogin", Language.GERMAN).orElseThrow();
        assertEquals(actual, optionalUser);
    }

    @Test
    public void testEarnExpAndLevel() {
        when(service.earnExpAndLevel(any(User.class))).thenReturn(true);
        assertTrue(service.earnExpAndLevel(optionalUser));
    }

    @Test
    public void testEarnAchievement() {
        when(service.earnAchievement(anyLong(), anyLong())).thenReturn(true);
        assertTrue(service.earnAchievement(1L, 100L));
    }

    @Test
    public void testGetLevelByUserId() {
        when(service.getLevelByUserId(anyLong(), any(Language.class)))
                .thenReturn(new LocalizedLevel(Level.ADEPT, 1000, "Adept"));
        assertEquals(service.getLevelByUserId(1L, Language.ENGLISH),
                new LocalizedLevel(Level.ADEPT, 1000, "Adept"));
    }

    @Test
    public void testCountSolvedForUser() {
        when(service.countSolvedForUser(anyLong())).thenReturn(10L);
        assertEquals(service.countSolvedForUser(42L), 10L);
    }

    @Test
    public void testDeleteImageById() {
        when(service.deleteImageById(anyLong())).thenReturn(true);
        assertTrue(service.deleteImageById(100L));
    }

    @Test
    public void testUploadImageById() {
        when(service.uploadImageById(anyString(), anyLong())).thenReturn(true);
        assertTrue(service.uploadImageById("some", 124L));
    }
}