package repository;

import dao.UserDao;
import model.User;

import java.util.*;

public class MemoryUserDao implements UserDao {
    private List<User> users;
    private static MemoryUserDao instance;
    private long idCounter = 0;
    private final Map<Long,String> userTokenAssociations;

    private MemoryUserDao() {
        this.users = new ArrayList<>();
        this.userTokenAssociations= new HashMap<>();
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
    public Optional<User> findByMail(String mail) {
        return users.stream().filter(x -> x.getEmail().equals(mail)).findFirst();
    }

    @Override
    public boolean findAuthy(String username, String password) {
        return users.stream().anyMatch(x -> x.getEmail().equals("") && x.getPassword().equals(""));
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

    @Override
    public void addToken(long userId, String token) {
        userTokenAssociations.put(userId, token);
    }
}