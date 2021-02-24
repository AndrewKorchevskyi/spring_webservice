package com.simplewebservice.users;

import com.simplewebservice.exceptions.UserNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserService {

    private static List<User> users = new ArrayList<>();

    private static int usersCount = 3;

    static {
        users.add(new User(1, "Admin1", new Date()));
        users.add(new User(2, "Admin2", new Date()));
        users.add(new User(3, "Admin3", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User findOne(int id) {
        for (User user: users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    public User deleteById(int id) {
        Iterator<User> userIterator = users.iterator();
        while (userIterator.hasNext()) {
            User user = userIterator.next();
            if (user.getId() == id) {
                userIterator.remove();
                return user;
            }
        }
        return null;
    }

    public User updateById(int id, User user) {
        for (User currentUser : users) {
            if (currentUser.getId() == id) {
                currentUser.setName(user.getName());
                currentUser.setBirthDate(user.getBirthDate());
                return currentUser;
            }
        }
        throw new UserNotFoundException("User to update was not found");
    }
}
