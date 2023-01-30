package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    void createUsersTable();

    void saveUser(User user);

    void updateUser(User user);

    User getUserById(long id);

    void removeUserById(long id);

    List<User> getAllUsers();

}
