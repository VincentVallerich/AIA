package dao;

import model.Service;
import model.User;

public interface ServiceDao {
    void addService(Service service);

    int getServiceCount();
}
