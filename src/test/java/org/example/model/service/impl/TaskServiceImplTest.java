package org.example.model.service.impl;

import org.example.model.entity.Task;
import org.example.model.entity.enumeration.Category;
import org.example.model.entity.enumeration.Language;
import org.example.model.entity.enumeration.SolutionStatus;
import org.example.model.entity.localization.LocalizedCategory;
import org.example.model.entity.wrapper.SolvedTasksWrapper;
import org.example.model.entity.wrapper.StatusTaskWrapper;
import org.example.model.service.TaskService;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

import static org.testng.Assert.*;
import static org.mockito.Mockito.*;

public class TaskServiceImplTest {

    @Mock
    private final TaskService service = mock(TaskService.class);

    private Task task;

    @BeforeMethod
    public void setup() {
        task = Task.newBuilder().build();
    }

    @Test
    public void testFindById() {
        when(service.findById(anyLong(), any(Language.class)))
                .thenReturn(Optional.of(task));
        Task actual = service.findById(10L, Language.GERMAN).orElseThrow();
        assertEquals(actual, task);
    }

    @Test
    public void testFindAll() {
        when(service.findAll(any(Language.class))).thenReturn(List.of(task));
        List<Task> actual = service.findAll(Language.GERMAN);
        assertEquals(actual, List.of(task));
    }

    @Test
    public void testSave() {
        when(service.save(any(Task.class), any(Language.class))).thenReturn(true);
        assertTrue(service.save(task, Language.GERMAN));
    }

    @Test
    public void testFindByCategory() {
        when(service.findByCategory(any(Category.class), any(Language.class)))
                .thenReturn(List.of(task));
        List<Task> actual = service.findByCategory(Category.CHEMISTRY, Language.RUSSIAN);
        assertEquals(actual, List.of(task));
    }

    @Test
    public void testCountProgression() {
        var solved = new SolvedTasksWrapper(Category.ECONOMICS.name(), 10, 15);
        when(service.countProgression(anyLong(), any(Language.class)))
                .thenReturn(List.of(solved));
        var actual = service
                .countProgression(12L, Language.RUSSIAN);
        assertEquals(actual, List.of(solved));
    }

    @Test
    public void testSaveTaskForUser() {
        when(service.saveTaskForUser(anyLong(), anyLong())).thenReturn(true);
        assertTrue(service.saveTaskForUser(124L, 144L));
    }

    @Test
    public void testFindAllSolvedTasksForUser() {
        when(service.findAllSolvedTasksForUser(anyLong(), any(Language.class)))
                .thenReturn(List.of(task));
        var actual = service
                .findAllSolvedTasksForUser(124L, Language.RUSSIAN);
        assertEquals(actual, List.of(task));
    }

    @Test
    public void testIsSolvedTaskPresent() {
        when(service.isSolvedTaskPresent(anyLong(), anyLong()))
                .thenReturn(true);
        assertTrue(service.isSolvedTaskPresent(14L, 61L));
    }

    @Test
    public void testResolveTasksForUserByCategory() {
        var wrapped = new StatusTaskWrapper(task, SolutionStatus.SOLVED);
        when(service.resolveTasksForUserByCategory(anyLong(),
                any(Language.class), any(Category.class)))
                .thenReturn(List.of(wrapped));
        var actual = service
                .resolveTasksForUserByCategory(1L,
                        Language.RUSSIAN, Category.ECONOMICS);
        assertEquals(actual, List.of(wrapped));
    }

    @Test
    public void testFindAllTaskCategories() {
        var localized = new LocalizedCategory(Category.CHEMISTRY,
                "name", "flavor");
        when(service.findAllTaskCategories(Language.ENGLISH))
                .thenReturn(List.of(localized));
        var actual = service.findAllTaskCategories(Language.GERMAN);
        assertEquals(actual, List.of(localized));
    }
}