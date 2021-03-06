package service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import dao.UserDao;
import model.User;
import provider.UserDaoProvider;

import java.util.List;

public class AuthentificationService {

    public static boolean doIKnowHim(User user) {
        return UserDaoProvider.getUserDao().findById(user.getId()).isPresent();
    }

    public static String giveAToken(User user) {
        if (doIKnowHim(user)) {
            try {
                Algorithm algorithm = Algorithm.HMAC256("secret");
                String token = JWT.create()
                        .withIssuer("auth0")
                        .sign(algorithm);
                UserDaoProvider.getUserDao().addToken(user.getId(), token);
                return token;
            } catch (JWTCreationException exception){
                //Invalid Signing configuration / Couldn't convert Claims.
                exception.printStackTrace();
            }
        }
        return null;
    }

    public static boolean verifyAToken(String token) {
        UserDao userDao = UserDaoProvider.getUserDao();
        return userDao.verifyToken(token);
    }
}
