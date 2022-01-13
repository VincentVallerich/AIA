package dao;

import model.User;

public interface UserDao extends BasicDao<User> {
    boolean verifyToken(String token);
}
