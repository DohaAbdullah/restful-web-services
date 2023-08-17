package com.rest.webservices.restfulwebservices;

import com.rest.webservices.restfulwebservices.controller.UserJpaResource;
import com.rest.webservices.restfulwebservices.entitiy.User;
import com.rest.webservices.restfulwebservices.jpa.UserRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private UserRepository repository;
    public static int userCount = 0;

    static {
        users.add(new User(++userCount, "Doha", LocalDate.now().minusYears(30)));
        users.add(new User(++userCount, "Bahar", LocalDate.now().minusYears(25)));
        users.add(new User(++userCount, "Sparingly", LocalDate.now().minusYears(22)));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        user.setId(++userCount);
        users.add(user);
        return user;
    }

    public User findOne(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public void deleteById(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }

}
