package controllers;

import dao.ColocationDao;
import dao.ServiceDao;
import dao.UserDao;
import provider.ColocationDaoProvider;
import provider.ServiceDaoProvider;
import provider.UserDaoProvider;
import service.AuthentificationService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("user")
public class UserController {
    private final ServiceDao serviceDao = ServiceDaoProvider.getServiceDao();
    private final ColocationDao colocationDao = ColocationDaoProvider.getColocationDao();
    private final UserDao userDao = UserDaoProvider.getUserDao();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Response authentification(@FormParam("userName") String username,@FormParam("password") String password) {
        if(userDao.findAuthy(username, password)){
            return Response.status(Response.Status.ACCEPTED).entity(AuthentificationService.giveAToken( userDao.findByMail(username).get())).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();

    }



}
