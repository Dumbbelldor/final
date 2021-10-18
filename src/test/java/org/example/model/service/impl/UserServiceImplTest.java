package org.example.model.service.impl;

import org.example.model.entity.User;
import org.example.model.service.UserService;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UserServiceImplTest {

    private static final UserService service = UserServiceImpl.INSTANCE;

    @Test
    public void testUploadImageById() {
        service.uploadImageById("/sosos", 1L);
    }
}