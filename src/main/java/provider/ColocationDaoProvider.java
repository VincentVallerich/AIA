package provider;

import dao.ColocationDao;
import repository.InMemoryColocationRepository;

public class ColocationDaoProvider {
    public static ColocationDao getColocationDao() {
        return InMemoryColocationRepository.getInstance();
    }
}
