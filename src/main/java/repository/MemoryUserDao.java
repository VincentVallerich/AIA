package repository;

import dao.ServiceDao;
import dao.UserDao;
import model.User;

import java.util.List;

public class MemoryUserDao implements UserDao {
    private List<User> users;
    private static MemoryUserDao instance;

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new MemoryUserDao();
        }
        return instance;
    }

    @Override
    public List<User> getAllUsers() {
        return this.users;
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public void deleteUser(User user) {
        users.remove(user);
    }
}
