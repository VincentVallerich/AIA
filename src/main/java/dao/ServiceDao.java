package dao;

import model.Service;

import java.util.List;

public interface ServiceDao extends BasicDao<Service> {
    List<Service> getServicesByIds(List<Long> ids);
    void addService(Service service);
    void acceptService(long id);
    void rejectService(long id);
}