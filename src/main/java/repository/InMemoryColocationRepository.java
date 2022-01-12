package repository;

import dao.ColocationDao;
import dao.ServiceDao;
import model.Colocation;
import model.Service;
import provider.ServiceDaoProvider;

import java.util.*;

public class InMemoryColocationRepository implements ColocationDao {
    private static InMemoryColocationRepository instance;
    private final List<Colocation> colocations;
    private final Map<Long, List<Long>> colocationsServicesAssociations;
    private long idCount = 0;
    private final ServiceDao serviceDao = ServiceDaoProvider.getServiceDao();

    private InMemoryColocationRepository()
    {
        this.colocations = new ArrayList<>();
        this.colocationsServicesAssociations = new HashMap<>();
    }

    public static InMemoryColocationRepository getInstance() {
        if (instance == null) {
            instance = new InMemoryColocationRepository();
        }

        return instance;
    }

    @Override
    public List<Colocation> findAll() {
        return colocations;
    }

    @Override
    public Optional<Colocation> findById(long id) {
        return colocations.stream().filter(x -> x.getId() == id).findFirst();
    }

    @Override
    public void delete(long id) {
        findById(id).ifPresent(colocations::remove);
    }

    @Override
    public boolean insert(Colocation colocation) {
        colocation.setId(idCount++);
        colocationsServicesAssociations.put(colocation.getId(), new ArrayList<>());
        return colocations.add(colocation);
    }

    @Override
    public List<Service> getColocationServices(long colocationId) {
        return serviceDao.getServicesByIds(colocationsServicesAssociations.getOrDefault(colocationId, List.of()));
    }

    @Override
    public Optional<Service> getAService(long colocationId, long serviceId) {
        return getColocationServices(colocationId).stream().filter(x -> x.getId() == serviceId).findFirst();
    }

    @Override
    public void addService(long colocationId, Service service) {
        serviceDao.addService(service);
        List<Long> servicesId = colocationsServicesAssociations.get(colocationId);
        servicesId.add(service.getId());
        colocationsServicesAssociations.put(colocationId, servicesId);
    }

    @Override
    public void deleteService(long colocationId, long serviceId) {
        serviceDao.findById(serviceId).ifPresent(s -> {
            colocationsServicesAssociations.get(colocationId).remove(s.getId());
            serviceDao.delete(s.getId());
        });
    }
}
