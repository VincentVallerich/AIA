package repository;

import dao.ServiceDao;
import model.Service;

import java.util.ArrayList;
import java.util.List;

public class InMemoryServiceRepository implements ServiceDao {

    private static InMemoryServiceRepository instance;
    private final List<Service> services;
    private long idCounter;

    private InMemoryServiceRepository() {
        this.services = new ArrayList<>();
        this.idCounter = 0;
    }

    public static InMemoryServiceRepository getInstance() {
        if (instance == null) {
            instance = new InMemoryServiceRepository();
        }
        return instance;
    }

    @Override
    public void addService(Service service) {
        if (!this.services.contains(service)) {
            services.add(service);
        }
    }

    @Override
    public void acceptService(long id) {
        this.services.stream().filter(x -> x.getId() == id).findFirst().ifPresent(Service::accept);
    }

    @Override
    public void rejectService(long id) {
        this.services.stream().filter(x -> x.getId() == id).findFirst().ifPresent(Service::reject);
    }

    @Override
    public List<Service> findAll() {
        return services;
    }

    @Override
    public Service findById(long id) {
        return services.stream().filter(x -> x.getId() == id).findFirst().orElseGet(Service::new);
    }

    @Override
    public void delete(long id) {
        services.stream().filter(x -> x.getId() == id).findFirst().ifPresent(services::remove);
    }

    @Override
    public boolean insert(Service service) {
        service.setId(idCounter);
        idCounter++;
        return services.add(service);
    }
}
