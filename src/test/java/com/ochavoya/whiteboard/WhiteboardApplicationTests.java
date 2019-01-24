
package com.ochavoya.whiteboard;

import com.ochavoya.whiteboard.dto.UserLoginDTO;
import com.ochavoya.whiteboard.dto.UserRegisterDTO;
import com.ochavoya.whiteboard.dto.WhiteboardItemDTO;
import com.ochavoya.whiteboard.dto.WhiteboardResponse;
import com.ochavoya.whiteboard.entities.UserEntity;
import com.ochavoya.whiteboard.repository.UserRepository;
import com.ochavoya.whiteboard.service.UserRepositoryService;
import com.ochavoya.whiteboard.service.WhiteboardDataRepositoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WhiteboardApplicationTests {

    private Pattern pattern = Pattern.compile("^[0123456789abcdef]{32}$");

    @Autowired
    private UserRepositoryService userRepositoryService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WhiteboardDataRepositoryService whiteboardDataRepositoryService;

    @Test
    public void contextLoads() {
    }

    private WhiteboardResponse registerValidUser() {

        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setUsername("__test__");
        userRegisterDTO.setName("John Doe");
        userRegisterDTO.setPassword("password");

        return userRepositoryService.register(userRegisterDTO);
    }

    private WhiteboardItemDTO createValidRecord() {

        registerValidUser();
        String token = userRepositoryService.login(new UserLoginDTO("__test__", "password")).getMessage();

        Date date = new Date();
        date.setTime(date.getTime() + 24 * 3600 * 1000);

        WhiteboardItemDTO whiteboardItemDTO = new WhiteboardItemDTO(1, 1, "item title", "item description",
                new Timestamp(date.getTime()), token);
        return whiteboardDataRepositoryService.create(whiteboardItemDTO);
    }

    @Test
    @Transactional
    public void test_UserRegistration() {
        // Act
        WhiteboardResponse response = registerValidUser();
        List<UserEntity> userEntityList = userRepository.getUserEntitiesByUsername("__test__");

        // Assert
        assertTrue(response.getSuccess());
        assertEquals("User __test__ was successfully registered", response.getMessage());
        assertEquals(1, userEntityList.size());

        // Act - repeat registration
        response = registerValidUser();

        // Assert
        assertFalse(response.getSuccess());
        assertEquals("Username __test__ is already taken", response.getMessage());
    }

    @Test
    @Transactional
    public void test_LoginLogout() {
        // Prepare
        registerValidUser();

        // Act
        WhiteboardResponse response = userRepositoryService.login(new UserLoginDTO("__test__", "password"));

        // Assert
        assertTrue(response.getSuccess());
        assertTrue(pattern.matcher(response.getMessage()).matches());

        // Act
        response = userRepositoryService.logout("__test__");

        // Assert
        assertFalse(response.getSuccess());
        assertEquals("User __test__ was successfully logged out", response.getMessage());

        // Act
        response = userRepositoryService.login(new UserLoginDTO("__test__", "wrong_password"));

        // Assert
        assertFalse(response.getSuccess());
        assertEquals("Wrong username/password", response.getMessage());
    }

    @Test
    @Transactional
    public void test_postItem() {
        // Prepare
        WhiteboardItemDTO whiteboardItemResponse = createValidRecord();

        assertEquals(new Integer("1"), whiteboardItemResponse.getBoardId());
        assertEquals(new Integer("1"), whiteboardItemResponse.getSectionId());
        assertEquals("item title", whiteboardItemResponse.getTitle());
        assertEquals("item description", whiteboardItemResponse.getDetail());
    }

    @Test
    @Transactional
    public void test_dataLoad() {
        // Prepare
        createValidRecord();

        List<WhiteboardItemDTO> response = whiteboardDataRepositoryService.load();

        assertTrue(response.size() == 1);
    }
}