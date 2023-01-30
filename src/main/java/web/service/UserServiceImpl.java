package web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.dao.UserDaoImpl;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    public UserServiceImpl (UserDao userDao) {
        this.userDao = userDao;
    }
    public void createUsersTable() {
        userDao.createUsersTable();
    }
    public void saveUser(User user) {
        userDao.saveUser(user);
    }
    public void updateUser(User user) {
        userDao.updateUser(user);
    }
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }
    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }
    public List<User> getAllUsers() {
        List<User> userList = userDao.getAllUsers();
        return userList;
    }
}
