package unit;

import dao.ServiceDao;
import model.Service;
import model.State;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import provider.ServiceDaoProvider;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceTest {

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
    public void userCanAddAService() {
        Service service = new Service();
        dao.addService(service);
        assertEquals(1, dao.findAll().size());
    }

    @Test
    public void userCantAddAServiceThatAlreadyExist() {
        Service service = new Service();
        dao.addService(service);
        dao.addService(service);
        assertEquals(1, dao.findAll().size());
    }

    @Test
    public void beneficiaryCanValidateAService() {
        Service service = new Service();
        dao.insert(service);
        dao.acceptService(service.getId());
        assertEquals(State.ACCEPT, dao.findById(service.getId()).getState());
    }

    @Test
    public void beneficiaryCanRejectAService() {
        Service service = new Service("test", "desc", 1);
        dao.insert(service);
        dao.rejectService(service.getId());
        assertEquals(State.REJECT, dao.findById(service.getId()).getState());
    }
}
