package repository;

import dao.ColocationDao;
import dao.ServiceDao;
import model.Colocation;
import model.Service;
import provider.ServiceDaoProvider;

import java.util.*;

public class H2ColocationRepository implements ColocationDao {

    @Override
    public List<Colocation> findAll() {
        // Select * from Colocation
        return null;
    }

    @Override
    public Optional<Colocation> findById(long id) {
        // Select * from colocation where colocation.id = id
        return Optional.empty();
    }

    @Override
    public void delete(long id) {
        // delete from colocation where colocation.id = id
    }

    @Override
    public boolean insert(Colocation colocation) {
        // INSERT INTO Colocation VALUES colocation
        return false;
    }

    @Override
    public List<Service> getColocationServices(long colocationId) {
        // Select * from service join collocService on service.serviceid = collocService.serviceid where collocService.coloid = colocationId
        return null;
    }

    @Override
    public Optional<Service> getAService(long colocationId, long serviceId) {

        return Optional.empty();
    }

    @Override
    public void deleteService(long colocationId, long serviceId) {

    }

    @Override
    public void addService(long id, Service service) {

    }
}
