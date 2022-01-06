package dao;

import model.Service;

public interface ServiceDao extends BasicDao<Service> {
    void addService(Service service);
    void acceptService(long id);
    void rejectService(long id);
}
