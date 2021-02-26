package com.simplewebservice;

import static org.assertj.core.api.Assertions.assertThat;

import com.simplewebservice.users.User;
import com.simplewebservice.users.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.Date;

@SpringBootTest
public class SimpleWebserviceApplicationTests {

	@Autowired
	private UserController userController;

	@Test
	public void contextLoads() {
		assertThat(userController).isNotNull();
	}

	@Test
	public void createUser() {
		String testUserName = "TestUserCreate";
		Date testUserBirthDate = new Date();
		User user = userController.createUser(new User(testUserName, testUserBirthDate)).getBody();
		assertThat(user.getId()).isNotNull();
		assertThat(user.getName()).isEqualTo(testUserName);
		assertThat(user.getBirthDate()).isEqualTo(testUserBirthDate);
		userController.deleteUser(user.getId());
	}

	@Test
	public void updateUser() {
		String testUserName = "TestUserName";
		Date testUserBirthDate = new Date();
		User user = userController.createUser(new User(testUserName, testUserBirthDate)).getBody();

		testUserName = "UpdatedTestUserName";
		testUserBirthDate = new Date();
		user.setName(testUserName);
		user.setBirthDate(testUserBirthDate);
		userController.updateUser(user.getId(), user);

		User updatedUser = userController.retrieveUser(user.getId()).getContent();

		assertThat(user.getId()).isEqualTo(updatedUser.getId());
		assertThat(testUserName).isEqualTo(updatedUser.getName());
		assertThat(testUserBirthDate).isEqualTo(updatedUser.getBirthDate());
		userController.deleteUser(updatedUser.getId());
	}

	@Test
	public void deleteUserById() {
		User user = userController.createUser(new User("UserToDelete", new Date())).getBody();
		assertThat(userController.deleteUser(user.getId()).getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}
}
