package dao;

import model.User;

import java.util.List;

public interface UserDao {
    public List<User> getAllUsers();
    public void addUser(User user);
    public void deleteUser(User user);
}
