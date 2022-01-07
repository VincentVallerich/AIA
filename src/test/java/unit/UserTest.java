package unit;

import model.User;
import org.junit.jupiter.api.BeforeEach;

public class UserTest {

    private User myUser;

    @BeforeEach
    private void creation() {
        this.myUser = new User("john.doe@email.com", "password", "John", "Doe");

    }
}
