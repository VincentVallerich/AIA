package provider;

import dao.UserDao;
import repository.MemoryUserDao;

public class UserDaoProvider {

    public static UserDao getUserDao() {
        return MemoryUserDao.getInstance();
    }
}
