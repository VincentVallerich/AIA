package unit;

import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    private User myUser;

    @BeforeEach
    private void creation() {
        this.myUser = new User("john.doe@email.com", "password", "John", "Doe");

    }

    @Test
    private void authentificationTest(){
        if
    }
}
