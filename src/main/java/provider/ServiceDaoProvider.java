package provider;

import dao.ServiceDao;
import repository.InMemoryServiceRepository;

public class ServiceDaoProvider {

    public static ServiceDao getServiceDao() {
        return InMemoryServiceRepository.getInstance();
    }
}
