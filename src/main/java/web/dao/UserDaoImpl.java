package web.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entitymanager;
    
    public UserDaoImpl() {

    }
    String sql_request;

    @Override
    @Transactional
    public void createUsersTable() {

        sql_request = "CREATE TABLE IF NOT EXISTS users " +
                "(ID SERIAL PRIMARY KEY, " +
                " NAME VARCHAR(255) NOT NULL, " +
                " LASTNAME VARCHAR(255) NOT NULL, " +
                " AGE INTEGER)";
        entitymanager.createNativeQuery(sql_request).executeUpdate();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
            entitymanager.persist(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        entitymanager.merge(user);
    }

    @Override
    @Transactional
    public void removeUserById(long id) {
        User user = entitymanager.find(User.class, id);
        if (user != null) {
            entitymanager.remove(user);
        }
    }
    @Override
    @Transactional
    public User getUserById(long id) {
        return entitymanager.find(User.class, id);
    }
    @Override
    public List<User> getAllUsers() {
            sql_request = "SELECT * from users";
            List<User> userList = entitymanager.createNativeQuery(sql_request, User.class).getResultList();
            return userList;
    }
}
