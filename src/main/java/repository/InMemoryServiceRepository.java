package repository;

import dao.ServiceDao;
import model.Service;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class InMemoryServiceRepository implements ServiceDao {

    private static InMemoryServiceRepository instance;
    private List<Service> services;

    private InMemoryServiceRepository() {
        this.services = new ArrayList<>();
    }

    public static InMemoryServiceRepository getInstance() {
        if (instance == null) {
            instance = new InMemoryServiceRepository();
        }
        return instance;
    }

    @Override
    public void addService(Service service) {
        services.add(service);
    }

    @Override
    public int getServiceCount() {
        return services.size();
    }
}
