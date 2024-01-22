package services;

import com.google.common.hash.Hashing;
import models.User;

import java.nio.charset.StandardCharsets;

public class LoginService {
    public String hashPassword(String password) {
        return Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
    }

    public User login(String username, String password) {
        User user = new UserDAO().getByUsername(username);
        if (user != null && user.getPassword().equals(hashPassword(password))) {
            return user;
        }
        return null;
    }

    public User register(String username, String password) {
        User user = null;
        UserDAO udao = new UserDAO();

        Integer id = udao.create(new User(username, hashPassword(password)));
        if (id != null) {
            user = udao.getByID(id);
        }

        return user;
    }
}
