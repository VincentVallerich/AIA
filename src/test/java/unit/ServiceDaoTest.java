package unit;

import dao.ServiceDao;
import model.Service;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import provider.ServiceDaoProvider;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceDaoTest {
    static ServiceDao dao;

    @BeforeAll
    public static void init() {
        dao = ServiceDaoProvider.getServiceDao();
    }

    @BeforeEach
    public void clear() {
        for (long id : dao.findAll().stream().map(Service::getId).collect(Collectors.toList())) {
            dao.delete(id);
        }
    }

    @Test
    void findAllReturnInitiallyEmptyList() {
        assertTrue(dao.findAll().isEmpty());
    }

    @Test
    void findAllShouldReturnOneService() {
        Service service = new Service("service1", "desc", 1);
        dao.insert(service);
        assertAll(
            () -> assertEquals(1, dao.findAll().size()),
            () -> assertTrue(dao.findAll().contains(service))
        );
    }

    @Test
    void findAllReturnMultipleServices() {
        Service service = new Service("service1", "desc", 1);
        Service service2 = new Service("service2", "desc", 4);
        dao.insert(service);
        dao.insert(service2);
        assertAll(
                () -> assertEquals(2, dao.findAll().size()),
                () -> assertTrue(dao.findAll().contains(service)),
                () -> assertTrue(dao.findAll().contains(service2))
        );
    }

    @Test
    void canRetrieveServiceById() {
        Service service = new Service("service1", "desc", 1);
        Service service2 = new Service("service2", "desc", 4);
        dao.insert(service);
        dao.insert(service2);
        assertAll(
            () -> assertEquals(service, dao.findById(service.getId())),
            () -> assertEquals(service2, dao.findById(service2.getId()))
        );
    }
}
