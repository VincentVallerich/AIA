package repository;

import dao.ColocationDao;
import dao.ServiceDao;
import dao.UserDao;
import model.Colocation;
import model.Service;
import model.User;
import provider.ServiceDaoProvider;
import provider.UserDaoProvider;

import java.util.*;

public class InMemoryColocationRepository implements ColocationDao {
    private static InMemoryColocationRepository instance;
    private final List<Colocation> colocations;
    private final Map<Long, List<Long>> colocationsServicesAssociations;
    private final Map<Long, List<Long>> colocationUsersAssociations;
    private long idCount = 0;
    private final ServiceDao serviceDao = ServiceDaoProvider.getServiceDao();
    private final UserDao userDao = UserDaoProvider.getUserDao();

    private InMemoryColocationRepository()
    {
        this.colocations = new ArrayList<>();
        this.colocationsServicesAssociations = new HashMap<>();
        this.colocationUsersAssociations= new HashMap<>();
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
    public long getAdminId(long colocationId) {
        Optional<Colocation> colocation = findById(colocationId);
        if(colocation.isPresent())
        {

            return colocation.get().getAdminId();
        }
        return 0;
    }

    @Override
    public List<User> getUsers(long colocationId) {
        Optional<Colocation> colocation = findById(colocationId);
        if(colocation.isPresent())
        {
            List<User> users = new ArrayList<>(colocationUsersAssociations.size());
            for (long id : colocationUsersAssociations.get(colocationId)) {
                users.add(userDao.findById(id).get());
            }

            return users;
        }
        return List.of();
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
    public void addUsers(long id, User user) {
        userDao.insert(user);
        List<Long> usersId = colocationUsersAssociations.get(id);
        usersId.add(user.getId());
        colocationUsersAssociations.put(id, usersId);
    }

    @Override
    public void deleteService(long colocationId, long serviceId) {
        serviceDao.findById(serviceId).ifPresent(s -> {
            colocationsServicesAssociations.get(colocationId).remove(s.getId());
            serviceDao.delete(s.getId());
        });
    }
}
