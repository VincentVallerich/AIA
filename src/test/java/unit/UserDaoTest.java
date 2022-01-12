package unit;

import dao.UserDao;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import provider.UserDaoProvider;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class UserDaoTest {

    UserDao dao = UserDaoProvider.getUserDao();

    @BeforeEach
    public void clear() {
        for (long id : dao.findAll().stream().map(User::getId).collect(Collectors.toList())) {
            dao.delete(id);
        }
    }

    @Test
    void findAllReturnInitiallyEmptyList() {
        assertTrue(dao.findAll().isEmpty());
    }

    @Test
    void findAllShouldReturnOneService() {
        User user = new User("mail", "password", "first", "last");
        dao.insert(user);
        assertAll(
                () -> assertEquals(1, dao.findAll().size()),
                () -> assertTrue(dao.findAll().contains(user))
        );
    }

    @Test
    void findAllReturnMultipleServices() {
        User user1 = new User("mail1", "pass1", "fisrt1", "last1");
        User user2 = new User("mail2", "pass2", "fisrt2", "last2");
        dao.insert(user1);
        dao.insert(user2);
        assertAll(
                () -> assertEquals(2, dao.findAll().size()),
                () -> assertTrue(dao.findAll().contains(user1)),
                () -> assertTrue(dao.findAll().contains(user2))
        );
    }

    @Test
    void canRetrieveServiceById() {
        User user1 = new User("mail1", "pass1", "fisrt1", "last1");
        User user2 = new User("mail2", "pass2", "fisrt2", "last2");
        dao.insert(user1);
        dao.insert(user2);
        assertAll(
                () -> assertTrue(dao.findById(user1.getId()).isPresent()),
                () -> assertTrue(dao.findById(user2.getId()).isPresent()),
                () -> assertEquals(user1, dao.findById(user1.getId()).get()),
                () -> assertEquals(user2, dao.findById(user2.getId()).get())
        );
    }

}
