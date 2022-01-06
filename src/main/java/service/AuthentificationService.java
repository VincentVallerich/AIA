package service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import dao.UserDao;
import model.User;

import java.util.List;

public class AuthentificationService {
    UserDao dao;
    public boolean doIKnowHim(User user) {
        List<User> users = dao.getAllUsers();
        return users.contains(user);
    }

    public String giveAToken(User user) {
        if (doIKnowHim(user)) {
            try {
                Algorithm algorithm = Algorithm.HMAC256("secret");
                String token = JWT.create()
                        .withIssuer("auth0")
                        .sign(algorithm);
                return token;
            } catch (JWTCreationException exception){
                //Invalid Signing configuration / Couldn't convert Claims.
                exception.printStackTrace();
            }
        }
        return null;
    }

    public boolean verifyAToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception){
            return false;
        }
    }
}
