package unit;

import dao.ColocationDao;
import dao.ServiceDao;
import dao.UserDao;
import model.Colocation;
import model.Service;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import provider.ColocationDaoProvider;
import provider.ServiceDaoProvider;
import provider.UserDaoProvider;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class ColocationDaoTest {

    private ColocationDao colocationDao = ColocationDaoProvider.getColocationDao();
    private UserDao userDao = UserDaoProvider.getUserDao();
    private ServiceDao serviceDao = ServiceDaoProvider.getServiceDao();
    private User adminUser = new User("mail", "pass", "first", "last");

    @BeforeEach
    public void clear() {
        for (long id : colocationDao.findAll().stream().map(Colocation::getId).collect(Collectors.toList())) {
            colocationDao.delete(id);
        }

        for (long id : userDao.findAll().stream().map(User::getId).collect(Collectors.toList())) {
            userDao.delete(id);
        }

        for (long id : serviceDao.findAll().stream().map(Service::getId).collect(Collectors.toList())) {
            serviceDao.delete(id);
        }
    }

    @Test
    void findAllReturnInitiallyEmptyList() {
        assertTrue(colocationDao.findAll().isEmpty());
    }

    @Test
    void findAllShouldReturnOneService() {
        userDao.insert(adminUser);
        Colocation colocation = new Colocation("coloc1", adminUser.getId());
        colocationDao.insert(colocation);
        assertAll(
                () -> assertEquals(1, colocationDao.findAll().size()),
                () -> assertTrue(colocationDao.findAll().contains(colocation))
        );
    }

    @Test
    void findAllReturnMultipleServices() {
        userDao.insert(adminUser);
        Colocation colocation = new Colocation("coloc1", adminUser.getId());
        Colocation colocation2 = new Colocation("coloc2", adminUser.getId());
        colocationDao.insert(colocation);
        colocationDao.insert(colocation2);
        assertAll(
                () -> assertEquals(2, colocationDao.findAll().size()),
                () -> assertTrue(colocationDao.findAll().contains(colocation)),
                () -> assertTrue(colocationDao.findAll().contains(colocation2))
        );
    }

    @Test
    void canRetrieveServiceById() {
        userDao.insert(adminUser);
        Colocation colocation = new Colocation("coloc1", adminUser.getId());
        Colocation colocation2 = new Colocation("coloc2", adminUser.getId());
        colocationDao.insert(colocation);
        colocationDao.insert(colocation2);
        assertAll(
                () -> assertTrue(colocationDao.findById(colocation.getId()).isPresent()),
                () -> assertEquals(colocation, colocationDao.findById(colocation.getId()).get()),
                () -> assertEquals(colocation2, colocationDao.findById(colocation2.getId()).get())
        );
    }

    @Test
    void canAddServiceInColocation() {
        Service service = new Service("title", "description", 1);
        userDao.insert(adminUser);
        Colocation colocation = new Colocation("coloc1", adminUser.getId());
        colocationDao.insert(colocation);
        colocationDao.addService(colocation.getId(), service);
        assertAll(
                () -> assertEquals(1, colocationDao.getColocationServices(colocation.getId()).size()),
                () -> assertEquals(1, serviceDao.findAll().size()),
                () -> assertTrue(serviceDao.findById(service.getId()).isPresent()),
                () -> assertEquals(service, serviceDao.findById(service.getId()).get()),
                () -> assertTrue(colocationDao.getAService(colocation.getId(), service.getId()).isPresent()),
                () -> assertEquals(service, colocationDao.getAService(colocation.getId(), service.getId()).get())
        );
    }

    @Test
    void deleteColocationService() {
        Service service = new Service("title", "description", 1);
        userDao.insert(adminUser);
        Colocation colocation = new Colocation("coloc1", adminUser.getId());
        colocationDao.insert(colocation);
        colocationDao.addService(colocation.getId(), service);
        colocationDao.deleteService(colocation.getId(), service.getId());
        assertAll(
                () -> assertFalse(colocationDao.getAService(colocation.getId(), service.getId()).isPresent()),
                () -> assertFalse(serviceDao.findById(service.getId()).isPresent())
        );
    }
}
