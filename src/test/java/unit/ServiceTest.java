package unit;

import dao.ServiceDao;
import model.Service;
import model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import provider.ServiceDaoProvider;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceTest {

    static ServiceDao dao;
    User user;

    @BeforeAll
    public static void init() {
        dao = ServiceDaoProvider.getServiceDao();
    }

    @BeforeEach
    public void setUser() {
        this.user = new User("mail", "password", "first", "last");
    }

    @Test
    public void userCanAddAService() {
        Service service = new Service();
        int count = dao.getServiceCount();
        dao.addService(service);
        assertEquals(count+1, dao.getServiceCount());
    }



}
