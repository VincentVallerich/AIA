package dao;

import model.Colocation;
import model.Service;
import model.User;

import java.util.List;
import java.util.Optional;

public interface ColocationDao extends BasicDao<Colocation> {
    List<Service> getColocationServices(long colocationId);
    Optional<Service> getAService(long colocationId, long serviceId);
    void deleteService(long colocationId, long serviceId);
    void addService(long id, Service service);
    void addUsers(long id, User user);
    void delete(long colocationId);
    long getAdminId(long colocationId);
    List<User> getUsers(long colocationId);
}
