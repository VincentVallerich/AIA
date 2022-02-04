package dao;

import model.User;

import java.util.Optional;

public interface UserDao extends BasicDao<User> {
    Optional<User> findByMail(String mail);

    boolean findAuthy(String username, String password);
    void addToken(long userId, String token);
    boolean verifyToken(String token);

}
