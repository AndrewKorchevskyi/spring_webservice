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
	public void createUserWithGeneratedId() {
		String testUserName = "Unit Tests User";
		Date testUserBirthDate = new Date();
		User user = userController.createUser(new User(null, testUserName, testUserBirthDate)).getBody();
		assertThat(user.getId()).isNotNull();
		assertThat(user.getName()).isEqualTo(testUserName);
		assertThat(user.getBirthDate()).isEqualTo(testUserBirthDate);
		userController.deleteUser(user.getId());
	}

	@Test
	public void createUserWithSpecifiedId() {
		int testUserId = 666;
		String testUserName = "Unit Tests User 2";
		Date testUserBirthDate = new Date();
		User user = userController.createUser(new User(testUserId, testUserName, testUserBirthDate)).getBody();
		assertThat(user.getId()).isEqualTo(testUserId);
		assertThat(user.getName()).isEqualTo(testUserName);
		assertThat(user.getBirthDate()).isEqualTo(testUserBirthDate);
		userController.deleteUser(user.getId());
	}

	@Test
	public void updateUser() {
		int testUserId = 747;

		String testUserName = "Unit Tests User 3";
		Date testUserBirthDate = new Date();
		userController.createUser(new User(testUserId, testUserName, testUserBirthDate));

		testUserName = "Updated Name";
		testUserBirthDate = new Date();
		User user2 = new User(testUserId, testUserName, testUserBirthDate);
		userController.updateUser(testUserId, user2);

		User updatedUser = userController.retrieveUser(testUserId).getContent();

		assertThat(user2.getId()).isEqualTo(updatedUser.getId());
		assertThat(user2.getName()).isEqualTo(updatedUser.getName());
		assertThat(user2.getBirthDate()).isEqualTo(updatedUser.getBirthDate());
		userController.deleteUser(updatedUser.getId());
	}

	@Test
	public void deleteUserById() {
		int testUserId = 777;
		User user = userController.createUser(new User(testUserId, "Unit Tests User 4", new Date())).getBody();
		assertThat(userController.deleteUser(user.getId()).getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}
}
