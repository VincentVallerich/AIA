package model;

import dao.UserDao;
import provider.UserDaoProvider;

import java.util.Base64;

public class Colocation {

    private String name;
    private long id;
    private long adminId;
    private long invitationLink;
    private UserDao userDao = UserDaoProvider.getUserDao();

    public Colocation(){
        this.id=0;
        this.adminId=0;
        this.name="";
    }
    public Colocation(String name, long id, long adminId) {
        this();
        this.name = name;
        this.id = id;
        this.adminId = adminId;
    }

    private void addUser(User user){
        userDao.insert(user);
    }
    private byte[] sendInvitation(long id)
    {
        return Base64.getEncoder().encode(String.valueOf(id).getBytes());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }
}