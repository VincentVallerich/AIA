package repository;

import dao.UserDao;
import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemoryUserDao implements UserDao {
    private List<User> users;
    private static MemoryUserDao instance;
    private long idCounter = 0;

    private MemoryUserDao() {
        this.users = new ArrayList<>();
    }

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
    public Optional<User> findById(long id) {
        return users.stream().filter(x -> x.getId()==id).findFirst();
    }

    @Override
    public void delete(long id)
    {
        findById(id).ifPresent(users::remove);
    }

    @Override
    public boolean insert(User user) {
        user.setId(idCounter++);
        return users.add(user);
    }

    @Override
    public boolean verifyToken(String token) {
        for (int i = 0; i < users.size(); i ++) {
            if (users.get(i).getToken()==token) {
                return true;
            }
        }
        return false;
    }
}