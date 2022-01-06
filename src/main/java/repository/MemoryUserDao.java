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
    public List<User> findAll() {
        return this.users;
    }

    @Override
    public User findById(long id) {
        return users.stream().filter(x -> x.getId()==id).findFirst().get();
    }

    @Override
    public void delete(long id) {
        users.remove(findById(id));
    }

    @Override
    public boolean insert(User user) {
        return users.add(user);
    }
}
