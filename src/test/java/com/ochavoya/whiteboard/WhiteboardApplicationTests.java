package com.ochavoya.whiteboard;

import com.ochavoya.whiteboard.dto.UserRegisterDTO;
import com.ochavoya.whiteboard.dto.WhiteboardResponse;
import com.ochavoya.whiteboard.entities.UserEntity;
import com.ochavoya.whiteboard.repository.UserRepository;
import com.ochavoya.whiteboard.service.UserRepositoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WhiteboardApplicationTests {

	@Autowired
	private UserRepositoryService userRepositoryService;

	@Autowired
	private UserRepository userRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	@Transactional
	public void test_UserRegistration() {
		// Prepare
		UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
		userRegisterDTO.setUsername("__test__");
		userRegisterDTO.setName("John Doe");
		userRegisterDTO.setPassword("password");

		// Act
		WhiteboardResponse response = userRepositoryService.register(userRegisterDTO);
		List<UserEntity> userEntityList = userRepository.getUserEntitiesByUsername("__test__");

		// Assert
		assertTrue(response.getSuccess());
		assertEquals("User __test__ was successfully registered.", response.getMessage());
		assertEquals(userEntityList.size(),1);

		// Act - repeat registration
		response = userRepositoryService.register(userRegisterDTO);

		// Assert
		assertFalse(response.getSuccess());
		assertEquals("Username __test__ is already taken.", response.getMessage());
	}


}

